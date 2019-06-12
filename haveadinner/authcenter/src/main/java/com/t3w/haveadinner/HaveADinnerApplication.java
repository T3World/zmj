package com.t3w.haveadinner;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description 启动类
 * @author umr
 * @date 2019/5/28
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.t3w.haveadinner.mapper","com.t3w.haveadinner.zaokuang.mapper"})
public class HaveADinnerApplication {
    public static void main(String[] args) {
        SpringApplication.run(HaveADinnerApplication.class, args);
    }
}
