package com.zmj.history;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.io.FileSystemResource;

/**
 * @description 启动类
 * @author umr
 * @date 2019/6/5
 */
@EnableEurekaClient
@SpringBootApplication
public class HistoryBaseApplication {
    public static void main(String[] args){
//        YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
//        yamlPropertiesFactoryBean.setResources(new FileSystemResource("../Conf/com.zmj.microservice.HistoryBase.yml"));
//        SpringApplication app = new SpringApplication(HistoryBaseApplication.class);
//        app.setDefaultProperties(yamlPropertiesFactoryBean.getObject());
//        app.run(args);
        SpringApplication.run(HistoryBaseApplication.class,args);
    }
}
