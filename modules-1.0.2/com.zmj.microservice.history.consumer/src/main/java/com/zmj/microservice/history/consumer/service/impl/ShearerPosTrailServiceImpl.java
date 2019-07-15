package com.zmj.microservice.history.consumer.service.impl;

import com.bestvike.linq.Linq;
import com.zmj.microservice.common.history.cenum.ResponseCodeEnum;
import com.zmj.microservice.common.history.exception.IllegalParamException;
import com.zmj.microservice.common.history.pojo.DTO.*;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.history.pojo.VO.RunningState;
import com.zmj.microservice.common.history.pojo.VO.SysResult;
import com.zmj.microservice.common.history.util.CommonUtil;
import com.zmj.microservice.common.history.util.DbUtil;
import com.zmj.microservice.common.history.util.StateUtil;
import com.zmj.microservice.history.consumer.service.ShearerPosTrailService;
import com.zmj.microservice.history.consumer.service.feign.ElectronicControlSystemFeignService;
import com.zmj.microservice.history.consumer.service.feign.ShearerSystemFeignService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Service
public  class ShearerPosTrailServiceImpl implements ShearerPosTrailService {

    private static Logger logger = Logger.getLogger(ShearerPosTrailServiceImpl.class);

    @Autowired
    private ShearerSystemFeignService ssservice;

    @Autowired
    private ElectronicControlSystemFeignService eleService;

    @Override
    public SysResult<CommonVO> getInfraredPosData(BaseUNDTO fs) {
        SysResult<CommonVO> infraredPosData = eleService.getInfraredPosData(fs);
        if (infraredPosData.getErrcode()!=200)
            return infraredPosData;
        List<CommonVO> data = infraredPosData.getData();
        //保留两位小数并去重
        CommonUtil.rmreAndRound(data);
        return new SysResult<>(data);
    }

    @Override
    public SysResult getPositionData(ShearerPositionDTO fs) throws IllegalParamException {

        SysResult<CommonVO> sysResult = this.ssservice.getPositionData(new PositionDataDTO(fs.getDataSourceName(), fs.getStartTime(), fs.getEndTime()));
        //验证结果
        if (sysResult.getErrcode() != 200)
            return sysResult;

        BigDecimal s1 = new BigDecimal(fs.getScuNo1());
        BigDecimal s2 = new BigDecimal(fs.getScuNo2());
        BigDecimal p1 = BigDecimal.valueOf(fs.getPos1());
        BigDecimal p2 = BigDecimal.valueOf(fs.getPos2());
        if(s1.equals(s2) || p1.equals(p2))
            throw new IllegalParamException("所传架号不能相等!");

        BigDecimal b = s1.subtract(s2)
                .divide(p1.subtract(p2), 2, RoundingMode.DOWN);
        List<CommonVO> data = sysResult.getData();
        Iterator<CommonVO> iterator = data.iterator();
        List<CommonVO<Integer>> result = new LinkedList<>();
        //去重-2
        boolean flag = true;
        Integer flagi = -1;
        while (iterator.hasNext()){
            CommonVO next = iterator.next();
            BigDecimal value = (BigDecimal)next.getValue();
            //过滤掉pos为0的数据
            if (value.compareTo(BigDecimal.valueOf(0)) == 0)
                continue;

            Integer r = Integer.valueOf(b.multiply(value.subtract(p1))
                    .add(s1)
                    .abs()
                    .setScale(0, RoundingMode.DOWN)
                    .toBigInteger().toString());
            //去重并除0
            flag = !(flagi.equals(r) && Integer.valueOf(0).equals(r));
            flagi = r;
            if (flag) {
                result.add(new CommonVO<>(r,next.getTime()));
            }
        }
        return new SysResult(result);
    }
    @Override
    public SysResult<CommonVO> getInfraredPosWithAutoRunningData(BaseUNDTO fs){
        // 获取自动根机结果
        SysResult<CommonVO> autoResult = eleService.getIsAutoRunningData(fs);
        // 获取煤机红外位置结果
        SysResult<CommonVO> infraredPos = eleService.getInfraredPosData(fs);
        SysResult<CommonVO> result = new SysResult<>();
        List<CommonVO>  dataList = new ArrayList<>();
        if(autoResult.getErrcode()!= 200 && !autoResult.getErrcode().equals(ResponseCodeEnum.EMPTY_RESULT.getValue())){
            return  autoResult;
        }
        if(infraredPos.getErrcode()!= 200 && !autoResult.getErrcode().equals(ResponseCodeEnum.EMPTY_RESULT.getValue())){
            return  infraredPos;
        }
        // 获取自动根机data值
        List<CommonVO>  autoData  =  autoResult.getData();
        // 获取煤机红外data值
        List<CommonVO> infposData =   infraredPos.getData();
        //去重
        CommonUtil.rmreAndRound(infposData);

        dataList.addAll(autoData);
        dataList.addAll(infposData);
        List<CommonVO> historyDOS = Linq.asEnumerable(dataList)
                .orderBy(CommonVO::getTime).toList();
        result.setErrcode(ResponseCodeEnum.OK);
        result.setErrmsg("访问成功");
        result.setData(historyDOS);
        return result;
    }

    @Override
    public SysResult getInfraredPosWithAutoModeData(BaseUNDTO fs) {
        //验证返回值
        SysResult<CommonVO> infrared = this.eleService.getInfraredPosData(fs);
        if (infrared.getErrcode() != 200 && !infrared.getErrcode().equals(ResponseCodeEnum.EMPTY_RESULT.getValue()))
            return infrared;
        SysResult<CommonVO> runningStateData = this.ssservice.getRunningStateData(new RunningStateDataDTO(fs.getDataSourceName(), fs.getStartTime(), fs.getEndTime()));
        if (runningStateData.getErrcode()!=200 && !runningStateData.getErrcode().equals(ResponseCodeEnum.EMPTY_RESULT.getValue()))
            return runningStateData;
        //转化状态为boolean
        List<RunningState<CommonVO<Boolean>>> runningStates = parseStateToBoolean("08", runningStateData.getData(),fs.getStartTime());
        List<CommonVO<Boolean>> list = runningStates.get(0).getData();
        List<CommonVO> data = infrared.getData();
        //去重
        CommonUtil.rmreAndRound(data);
        //排序
        LinkedList<CommonVO> commonVOS = new LinkedList<>();
        commonVOS.addAll(list);
        commonVOS.addAll(data);
        List<CommonVO> result = Linq.asEnumerable(commonVOS).orderBy(CommonVO::getTime).toList();
        return new SysResult(result);
    }

    /**
     * 用于查询在指定时间段编码器位置与自动跟机的情况；
     * 1、	先调用ElectronicControlSystemService获取支架跟机状态；
     * 2、	再调用ShearerSystemService获取相同时间段内的编码器位置 (状态3)；
     * 3、	将编码器的数据与自动跟机状态的数据合并在一起，并按照时间顺序进行排序，返回结果；
     *
     * @param fs
     */
    @Override
    public SysResult<CommonVO> getPositionWithAutoRunningData(ShearerPositionDTO fs) throws IllegalParamException {
        // 获取自动跟机的状态
        SysResult<CommonVO> isAutoRun  =  eleService.getIsAutoRunningData(new BaseUNDTO(fs.getDataSourceName(),fs.getStartTime(),fs.getEndTime()));
        // 获取编码器位置
        SysResult<CommonVO> position = this.getPositionData(fs);
        // 获取自动跟机状态数据
        List<CommonVO> autoList = isAutoRun.getData();
        // 获取编码器位置数据
        List<CommonVO> posList = position.getData();
        List<CommonVO> list = new ArrayList<>();
        if(isAutoRun.getErrcode()!= 200 && !isAutoRun.getErrcode().equals(ResponseCodeEnum.EMPTY_RESULT.getValue())){
            return  isAutoRun;
        }
        if(position.getErrcode()!=200 && !position.getErrcode().equals(ResponseCodeEnum.EMPTY_RESULT.getValue())){
            return  position;
        }
        list.addAll(autoList);
        list.addAll(posList);
        List<CommonVO> comResult = Linq.asEnumerable(list).orderBy(CommonVO::getTime).toList();
        return new SysResult<>(comResult);
    }


    @Override
    public SysResult getPositionWithAutoModeData(ShearerPositionDTO fs) throws IllegalParamException {
        SysResult<CommonVO<Double>> positionData = this.getPositionData(fs);
        if (positionData.getErrcode()!=200 && !positionData.getErrcode().equals(ResponseCodeEnum.EMPTY_RESULT.getValue())) {
            return positionData;
        }
        SysResult<CommonVO> runningStateData = this.ssservice.getRunningStateData(new RunningStateDataDTO(fs.getDataSourceName(), fs.getStartTime(), fs.getEndTime(), "08"));
        if (runningStateData.getErrcode()!=200 && !runningStateData.getErrcode().equals(ResponseCodeEnum.EMPTY_RESULT.getValue()))
            return runningStateData;

        logger.info("runningStateData: List size: "+runningStateData.getData().size());

        List<RunningState<CommonVO<Boolean>>> runningStates = parseStateToBoolean("08", runningStateData.getData(),fs.getStartTime());

        List<CommonVO<Boolean>> list = runningStates.get(0).getData();

        logger.info("08 list size: "+ list.size());

        List<CommonVO<Double>> data = positionData.getData();

        LinkedList<CommonVO> commonVOS = new LinkedList<>();
        commonVOS.addAll(list);
        commonVOS.addAll(data);
        long l = System.currentTimeMillis();
        List<CommonVO> result = Linq.asEnumerable(commonVOS).orderBy(CommonVO::getTime).toList();
        long e = System.currentTimeMillis();
        logger.info("sort cost "+(e-l)+"ms");
        return new SysResult(result);
    }

    /**
     * 采煤机速度接口,去重
     * */
    @Override
    public SysResult getTractionSpeedData(TractionSpeedDataDTO dto) {
        SysResult<CommonVO> tractionSpeedData = this.ssservice.getTractionSpeedData(dto);
        if (!tractionSpeedData.getErrcode().equals(ResponseCodeEnum.OK.getValue()))
            return tractionSpeedData;
        List<CommonVO> data = tractionSpeedData.getData();
        //去重
        CommonUtil.rmreAndRound(data);
        data = Linq.asEnumerable(data).where((CommonVO vo) -> Double.valueOf(vo.getValue().toString()) < 20).toList();
        tractionSpeedData.setData(data);
        return tractionSpeedData;
    }

    /**
     * 解析采煤机状态,将状态转换成boolean值,
     * 如果list.size为空或为nul了,返回null
     * */
    private List<RunningState<CommonVO<Boolean>>> parseStateToBoolean(String stateIds, List<CommonVO> list,Long startTime){

        List<RunningState<CommonVO<Boolean>>> result = new LinkedList<>();
        String[] ids = StateUtil.parseIds(stateIds);
        //先判断list是否为空
        boolean flag = false;

        long begin = System.currentTimeMillis();

        for (int i = 0;i<ids.length;i++ ){
            LinkedList<CommonVO<Boolean>> data = new LinkedList<>();

            if(null == list || list.size() == 0){
                data.add(new CommonVO<>(false,DbUtil.formatTime(startTime)));
            }else {
                Iterator<CommonVO> iterator = list.iterator();

                int times = 0;
                while (iterator.hasNext()){
                    CommonVO<String> next = iterator.next();
                    boolean compare = StateUtil.compare(ids[i], next.getValue());
                    //临时日志
                    if (compare)
                        times++;

                    if(flag != compare){
                        flag = compare;
                        next.setValue(String.valueOf(compare));
                        data.add(new CommonVO<>(compare,next.getTime()));
                    }
                }
                logger.info("08 times :"+ times);
            }
            result.add(new RunningState(ids[i], data));
        }
        long end = System.currentTimeMillis();
        logger.info("paht cost : "+(end-begin) + " ms");
        return result;
    }

}
