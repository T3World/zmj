package com.zmj.ffdsanifnweiqi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
* @description:    注册中心
* @author:         umr
* @date:           2019/5/6
*/
@EnableEurekaServer
@SpringBootApplication
public class FfdsanifnweiqiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FfdsanifnweiqiApplication.class, args);
    }

}
