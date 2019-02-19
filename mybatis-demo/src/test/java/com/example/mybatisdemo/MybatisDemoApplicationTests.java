package com.example.mybatisdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisDemoApplicationTests {

    @Test
    public void contextLoads() {
        String pattern = "^datavalues_+?\\d*";
        String content1 = "datavalues_20170905113022";
        String content2 = "datavalues_1";
        boolean isMatch = Pattern.matches(pattern, content2);
        System.out.println("*************result:"+isMatch);

        System.out.println(content1.substring(4,5));
        System.out.println(content1.substring(0,1));
        System.out.println(content1.substring(0,2));
        System.out.println(content1.substring(2,3));
    }

}

