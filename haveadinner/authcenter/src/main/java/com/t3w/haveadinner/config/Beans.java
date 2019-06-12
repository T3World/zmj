package com.t3w.haveadinner.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.t3w.haveadinner.constant.DatabaseType;
import com.t3w.haveadinner.core.ShiroManager;
import com.t3w.haveadinner.core.StatelessDefaultSubjectFactory;
import com.t3w.haveadinner.core.TokenPoke;
import com.t3w.haveadinner.filter.StatelessAuthcFilter;
import com.t3w.haveadinner.realm.JWTRealm;
import com.t3w.haveadinner.realm.MysqlRealm;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SessionStorageEvaluator;
import org.apache.shiro.mgt.SubjectDAO;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @description:    不知道起啥名字好,反正是配置类,放springBean的地方
 * @author:         umr
 * @date:           2019/5/17
 */

@Configuration
public class Beans {

    @Autowired
    private Environment env;

    @Bean("shiroSecurityManager")
    public WebSecurityManager getSecurityManager(JWTRealm jwtRealm, MysqlRealm mysqlRealm, SubjectDAO subjectDAO){
        LinkedList<Realm> realms = new LinkedList<>();
        realms.add(jwtRealm);
        realms.add(mysqlRealm);
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager(realms);
        manager.setSubjectFactory(new StatelessDefaultSubjectFactory());
        DefaultSessionManager sessionManager = new DefaultSessionManager();
        sessionManager.setSessionValidationSchedulerEnabled(false);
        manager.setSessionManager(sessionManager);
        manager.setSubjectDAO(subjectDAO);
        SecurityUtils.setSecurityManager(manager);
        return manager;
    }

    @Bean
    public ShiroManager getShiroManager(@Qualifier("shiroSecurityManager") WebSecurityManager sm ){
        return new ShiroManager(sm);
    }

    /**
     * shiro 的 web 过滤器
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(StatelessAuthcFilter saFilter, @Qualifier("shiroSecurityManager") WebSecurityManager sm){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        HashMap<String, Filter> map = new HashMap<>();
        map.put("statelessAuthc",saFilter);
        shiroFilterFactoryBean.setFilters(map);
        shiroFilterFactoryBean.setFilterChainDefinitions("/**=statelessAuthc");
        shiroFilterFactoryBean.setSecurityManager(sm);
        return shiroFilterFactoryBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean(StatelessAuthcFilter statelessAuthcFilter){
        FilterRegistrationBean<StatelessAuthcFilter> filter = new FilterRegistrationBean<>();
        filter.setFilter(statelessAuthcFilter);
        filter.setName("statelessAuthcFilter");
        filter.addUrlPatterns("/**");
        filter.setOrder(1);
        return filter;

    }

    @Bean
    public SubjectDAO getSubjectDAO(SessionStorageEvaluator storageEvaluator){
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        subjectDAO.setSessionStorageEvaluator(storageEvaluator);
        return subjectDAO;
    }

    @Bean
    public SessionStorageEvaluator getSessionStorageEvaluator(){
        DefaultSessionStorageEvaluator storageEvaluator = new DefaultSessionStorageEvaluator();
        storageEvaluator.setSessionStorageEnabled(false);
        return storageEvaluator;
    }


    private DataSource druidDataSourceLocal(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        dataSource.setMaxActive(env.getProperty("spring.datasource.maxActive",Integer.class));
        return dataSource;
    }

    private DataSource druidDataSourceZaoKuang(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(env.getProperty("t3w.datasource.url"));
        dataSource.setUsername(env.getProperty("t3w.datasource.username"));
        dataSource.setPassword(env.getProperty("t3w.datasource.password"));
        dataSource.setMaxActive(env.getProperty("t3w.datasource.maxActive",Integer.class));
        return dataSource;
    }

    @Bean
    public DynamicDataSource dataSource(){
        DataSource dataSource1 = druidDataSourceLocal();
        DynamicDataSource dataSource = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DatabaseType.LOCAL, dataSource1);
        targetDataSources.put(DatabaseType.ZAOKUANG, druidDataSourceZaoKuang());
        // 该方法是AbstractRoutingDataSource的方法
        dataSource.setTargetDataSources(targetDataSources);
        // 默认的datasource设置为druidDataSourceLocal
        dataSource.setDefaultTargetDataSource(dataSource1);
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DynamicDataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean fb = new MybatisSqlSessionFactoryBean();
        fb.setDataSource(dataSource);
        // 下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
        fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapperLocations")));
        return fb.getObject();
    }

    @Bean
    public TokenPoke tokenPoke(){
        return new TokenPoke();
    }

}
