package com.t3w.haveadinner.config;

import com.t3w.haveadinner.core.DatabaseContextHolder;
import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @description 类描述
 * @author umr
 * @date 2019/5/28
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    private static final Logger LOGGER = Logger.getLogger(DynamicDataSource.class);

    @Override
    protected Object determineCurrentLookupKey() {
        return DatabaseContextHolder.getDatabaseType();
    }
}
