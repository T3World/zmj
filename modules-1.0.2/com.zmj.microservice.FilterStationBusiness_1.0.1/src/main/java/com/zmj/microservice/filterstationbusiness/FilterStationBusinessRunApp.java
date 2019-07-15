package com.zmj.microservice.filterstationbusiness;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.io.FileSystemResource;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class FilterStationBusinessRunApp {
    public static void main(String[] args) {
        YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
        yamlPropertiesFactoryBean.setResources(new FileSystemResource("../Conf/com.zmj.microservice.FilterStationBusiness.yml"));
        SpringApplication app = new SpringApplication(FilterStationBusinessRunApp.class);
        app.setDefaultProperties(yamlPropertiesFactoryBean.getObject());
        app.run(args);
    }
}
