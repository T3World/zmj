package com.example.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class PutSomething implements Runnable {

    private static Table table;
    private byte[] qualifier;
    private static final byte[] COLUME_FAMILY = Bytes.toBytes("columnFamily1");
    private static final byte[] ROW_KEY = Bytes.toBytes("c1");
    private static int index = 0;


    public PutSomething(Table table,byte[] qualifier) {
        this.table = table;
        this.qualifier = qualifier;
    }


    @Override
    public void run() {
        long s = System.currentTimeMillis();
        putSingle();
//        putMuch();
        long e = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName()+" cost "+(e-s)+" ms");
    }


    private void putSingle(){
        for (int i = 0;i<1000;i++) {
            Put p1 = new Put(ROW_KEY);
            p1.addColumn(COLUME_FAMILY, qualifier, ("value" + index).getBytes());
            index++;
            try {
                table.put(p1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void putMuch(){
        Put p1 = new Put(ROW_KEY);

        for (int i =0;i<1000;i++){
            p1.addColumn(COLUME_FAMILY,qualifier,("value"+index).getBytes());
            index++;
        }
        try {
            table.put(p1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
