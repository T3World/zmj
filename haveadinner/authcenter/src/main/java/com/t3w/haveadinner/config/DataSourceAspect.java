package com.t3w.haveadinner.config;

import com.t3w.haveadinner.constant.DatabaseType;
import com.t3w.haveadinner.core.DatabaseContextHolder;
import com.t3w.haveadinner.mapper.flag.ZaoKuangMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAspect {

    //使用定义切点表达式的方法进行点表达式的引入
    @Before("execution(* com.t3w.haveadinner.mapper.*.*(..))")
    public void setDataSourceKey(JoinPoint point){
        System.out.println("mapper !");
        // 如果连接点所属的类实例是StudentDao
        if(point.getTarget() instanceof ZaoKuangMapper){
            DatabaseContextHolder.setDatabaseType(DatabaseType.ZAOKUANG);
        }else{//连接点是UserDao,可以不写是默认的数据源
            DatabaseContextHolder.setDatabaseType(DatabaseType.LOCAL);
        }
    }

    @After("execution(* com.t3w.haveadinner.controller.*.*(..))")
    public void remove() {
        System.out.println("run service!");
        DatabaseContextHolder.clear();
    }
}
