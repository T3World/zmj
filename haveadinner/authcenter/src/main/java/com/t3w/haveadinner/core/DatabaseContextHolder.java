package com.t3w.haveadinner.core;


import com.t3w.haveadinner.constant.DatabaseType;
import org.apache.log4j.Logger;

/**
 * @description 类描述
 * @author umr
 * @date 2019/5/28
 */
public class DatabaseContextHolder {
    /**
     *  线程安全的容器,要注意使用完毕后调用remove方法
     */
    private static final ThreadLocal<DatabaseType> CONTEXT =  new ThreadLocal<>();

    private static final Logger LOGGER = Logger.getLogger(DatabaseContextHolder.class);

    public static void setDatabaseType(DatabaseType type){
        CONTEXT.set(type);
    }

    public static DatabaseType getDatabaseType(){
        return CONTEXT.get();
    }

    public static void clear(){
        CONTEXT.remove();
    }

}
