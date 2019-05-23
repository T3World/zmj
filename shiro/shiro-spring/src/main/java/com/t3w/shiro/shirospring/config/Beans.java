package com.t3w.shiro.shirospring.config;

import com.t3w.shiro.shirospring.filter.StatelessAuthcFilter;
import com.t3w.shiro.shirospring.shiro.other.ShiroManager;
import com.t3w.shiro.shirospring.shiro.other.StatelessDefaultSubjectFactory;
import com.t3w.shiro.shirospring.shiro.realm.JWTRealm;
import com.t3w.shiro.shirospring.shiro.realm.MysqlRealm;
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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @description:    不知道起啥名字好,反正是配置类,放springBean的地方
 * @author:         umr
 * @date:           2019/5/17
 */

@Configuration
public class Beans {

    @Bean("shiroSecurityManager")
    public WebSecurityManager getSecurityManager(JWTRealm jwtRealm, MysqlRealm mysqlRealm,SubjectDAO subjectDAO){
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
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(StatelessAuthcFilter saFilter,@Qualifier("shiroSecurityManager") WebSecurityManager sm){
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

}
