package com.zzmj.service.impl;

import com.alibaba.fastjson.JSON;
import com.zzmj.mapper.ZZDeviceAttributeRelationMapper;
import com.zzmj.mapper.ZZDeviceMapper;
import com.zzmj.mapper.ZZDeviceTypeMapper;
import com.zzmj.mapper.ZZWorkfaceDeviceMapper;
import com.zzmj.pojo.entity.ZZDevice;
import com.zzmj.pojo.entity.ZZDeviceAttributeRelation;
import com.zzmj.pojo.entity.ZZDeviceExample;
import com.zzmj.pojo.entity.ZZDeviceType;
import com.zzmj.pojo.vo.AttributeInnerAttributeDevice;
import com.zzmj.pojo.vo.DeviceWithAttribute;
import com.zzmj.pojo.vo.PageObject;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.service.ZZDeviceService;
import com.zzmj.util.CodeUtil;
import com.zzmj.util.ErrorUtil;
import com.zzmj.util.FormatDateUtil;
import com.zzmj.util.exception.DoSqlFailedException;
import com.zzmj.util.exception.EmptyResultException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service("ZZDeviceService")
public class ZZDerviceServiceImpl implements ZZDeviceService {

    private static Logger logger = Logger.getLogger(ZZGlobalServiceImpl.class);
    @Autowired
    private ZZDeviceMapper zzDeviceMapper;
    @Autowired
    private ZZDeviceTypeMapper zzDeviceTypeMapper;
    @Autowired
    private ZZDeviceAttributeRelationMapper zzDeviceAttributeRelationMapper;
    @Autowired
    private ZZWorkfaceDeviceMapper zzWorkfaceDeviceMapper;
    

    /**
     * 添加 设置isDel属性为0
     */
    @Override
    public SysResult addZZDevice(ZZDevice zzDevice) {
        try {
            String deviceId = CodeUtil.createUuid36();
            String id = CodeUtil.createUuid36();
            zzDevice.setDeviceId(deviceId);
            zzDevice.setId(id);
            zzDevice.setUpdateTime(FormatDateUtil.dataFormat(new Date()));

            zzDevice.setIsDel("0");
            int flag = zzDeviceMapper.insertSelective(zzDevice);
            if (flag < 1) {
                throw new DoSqlFailedException("添加设备失败!");
            }
            return new SysResult(ErrorUtil.CODE2000, "添加成功", zzDevice);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("设备添加失败，出现异常", e);
            throw new DoSqlFailedException("设备添加失败");
        }

    }

    /**
     * 单个删除设备表
     * 先根据deviceId判断关联关系的表(工作面设备)是否有isDel为0的数据
     */
    @Override
    public SysResult delZZDevice(ZZDevice zzDevice) {
        try {
            String deviceId = zzDevice.getDeviceId();
            
            int a=zzWorkfaceDeviceMapper.selectWorkfaceDeviceByDeviceId(deviceId);
            if(a>0)
            	 return new SysResult(ErrorUtil.CODE2001, "工作面设备表中存在相关联的数据", zzDevice);
            
            zzDevice.setIsDel("1");
            zzDevice.setUpdateTime(FormatDateUtil.dataFormat(new Date()));
            ZZDeviceExample example = new ZZDeviceExample();
            example.createCriteria().andDeviceIdEqualTo(deviceId);
            int flag = zzDeviceMapper.updateByExampleSelective(zzDevice, example);
            if (flag < 1)
                return new SysResult(ErrorUtil.CODE2001, "删除失败!", flag);
            return new SysResult(ErrorUtil.CODE2000, "删除成功ok", zzDevice);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除失败", e);
            throw new DoSqlFailedException("删除失败!");
        }
    }

    @Override
    public SysResult updateZZdevice(ZZDevice zzDevice) {
        try {
            String deviceId = zzDevice.getDeviceId();
            zzDevice.setUpdateTime(FormatDateUtil.dataFormat(new Date()));
            ZZDeviceExample example = new ZZDeviceExample();
            example.createCriteria().andDeviceIdEqualTo(deviceId);
            int flag = zzDeviceMapper.updateByExampleSelective(zzDevice, example);
            if (flag < 1)
                return new SysResult(ErrorUtil.CODE2001, "更新失败!", flag);
            return new SysResult(ErrorUtil.CODE2000, "更新成功ok", zzDevice);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("更新失败!", e);
            throw new DoSqlFailedException("更新失败!", e);
        }
    }

    @Override
    public SysResult selectByid(String deviceId) {

        try {
            ZZDeviceExample example = new ZZDeviceExample();
            example.createCriteria().andDeviceIdEqualTo(deviceId);

//			 List<ZZDevice> list= zzDeviceMapper.selectByExample(example);
            List<ZZDevice> list = zzDeviceMapper.selectByExampleWithBLOBs(example);
            if (list != null)
                return new SysResult(ErrorUtil.CODE2000, "查询成功ok", list);
            return new SysResult(ErrorUtil.CODE2001, "查询失败", null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询一个失败!", e);
            throw new DoSqlFailedException("查询失败!", e);
        }
    }

    /**
     * 根据设备类型id查询设备表集合 且查询出的对象的isDel属性为0
     */
    @Override
    public SysResult selectByDeviceTypeId(String deviceTypeId) {
        if ("".equals(deviceTypeId.trim()))
            deviceTypeId = null;
        List<ZZDevice> list = zzDeviceMapper.selectByDeviceTypeId(deviceTypeId);
        if (list.size() == 0) {
            throw new EmptyResultException("查询为空!");
        }
        return new SysResult(ErrorUtil.CODE2000, "查询成功ok", list);
    }

    /**
     * 对ZZDevice设备表进行分页 设备类型表id DeviceType_Id 对Device_Name进行模糊查询 第几页 每页显示几条
     */
    @Override
    public SysResult listZZDevicePage(String devicetypeId, String keyword, Integer pageNo, Integer pageSize) {

        try {
            if (pageNo == null || pageNo == 0) {
                pageNo = 1;
            } else {
                pageNo = pageNo;
            }
            if (pageSize == null || pageSize == 0) {
                pageSize = 11;
            } else {
                pageSize = pageSize;
            }
            if (null == keyword || keyword.equals("")) {
                keyword = "%%";
            } else {
                keyword = "%" + keyword + "%";
            }
            // 先查询结果总数
            int count = this.getCount(devicetypeId, keyword);
            // 从第几条开始
            int rowNum = (pageNo - 1) * pageSize;
            List<ZZDevice> zzDeviceList = ListZZDeviceByPage(devicetypeId, keyword, rowNum, pageSize);
            return new SysResult(ErrorUtil.CODE2000, "获取成功",
                    new PageObject<ZZDevice>(pageNo, pageSize, count, zzDeviceList));
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("分页查询出现异常，请检查");
            throw new DoSqlFailedException("分页查询出现异常,请检查");
        }
    }

    List<ZZDevice> ListZZDeviceByPage(String devicetypeId, String keyword, Integer rowNum, Integer pageSize) {
        if (null == devicetypeId || devicetypeId.equals("")) {
            if (null == keyword || keyword.equals("")) {
                List<ZZDevice> list = zzDeviceMapper.listZZDeviceAll(rowNum, pageSize);
                logger.info(String.valueOf(list.size()));
                return list;
            }
            return zzDeviceMapper.listDeviceByKeyword(keyword, rowNum, pageSize);
        } else {
            if (null == keyword || keyword.equals("")) {
                List<ZZDevice> list = zzDeviceMapper.listZZDeviceBydeviceTypeId(devicetypeId, rowNum, pageSize);
                logger.info(String.valueOf(list.size()));
                return list;
            }
        }
        return zzDeviceMapper.getlikeKeyWordAndDevicetypeId(devicetypeId, keyword, rowNum, pageSize);
    }

    /**
     * 查询总条数 分为四种情况
     */
    public int getCount(String devicetypeId, String keyword) {
        if (null == devicetypeId || devicetypeId.equals("")) {
            if (null == keyword || keyword.equals("")) {
                return zzDeviceMapper.getAllCount(); // 当设备类型id，关键字都为空的时候，查找表中的所有个数
            }
            return zzDeviceMapper.countDeviceByKeyword(keyword); // 当设备类型Id为空，关键字不为空的时候，查找符合关键字的条数
        }
        if (null == keyword || keyword.equals("")) {
            return zzDeviceMapper.getCountBydeviceTypeId(devicetypeId); // 当关键字为空，设备类型id不为空的时，查找符合设备类型id的条数
        }
        // 当设备类型id，关键字都不为空时，查询符合双方条件的数量
        return zzDeviceMapper.countDeviceByDeviceTypeIdAndByKeyword(devicetypeId, keyword);
    }

    /**
     * 根据工作面id 设备类型id 查询所有设备集合
     */
    @Override
    public SysResult selectDeviceByWorkfaceAndDeviceTypeId(String workfaceId, String deviceTypeId) {
        try {
            List<ZZDevice> deviceList = zzDeviceMapper.selectDeviceByWorkfaceAndDeviceTypeId(workfaceId, deviceTypeId);
            if (deviceList != null)
                return new SysResult(ErrorUtil.CODE2000, "查询所有设备成功ok", deviceList);
            return new SysResult(ErrorUtil.CODE2001, "查询所有设备失败", null);
        } catch (Exception e) {
            logger.error("查询所有设备失败!", e);
            throw new DoSqlFailedException("查询所有设备失败!", e);
        }
    }

    /**
     * 根据id批量删除
     */
    @Override
    public SysResult batchDel(String ids) {
        
    	try {
			String[] id=ids.split(",");
			List<String> idToList=Arrays.asList(id);
			
			for(String deviceId:idToList){
				ZZDevice zzDevice=zzDeviceMapper.selectByDeviceId(deviceId);
				
				SysResult sysResult=this.delZZDevice(zzDevice);
				if(sysResult.getCode().equals("2001"))
					return new SysResult(ErrorUtil.CODE2001, "批量删除设备失败 失败！！", zzDevice);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("批量删除设备失败,出现异常", e);
            throw new DoSqlFailedException("批量删除设备失败,出现异常", e);
		}
    	
    	return new SysResult(ErrorUtil.CODE2000, "批量删除设备成功 成功！！", ids);
    	
    	
    	/*int result = 0;
        int add = 0;
        try {
            String fenli[] = ids.split(",");
            if (fenli.length > 0) {
                for (int i = 0; i < fenli.length; i++) {
                    String deviceId = fenli[i];
                    ZZDevice device = new ZZDevice();
                    device.setIsDel("1");
                    ZZDeviceExample example = new ZZDeviceExample();
                    example.createCriteria().andDeviceIdEqualTo(deviceId);
                    result = this.zzDeviceMapper.updateByExampleSelective(device, example);
                    add = add + result;
                }
                if (add == fenli.length) {
                    return new SysResult(ErrorUtil.CODE2000, "批量删除设备成功", null);
                }
                return new SysResult(ErrorUtil.CODE2001, "批量删除设备失败,id不存在", null);
            } else {
                return new SysResult(ErrorUtil.CODE2001, "批量删除设备出错,参数为空", null);
            }
        } catch (DoSqlFailedException e) {
            e.printStackTrace();
            logger.error("批量删除设备失败,出现异常", e);
            throw new DoSqlFailedException("批量删除设备失败,出现异常", e);
        }*/
    }



    /**
     * 连表查询deviceId
     * */
    @Override
    public SysResult listDeviceAttributeAllByDeviceId(String deviceId) {
        List<AttributeInnerAttributeDevice> attributes = null;
        try {
            attributes = this.zzDeviceAttributeRelationMapper.listDeviceAttributeAllByDeviceId(deviceId);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            throw new DoSqlFailedException("连表查询deviceId失败,出现异常", e);
        }
        return new SysResult(ErrorUtil.CODE2000, "连表查询属性成功", attributes);
    }

    /**
     * 根据deviceId查询单个设备对应的设备属性集合
     */
    @Override
    public SysResult selectDeviceWithAttributeByDeviceId(String deviceId) {
        DeviceWithAttribute deviceWithAttribute = null;
        try{
            ZZDevice zzDevice;
            ZZDeviceExample example = new ZZDeviceExample();
            example.createCriteria().andDeviceIdEqualTo(deviceId);
            List<ZZDevice> zzDevices = this.zzDeviceMapper.selectByExample(example);
            if (zzDevices.size()>0){
                zzDevice = zzDevices.get(0);
                List<AttributeInnerAttributeDevice> attributes = this.zzDeviceAttributeRelationMapper.listDeviceAttributeAllByDeviceId(deviceId);
                deviceWithAttribute = new DeviceWithAttribute(zzDevice, attributes);
                return new SysResult(ErrorUtil.CODE2000, "查询成功!", deviceWithAttribute);
            }else {
                return new SysResult(ErrorUtil.CODE2001, "查无结果!", null);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            throw new DoSqlFailedException("查询出错", e);
        }
    }



    /**
     * 查询所有的设备表和每个设备对应的设备属性表  关系稍微有点复杂
     */
    @Override
    public SysResult selectDeviceWithAttributeAll() {

        try {
            List<DeviceWithAttribute> deviceWithAttributeList = new ArrayList<>();
            //查询所有设备集合
            List<ZZDevice> zzDeviceList=zzDeviceMapper.selectAllDevice();
            //设备表与设备属性表  一对到的关系   遍历设备集合
            for(int i=0;i<zzDeviceList.size();i++){
                //获取每一个设备的id
                String deviceId=zzDeviceList.get(i).getDeviceId();
                //获取每一个设备对应的设备属性集合
                List<AttributeInnerAttributeDevice> attributes=zzDeviceAttributeRelationMapper.listDeviceAttributeAllByDeviceId(deviceId);

                deviceWithAttributeList.add(new DeviceWithAttribute(zzDeviceList.get(i), attributes));
            }
            return new SysResult(ErrorUtil.CODE2000, "查询设备表和设备属性表成功!", deviceWithAttributeList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询设备表和设备属性表出现异常", e);
            throw new DoSqlFailedException("查询设备表和设备属性表出现异常  异常", e);
        }
    }



    /**
     * 对设备表和设备属性表进行分页
     * 首先应当对设备表进行分页    其次查询所有的设备表和设备属性表数据并将其放入DeviceWithAttribute实体类中
     */
    public SysResult selectDeviceWithAttributePage(String keyword,Integer pageNo,Integer pageSize){


        PageObject<DeviceWithAttribute> pageObject = null;

        if(null==keyword || keyword.equals("")){
            keyword="%%";
        }else{
            keyword="%"+keyword+"%";
        }
        if(null==pageNo || pageNo==0)
            pageNo=1;
        if(null==pageSize ||pageSize==0 ){
            pageSize=11;
        }
        //每页从第几条开始
        Integer rowNo=(pageNo-1)*pageSize;

        /**
         * 查询设备表 和每一个设备对应的设备属性表  的所有数据
         */
        List<DeviceWithAttribute> deviceWithAttributeList = new ArrayList<>();


        try {
            if(null==keyword || keyword.equals("")){
                Integer rowCount=zzDeviceMapper.getAllCount();

                if (rowCount > 0) {
                    //查询所有设备集合(关键字无  并且对设备表进行分页)
                    List<ZZDevice> zzDeviceList=zzDeviceMapper.listZZDeviceAll(rowNo, pageSize);

                    //设备表与设备属性表  一对到的关系   遍历设备集合    并获取每个设备对应的设备属性集合
                    for(int i=0;i<zzDeviceList.size();i++){
                        //获取每一个设备的id
                        String deviceId=zzDeviceList.get(i).getDeviceId();
                        //获取每一个设备对应的设备属性集合
                        List<AttributeInnerAttributeDevice> attributes=zzDeviceAttributeRelationMapper.listDeviceAttributeAllByDeviceId(deviceId);

                        deviceWithAttributeList.add(new DeviceWithAttribute(zzDeviceList.get(i), attributes));
                        pageObject = new PageObject<DeviceWithAttribute>(pageNo, pageSize, rowCount, deviceWithAttributeList);
                    }
                    return new SysResult(ErrorUtil.CODE2000, "ok", pageObject);
                }else{
                    return new SysResult(ErrorUtil.CODE2001, "不好意思  数据库没有数据", null);
                }

            }else{
                Integer rowCount=zzDeviceMapper.countDeviceByKeyword(keyword);
                if(rowCount > 0){
                    //查询所有设备集合  此时(有关键字  并且对设备表进行分页 )
                    List<ZZDevice> zzDeviceList=zzDeviceMapper.listDeviceByKeyword(keyword,rowNo, pageSize);

                    //设备表与设备属性表  一对到的关系   遍历设备集合    并获取每个设备对应的设备属性集合
                    for(int i=0;i<zzDeviceList.size();i++){
                        //获取每一个设备的id
                        String deviceId=zzDeviceList.get(i).getDeviceId();
                        //获取每一个设备对应的设备属性集合
                        List<AttributeInnerAttributeDevice> attributes=zzDeviceAttributeRelationMapper.listDeviceAttributeAllByDeviceId(deviceId);

                        deviceWithAttributeList.add(new DeviceWithAttribute(zzDeviceList.get(i), attributes));
                        pageObject = new PageObject<DeviceWithAttribute>(pageNo, pageSize, rowCount, deviceWithAttributeList);
                    }
                    return new SysResult(ErrorUtil.CODE2000, "ok", pageObject);
                }else{
                    return new SysResult(ErrorUtil.CODE2001, "不好意思  数据库没有数据", null);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询设备表和设备属性表出现异常", e);
            throw new DoSqlFailedException("查询设备表和设备属性表出现异常  异常", e);
        }

    }

    /**
     * 往设备表中添加数据 
     * 先往设备表添加  再往设备属性关系表中循环遍历添加
     */
    @Override
    public SysResult addDeviceWithAttribute(DeviceWithAttribute deviceWithAttribute) {

        System.out.println(JSON.toJSONString(deviceWithAttribute));
        //zzDevice.setDeivceAttribute(deviceWithAttribute.getDeivceAttribute());   设备参数  此字段在写该方法的时候已经废弃
        int flag;
        try {
            ZZDevice zzDevice = new ZZDevice();

            String id = CodeUtil.createUuid36();
            String deviceId = CodeUtil.createUuid36();
            zzDevice.setId(id);
            zzDevice.setDeviceId(deviceId);
            zzDevice.setDeviceName(deviceWithAttribute.getDeviceName());
            zzDevice.setDeviceAlias(deviceWithAttribute.getDeviceAlias());
            zzDevice.setDeviceFirm(deviceWithAttribute.getDeviceFirm());
            zzDevice.setDeviceModel(deviceWithAttribute.getDeviceModel());
            zzDevice.setDeviceImg(deviceWithAttribute.getDeviceImg());
            zzDevice.setDeviceTypeId(deviceWithAttribute.getDeviceTypeId());
            zzDevice.setSortCode(deviceWithAttribute.getSortCode());
            zzDevice.setUpdateTime(FormatDateUtil.dataFormat(new Date()));
            zzDevice.setIsDel("0");

            flag = zzDeviceMapper.insertSelective(zzDevice);

            if (flag > 0) {
                //获取到的集合是 设备属性表加设备属性关系表共有属性
                List<AttributeInnerAttributeDevice> deivceAttribute = deviceWithAttribute.getDeviceAttribute();
                //创建设备属性关系表对象并且对该集合进行遍历
                ZZDeviceAttributeRelation zzDeviceAttributeRelation=new ZZDeviceAttributeRelation();
                for(AttributeInnerAttributeDevice attributeInnerAttributeDevice:deivceAttribute){
                    zzDeviceAttributeRelation.setId(CodeUtil.createUuid36());
                    zzDeviceAttributeRelation.setDeviceId(zzDevice.getDeviceId());
                    zzDeviceAttributeRelation.setAttributeId(attributeInnerAttributeDevice.getAttributeId());
                    zzDeviceAttributeRelation.setAttributeValue(attributeInnerAttributeDevice.getAttributeValue());

                    try {
                        int count = zzDeviceAttributeRelationMapper.insertZZDeviceAttributeRelation(zzDeviceAttributeRelation);
                        if(count<1)
                            return new SysResult(ErrorUtil.CODE2001, "添加设备属性关系表失败", zzDeviceAttributeRelation);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new DoSqlFailedException("添加设备属性关系表失败",e);
                    }
                }
                return new SysResult(ErrorUtil.CODE2000, "添加设备表成功", deviceWithAttribute);
            }
            return new SysResult(ErrorUtil.CODE2001, "添加设备表失败", zzDevice);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("添加设备失败,出现异常", e);
            throw new DoSqlFailedException("添加设备失败,出现异常", e);
        }
    }




    /**
     * 更改设备表  
     * 先删除设备属性关系表  再更改设备表  再循环遍历添加设备属性关系表  
     */
    @Override
    public SysResult updateDeviceWithAttributeByDeviceId(DeviceWithAttribute deviceWithAttribute) {

        try {
            String deviceId=deviceWithAttribute.getDeviceId();
            if(null ==deviceId ||deviceId.equals(""))
                return new SysResult(ErrorUtil.CODE2001, "设备id不存在", deviceId);

            //首先根据deviceId删除设备属性关系表
            zzDeviceAttributeRelationMapper.deleteByDeviceId(deviceId);

            ZZDevice zzDevice=new ZZDevice();
            zzDevice.setDeviceId(deviceId);
            zzDevice.setDeviceName(deviceWithAttribute.getDeviceName());
            zzDevice.setDeviceAlias(deviceWithAttribute.getDeviceAlias());
            zzDevice.setDeviceFirm(deviceWithAttribute.getDeviceFirm());
            zzDevice.setDeviceModel(deviceWithAttribute.getDeviceModel());
            zzDevice.setDeviceImg(deviceWithAttribute.getDeviceImg());
            zzDevice.setDeviceTypeId(deviceWithAttribute.getDeviceTypeId());
            zzDevice.setSortCode(deviceWithAttribute.getSortCode());
//          zzDevice.setDeivceAttribute(deviceWithAttribute.getDeivceAttribute());		//设备参数   此字段在写该方法的时候已经废弃
            zzDevice.setUpdateTime(FormatDateUtil.dataFormat(new Date()));
            ZZDeviceExample example = new ZZDeviceExample();
            example.createCriteria().andDeviceIdEqualTo(deviceId);
            int flag = zzDeviceMapper.updateByExampleSelective(zzDevice, example);

            if(flag>0){

                //获取到的集合是 设备属性表加设备属性关系表共有属性
                List<AttributeInnerAttributeDevice> deivceAttribute = deviceWithAttribute.getDeviceAttribute();
                //创建设备属性关系表对象并且对该集合进行遍历
                ZZDeviceAttributeRelation zzDeviceAttributeRelation=new ZZDeviceAttributeRelation();

                for(AttributeInnerAttributeDevice attributeInnerAttributeDevice:deivceAttribute){
                    zzDeviceAttributeRelation.setId(CodeUtil.createUuid36());
                    zzDeviceAttributeRelation.setDeviceId(deviceId);
                    zzDeviceAttributeRelation.setAttributeId(attributeInnerAttributeDevice.getAttributeId());
                    zzDeviceAttributeRelation.setAttributeValue(attributeInnerAttributeDevice.getAttributeValue());

                    try {
                        int count=zzDeviceAttributeRelationMapper.insertZZDeviceAttributeRelation(zzDeviceAttributeRelation);
                        if(count<1){
                            return new SysResult(ErrorUtil.CODE2001, "更改设备属性关系表失败", zzDeviceAttributeRelation);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new DoSqlFailedException("更改设备属性关系表失败",e);
                    }
                }
                return new SysResult(ErrorUtil.CODE2000, "更改设备表成功", deviceWithAttribute);
            }
            return new SysResult(ErrorUtil.CODE2001, "更改设备表失败", zzDevice);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("更改设备失败,出现异常", e);
            throw new DoSqlFailedException("更改设备失败,出现异常", e);
        }
    }




    /**
     * 删除设备
     * 先删除设备属性关系表  再删除设备表
     */
    @Override
    public SysResult deleteDeviceWithAttributeByDeviceId(DeviceWithAttribute deviceWithAttribute) {

        try {
            String deviceId=deviceWithAttribute.getDeviceId();
            if(null ==deviceId ||deviceId.equals(""))
                return new SysResult(ErrorUtil.CODE2001, "设备id不存在", deviceId);

            //先删除设备属性关系表
            zzDeviceAttributeRelationMapper.deleteByDeviceId(deviceId);
            //再删除设备表  在这里先定义假删除
            ZZDevice zzDevice=new ZZDevice();
            zzDevice.setIsDel("1");
            zzDevice.setUpdateTime(FormatDateUtil.dataFormat(new Date()));
            ZZDeviceExample example = new ZZDeviceExample();
            example.createCriteria().andDeviceIdEqualTo(deviceId);
            int flag = zzDeviceMapper.updateByExampleSelective(zzDevice, example);
            if (flag < 1)
                return new SysResult(ErrorUtil.CODE2001, "删除失败!", flag);
            return new SysResult(ErrorUtil.CODE2000, "删除设备表成功ok", zzDevice);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除失败", e);
            throw new DoSqlFailedException("删除失败!");
        }


    }
}
