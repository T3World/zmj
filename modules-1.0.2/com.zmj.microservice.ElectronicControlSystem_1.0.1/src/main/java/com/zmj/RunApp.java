package com.zmj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.io.FileSystemResource;

@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.zmj.mapper")
public class RunApp{
    public static void main(String... args) {
        YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
        yamlPropertiesFactoryBean.setResources(new FileSystemResource("../Conf/com.zmj.microservice.ElectronicControlSystem.yml"));
        SpringApplication app = new SpringApplication(RunApp.class);
        app.setDefaultProperties(yamlPropertiesFactoryBean.getObject());
        app.run(args);
    }
}
