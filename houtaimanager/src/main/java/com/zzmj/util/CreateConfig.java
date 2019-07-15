package com.zzmj.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class CreateConfig {

//    // 当前classpath的绝对路径
//    private String url = this.getClass().getClassLoader().getResource("").getFile();
    // app/conf配置文件路径
    private static File XML_FILE = new File("../Conf/application.xml");
    // spring配置文件路径
    private static File PROP_FILE = new File("config/application.properties");
    // 日志
    private static final Logger logger = Logger.getLogger("CreateConfig");

    public static void run() {
        logger.info(XML_FILE);
        logger.info(PROP_FILE);
        logger.info("---------CreateConfig begin!------------");
        Properties prop = new Properties();
        logger.info("----判断文件路径是否正确------");
        logger.warn(XML_FILE);
        if (XML_FILE.exists()) {
            logger.info("---------xml路径正确-----------");
            try {
                // 加载spring配置文件
                prop.load(new FileInputStream(PROP_FILE));
                logger.info("---------加载spring配置文件完成-----------");
                // 使用xml配置覆盖配置文件部分属性
                SAXReader sax = new SAXReader();
                Document docs;
                docs = sax.read(XML_FILE);
                Element root = docs.getRootElement();
                prop.setProperty("server.port", root.element("server").elementText("port"));
                prop.setProperty("spring.datasource.type",
                        root.element("spring").element("datasource").elementText("type"));
                prop.setProperty("spring.datasource.driver-class-name",
                        root.element("spring").element("datasource").elementText("driver-class-name"));
                prop.setProperty("spring.datasource.maxActive",
                        root.element("spring").element("datasource").elementText("maxActive"));
                prop.setProperty("spring.datasource.initialSize",
                        root.element("spring").element("datasource").elementText("initialSize"));
                prop.setProperty("spring.datasource.minIdle",
                        root.element("spring").element("datasource").elementText("minIdle"));
                prop.setProperty("spring.datasource.url",
                        root.element("spring").element("datasource").elementText("url"));
                prop.setProperty("spring.datasource.username",
                        root.element("spring").element("datasource").elementText("username"));
                prop.setProperty("spring.datasource.password",
                        root.element("spring").element("datasource").elementText("password"));
                prop.setProperty("zmj.cacheip", root.element("zmj").elementText("cacheip"));
                prop.setProperty("zmj.historyip", root.element("zmj").elementText("historyip"));
                logger.info("---------改写prop配置完成-----------");
            } catch (FileNotFoundException e1) {
                logger.error(e1);
                e1.printStackTrace();
            } catch (IOException e2) {
                logger.error(e2);
                e2.printStackTrace();
            } catch (DocumentException e) {
                logger.error(e);
                e.printStackTrace();
            }
            // 输出到spring
//            if (PROP_FILE.exists()) {
//                PROP_FILE.delete();
//            }
//            try {
//                PROP_FILE.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            try {
                prop.store(new OutputStreamWriter(new FileOutputStream(PROP_FILE)), "utf-8");
                logger.info("---------输出完成----------");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            logger.info("---------CreateConfig over!------------");
        } else {
            logger.warn("---------配置文件不存在!----------");
            logger.warn("XML_FILE");
        }
    }

}
