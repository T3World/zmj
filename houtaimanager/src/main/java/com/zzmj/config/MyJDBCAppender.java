package com.zzmj.config;

import org.apache.log4j.Category;
import org.apache.log4j.Priority;
import org.apache.log4j.jdbc.JDBCAppender;
import org.apache.log4j.spi.LoggingEvent;

public class MyJDBCAppender extends JDBCAppender {
    protected String getLogStatement(LoggingEvent event) {

        String fqnOfCategoryClass = event.fqnOfCategoryClass;

        Category logger = Category.getRoot();

        Priority level = event.level;

        Object message = event.getMessage();
        Throwable throwable = null;

        MyLoggingEvent bEvent = new MyLoggingEvent(fqnOfCategoryClass, logger, level, message, throwable);

        return super.getLogStatement(bEvent);
    }
}
