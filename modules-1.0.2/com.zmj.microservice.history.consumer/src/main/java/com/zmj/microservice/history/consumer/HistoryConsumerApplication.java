package com.zmj.microservice.history.consumer;


import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.io.FileSystemResource;

/**
 * @description main
 * @author umr
 * @date 2019/6/10
 */
@SpringCloudApplication
@EnableEurekaClient
@EnableFeignClients
public class HistoryConsumerApplication {

    public static void main(String... args){
        YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
        yamlPropertiesFactoryBean.setResources(new FileSystemResource("../Conf/com.zmj.microservice.HistoryConsumer.yml"));
        SpringApplication app = new SpringApplication(HistoryConsumerApplication.class);
        app.setDefaultProperties(yamlPropertiesFactoryBean.getObject());
        app.run(args);
//        SpringApplication.run(HistoryConsumerApplication.class,args);
    }
    
}
