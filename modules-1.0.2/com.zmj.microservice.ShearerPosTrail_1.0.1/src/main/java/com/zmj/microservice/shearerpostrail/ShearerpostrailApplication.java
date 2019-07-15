package com.zmj.microservice.shearerpostrail;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.io.FileSystemResource;


/**
 * @author umr,hushixian
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringCloudApplication
public class ShearerpostrailApplication {

    public static void main(String[] args) {
        YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
        yamlPropertiesFactoryBean.setResources(new FileSystemResource("../Conf/com.zmj.microservice.ShearerPostrail.yml"));
        SpringApplication app = new SpringApplication(ShearerpostrailApplication.class);
        app.setDefaultProperties(yamlPropertiesFactoryBean.getObject());
        app.run(args);
    }

}
