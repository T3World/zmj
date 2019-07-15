package com.zmj.microservice.mixingsystem;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.FileSystemResource;

@SpringBootApplication
@MapperScan("com.zmj.microservice.mixingsystem.mapper")
public class MixingSystemApplication {
    public static void main(String[] args) {
        YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
        yamlPropertiesFactoryBean.setResources(new FileSystemResource("../Conf/com.zmj.microservice.MixingSystem.yml"));
        SpringApplication app = new SpringApplication(MixingSystemApplication.class);
        app.setDefaultProperties(yamlPropertiesFactoryBean.getObject());
        app.run(args);
    }
}
