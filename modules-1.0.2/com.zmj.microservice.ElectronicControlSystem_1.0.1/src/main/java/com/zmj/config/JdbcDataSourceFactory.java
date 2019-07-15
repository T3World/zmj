package com.zmj.config;


import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JdbcDataSourceFactory {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
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
        return new UnpooledDataSource("com.mysql.jdbc.Driver", str, username, password);
    }

}
