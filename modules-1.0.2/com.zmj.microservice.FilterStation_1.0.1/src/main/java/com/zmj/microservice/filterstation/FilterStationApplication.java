package com.zmj.microservice.filterstation;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.FileSystemResource;

@SpringBootApplication
public class FilterStationApplication {
    public static void main(String[] args){
        YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
        yamlPropertiesFactoryBean.setResources(new FileSystemResource("../Conf/com.zmj.microservice.FilterStation.yml"));
        SpringApplication app = new SpringApplication(FilterStationApplication.class);
        app.setDefaultProperties(yamlPropertiesFactoryBean.getObject());
        app.run(args);
    }
}
