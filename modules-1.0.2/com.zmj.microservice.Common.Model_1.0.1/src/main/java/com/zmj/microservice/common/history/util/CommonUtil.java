package com.zmj.microservice.common.history.util;

import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;
import java.util.List;
/**
* @author:         umr
* @date:           2019/3/30
*/
public class CommonUtil {
    private static final Logger logger = Logger.getLogger(CommonUtil.class);

    /**
     * 对List<Common>类的数据去重
     *
     * 原理,遍历list
     * 将相邻的数据转化成String类型比对,相同的移除
     * */
    public static void removeRepetition(List<CommonVO> list){
        Iterator<CommonVO> iterator = list.iterator();
        String previous = "";
        while (iterator.hasNext()){
            CommonVO next = iterator.next();
            String value = String.valueOf(next.getValue());
            if(value.equals(previous)){
                iterator.remove();
            }
            previous = value;
        }
    }

    /**
     * 保留两位小数
     * 要求数据类型为小数
     * */
    public static void roung(List<CommonVO> list){
        Iterator<CommonVO> iterator = list.iterator();
        while (iterator.hasNext()){
            CommonVO next = iterator.next();
            BigDecimal value = (BigDecimal)next.getValue();
            //保留两位小数
            next.setValue(value.setScale(2, RoundingMode.DOWN).doubleValue());
        }
    }

    /**
     * 保留两位小数并去重
     * 要求数据类型为小数
     * */
    public static void rmreAndRound(List<CommonVO> list){
        Iterator<CommonVO> iterator = list.iterator();
        BigDecimal previous = BigDecimal.valueOf(0);
        while (iterator.hasNext()){
            CommonVO next = iterator.next();
            BigDecimal value = (BigDecimal)next.getValue();
            //保留两位小数
            next.setValue(value.setScale(2, RoundingMode.DOWN).doubleValue());
            if(value.compareTo(previous)==0){
                iterator.remove();
            }
            previous = value;
        }
    }

}
