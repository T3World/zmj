package com.example.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mortbay.util.ajax.JSON;

import java.io.IOException;

public class HbaseController {

    public static Configuration conf;
    public static Connection connection;
    public static Admin admin;
    public static Table table;

    @Before
    public void configuration() {
        conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.property.clientPort","2181");
        conf.set("hbase.zookeeper.quorum","192.168.238.130");
        conf.set("hbase.master","192.168.238.130:16000");
        try {
            connection = ConnectionFactory.createConnection(conf);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("connect failure!");
        }
        try {
            admin = connection.getAdmin();
            table = connection.getTable(TableName.valueOf("table1"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void close(){
        try {
            connection.close();
        } catch (IOException e) {
            connection = null;
            e.printStackTrace();
        }
    }

    /**
     * 创建表
     * */
    @Test
    public void createTcdable() throws IOException {

        TableName table1 = TableName.valueOf("table2");

        //表描述构造器
        TableDescriptorBuilder table1Builder = TableDescriptorBuilder.newBuilder(table1);

        //列族描述器构造器
        ColumnFamilyDescriptorBuilder columnFamily1Builder = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes("columnFamily1"));

        //获得列族描述器
        ColumnFamilyDescriptor cfd1 = columnFamily1Builder.build();

        //添加列族
        table1Builder.setColumnFamily(cfd1);

        //获得表描述器
        TableDescriptor t1b = table1Builder.build();

        //创建表
        admin.createTable(t1b);
    }
    /**
     * 插入数据
     * */

    @Test
    public void put() throws IOException {
        byte[] rowKey = new byte[]{-1,-2,-3,-4,0,1,2,3};
//        byte[] rowKey = Bytes.toBytes("row1");
        Put p1 = new Put(rowKey);

        p1.addColumn("columnFamily1".getBytes(),"c15".getBytes(),new byte[]{-1,-128,-11,0,1,2,3,4,5,6,7,8,9,10});
//        p1.addColumn("columnFamily1".getBytes(),"c2".getBytes(),"v60".getBytes());

        table.put(p1);
    }

    /**
     * 读取数据
     * */
    @Test
    public void get() throws IOException {

//        byte[] rowKey = Bytes.toBytes("row1");
        byte[] rowKey = new byte[]{-1,-2,-3,-4,0,1,2,3};

        Table table1 = connection.getTable(TableName.valueOf("table1"));
        Get get = new Get(rowKey);
//        get.addFamily(Bytes.toBytes("columnFamily1"));
        get.addColumn(Bytes.toBytes("columnFamily1"),Bytes.toBytes("c15"));
        Result result = table1.get(get);
        byte[] value = result.getValue(Bytes.toBytes("columnFamily1"), Bytes.toBytes("c15"));

        System.out.println(JSON.toString(value));
    }


    @Test
    public void testPut(){
        long s = System.currentTimeMillis();
        for (int i = 0;i<100;i++){

        }
        new Thread(new PutSomething(table, Bytes.toBytes("c1"))).start();
        new Thread(new PutSomething(table, Bytes.toBytes("c2"))).start();
        new Thread(new PutSomething(table, Bytes.toBytes("c3"))).start();
        new Thread(new PutSomething(table, Bytes.toBytes("c4"))).start();
        new Thread(new PutSomething(table, Bytes.toBytes("c5"))).start();
        new Thread(new PutSomething(table, Bytes.toBytes("c6"))).start();
        new Thread(new PutSomething(table, Bytes.toBytes("c7"))).start();
        new Thread(new PutSomething(table, Bytes.toBytes("c8"))).start();
        new Thread(new PutSomething(table, Bytes.toBytes("c9"))).start();
        new Thread(new PutSomething(table, Bytes.toBytes("c10"))).start();
        synchronized (this){
            try {
                this.wait(10000L);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        long e = System.currentTimeMillis();
        System.out.println("testPut cost : "+(e-s)+" ms");
    }

    @Test
    public void test2(){
        long s = System.currentTimeMillis();
        new PutSomething(table, Bytes.toBytes("c10")).run();
        long e = System.currentTimeMillis();
        System.out.println("test2 cost : "+(e-s)+" ms");
    }

}
