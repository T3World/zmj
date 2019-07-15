package com.zzmj;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;

import java.io.IOException;
@MapperScan(basePackages = "com.zzmj.mapper")
@SpringBootApplication
public class RunApp {
    public static void main(String[] args) throws IOException {
//        YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
//        yamlPropertiesFactoryBean.setResources(new FileSystemResource("../Conf/com.zmj.microservice.ZaokuangManager.yml"));
//        SpringApplication app = new SpringApplication(RunApp.class);
//        app.setDefaultProperties(yamlPropertiesFactoryBean.getObject());
//        app.run(args);
        SpringApplication.run(RunApp.class,args);
    }

    /**
     *
     * 1.需要先定义一个convert转换消息的对象； 2.添加fastjson的配置信息，比如是否要格式化返回的json数据
     * 3.在convert中添加配置信息 4.将convert添加到converters中
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {

        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        FastJsonConfig fastJsonConfig = new FastJsonConfig();

        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);

        fastConverter.setFastJsonConfig(fastJsonConfig);

        HttpMessageConverter<?> converter = fastConverter;

        return new HttpMessageConverters(converter);

    }

}
