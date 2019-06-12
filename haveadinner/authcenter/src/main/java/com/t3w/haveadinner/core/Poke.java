package com.t3w.haveadinner.core;

import java.util.HashMap;
import java.util.Map;
/**
 * @description 用来随便放点啥
 * @author umr
 * @date 2019/5/29
 */
public class Poke<T> {
    private final Map<String, T> map = new HashMap<>();

    public Poke() {
    }

    public T get(String key){
        return map.get(key);
    }

    public void set(String key,T t){
        map.put(key,t);
    }
}
