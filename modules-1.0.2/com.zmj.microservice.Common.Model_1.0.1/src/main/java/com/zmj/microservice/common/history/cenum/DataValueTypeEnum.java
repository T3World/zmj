package com.zmj.microservice.common.history.cenum;

/**
 * 查询历史记录时
 * 数据库返回值类型,
 *
 * @author umr,hushixian
 * @date 2019-3-30
 */

public enum DataValueTypeEnum {
    /**
     *
     * */
    BOOLEAN(Boolean.class),DOUBLE(Double.class),STRING(String.class);

    private Class c;

    DataValueTypeEnum(Class c) {
        this.c = c;
    }

    public Class getC() {
        return c;
    }
}
