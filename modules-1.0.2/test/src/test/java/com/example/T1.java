package com.example;

import com.zmj.microservice.common.history.util.DbUtil;
import org.apache.commons.net.util.Base64;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.mortbay.util.ajax.JSON;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class T1 {
    private static Logger logger = Logger.getLogger("T1");
    @Test
    public void time(){
        long now = System.currentTimeMillis();
        logger.info(now);
        for(int i = 0;i<0;i++){
          System.out.println("走不走!");
        }
    }

    @Test
    public void getTimeMillis() throws ParseException {
        String yyyyMMdd = "2019/05/06";
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date parse = format.parse(yyyyMMdd);
        System.out.println("timeMillis: "+parse.getTime());
        Date date = new Date(1562150293805L);
        String format1 = format.format(date);
        logger.info(format1);

    }

    @Test
    public void testsomething(){
        System.out.println("lalala: "+(0 == 0.0));


    }
    @Test
    public void testLog(){
        logger.info("test!!!!!!!!!!!!!!!");
        long 龙 = 9999999999999L;
        byte[] bytes = Bytes.toBytes(龙);
        logger.info(JSON.toString(bytes));
        logger.info(JSON.toString(Bytes.toLong(new byte[]{0,0,127,127,127,127,127,127})));
        logger.info(JSON.toString(Bytes.toBytes(System.currentTimeMillis())));

    }

    @Test
    public void testBase64(){
        String base64String="03,70,40,10,10,11,02,00,09,71,55,56,89,60,00,00";
        char b1 = 0;
        System.out.println(b1);
        byte[] result = Base64.encodeBase64(base64String.getBytes());
        System.out.println(JSON.toString(result));
        System.out.println(result.length);
        System.out.println(base64String.length());
    }


    @Test
    public void testFile(){
        String path = "E:\\ZMJ_JAVA\\document\\test1.xls";
        File file = new File(path);

        if (!file.exists()) {
            try {
                boolean newFile = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
