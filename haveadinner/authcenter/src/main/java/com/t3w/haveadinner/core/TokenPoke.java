package com.t3w.haveadinner.core;

import java.util.HashMap;
import java.util.Map;
/**
 * @description 这是一个口袋,用来在服务器保存token的状态---是否有效,主要用于登出功能
 * @author umr
 * @date 2019/5/29
 */
public class TokenPoke {
    private final Map<String, Boolean> map = new HashMap<>();

    public TokenPoke() {
    }

    public Boolean get(String tokenId){
        Boolean isValid = map.get(tokenId);
        return null == isValid ? false : isValid;
    }

    public void put(String tokenId,Boolean isValid){
        map.put(tokenId,isValid);
    }
}
