package com.t3w.haveadinner.mapper;

import java.util.stream.Stream;

/**
 * @description 模块表 crud
 * @author umr
 * @date 2019/5/29
 */
public interface ModuleMapper {
    default void test(){
        Stream.Builder<Object> builder = Stream.builder();
        Stream<Object> build = builder.build();

    }

}
