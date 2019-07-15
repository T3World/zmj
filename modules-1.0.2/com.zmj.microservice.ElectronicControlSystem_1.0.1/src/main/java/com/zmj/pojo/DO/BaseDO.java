package com.zmj.pojo.DO;

import java.io.Serializable;
import java.util.List;

public interface BaseDO extends Serializable {
    /* 每个配置用POJO都要有名字 */
    String getName();

    /* 返回下属子机构list,最底层返回null */
    List getChildrenList();
}
