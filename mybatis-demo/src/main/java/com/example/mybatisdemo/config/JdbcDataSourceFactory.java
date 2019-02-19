package com.example.mybatisdemo.config;


import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@PropertySource("classpath:jdbc.properties")
public class JdbcDataSourceFactory {

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public JdbcDataSourceFactory() {
    }

    public DataSource getJdbcDataSource(String database){
        String str = new StringBuffer(url).append(database).append("?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC").toString();
        return new UnpooledDataSource("com.mysql.cj.jdbc.Driver", str, username, password);
    }

}
