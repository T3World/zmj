package com.t3w;

public interface Alternate<T> extends AfterAlternate {

    /**
     * 获取当前对象
     * */
    T get();

    /**
     * 交换对当前目标和替补,尾行应执行 AfterAlternate() 方法
     * */
    void alternate();

}
