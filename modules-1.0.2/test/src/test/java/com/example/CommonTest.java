package com.example;

import com.zmj.microservice.common.history.util.DbUtil;
import org.junit.Test;

import java.text.ParseException;

public class CommonTest {
    String a ;

    @Test
    public void testArrayParam(){
        for (int i = 0;i<10;i++){
            t1();
            t1(null);
            t1("single");
            t1(new String[]{});
        }
    }

    private void t1(String... s){
        System.out.println("String...");
    }

//    private void t1(String s){
//        System.out.println("single string");
//    }

    private void t1(){
        System.out.println("no param");
    }

}
