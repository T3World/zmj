package com.zmj.microservice.shearersystem;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.io.FileSystemResource;

@EnableEurekaClient
@SpringBootApplication
public class ShearerSystemApplication {
    public static void main(String[] args){
        YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
        yamlPropertiesFactoryBean.setResources(new FileSystemResource("../Conf/com.zmj.microservice.ShearerSystem.yml"));
        SpringApplication app = new SpringApplication(ShearerSystemApplication.class);
        app.setDefaultProperties(yamlPropertiesFactoryBean.getObject());
        app.run(args);
//        SpringApplication.run(ShearerSystemApplication.class,args);
    }
}
