//package com.zmj.u.m.r;
//
//import com.zmj.microservice.common.history.pojo.VO.CommonVO;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//public class SuanFa {
//
//    public static Map<String,Long> kaijilv(List<CommonVO> list) throws ParseException {
//        //通讯正常时间
//        long a ;
//        //开机率需要的时间
//        long b ;
//        //时间间隔
//        long interval;
//        //上一条数据的时间戳;
//        long lastTime;
//        //
//
//
//
//        //传入参数为完整的从头到尾的四钟电机电流数据集合
//        //1.算出通信正常率,开机率,通讯正常时间
//        Iterator<CommonVO> i = list.iterator();
//        while (i.hasNext()){
//            CommonVO next = i.next();
//
//            long thisTime =   parseTime((String) next.getTime());
////            interval = thisTime - lastTime;
//
//            if (isRunning((double)next.getValue())){
//
//            }
//
//
//
//
//        }
//
//
//    }
//
//    private static long parseTime(String time) throws ParseException {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        return format.parse(time).getTime();
//    }
//
//    private static boolean isRunning(Double current){
//        return current != 0;
//    }
//
//    private static boolean isContinuous(){
//
//    }
//
//}
