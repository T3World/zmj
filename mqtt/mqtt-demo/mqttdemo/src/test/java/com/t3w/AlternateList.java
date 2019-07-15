package com.t3w;

import java.util.LinkedList;

/**
 * @description 类描述
 * @author umr
 * @date 2019/6/14
 */
public class AlternateList {

    private static final LinkedList<Object> YIN;
    private static final LinkedList<Object> YANG;

    private static LinkedList<Object> on;
    private static LinkedList<Object> off;
    static {
        YIN = new LinkedList<>();
        YANG = new LinkedList<>();
        on = YANG;
        off = YIN;
    }

    public static LinkedList<Object> getList(){
        return on;
    }

    private static void alternate(){
        LinkedList<Object> var = on;
        on = off;
        off = var;
        afterAlternate();
    }

    private static void afterAlternate() {


    }

}
