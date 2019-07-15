package com.zmj.microservice;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.io.FileSystemResource;

/**
* @description:    主类
* @author:         umr
* @date:           2019/4/20
*/

@SpringBootApplication
@EnableEurekaClient
public class TripleMachineApplication {
    public static void main(String... args){
        YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
        yamlPropertiesFactoryBean.setResources(new FileSystemResource("../Conf/com.zmj.microservice.TripleMachineSystem.yml"));
        SpringApplication app = new SpringApplication(TripleMachineApplication.class);
        app.setDefaultProperties(yamlPropertiesFactoryBean.getObject());
        app.run(args);
//        SpringApplication.run(TripleMachineApplication.class,args);
    }
}
