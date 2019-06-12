package com.t3w.haveadinner.mapper;

import com.t3w.haveadinner.pojo.Resource;

public interface ResourceMapper {

    Resource selectResourceByName(String name);
}
