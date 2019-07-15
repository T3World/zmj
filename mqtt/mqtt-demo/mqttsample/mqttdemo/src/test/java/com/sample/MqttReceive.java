package com.sample;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.junit.After;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description 订阅主题demo,请运行main方法,不要运行aTest();
 * @author umr
 * @date 2019/6/17
 */
public class MqttReceive {
    //mqtt服务器地址
    private static String HOST = "tcp://115.28.91.111:1884";
    //订阅的主题
    private static String TOPIC = "zaokuang/fucunmeidian/3s605/#";
    //    private static String TOPIC = "#";
    //订阅的多个主题
//    private static String[] TOPICS = new String[]{"#"};
    //消息质量
    private static int qos = 1;
    //多个主题对应的消息质量
//    private static int[] qoss = new int[]{1};
    //客户端ID,不能出现重复
    private static String clientId = "receive1";
    //用户名
    private static String userName = "super";
    //密码
    private static String password = "super";
    private MqttClient client;

    @Test
    public void aTest(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        try {
            // 1.创建客户端
            client = new MqttClient(HOST, clientId, new MemoryPersistence());
            // 2.Mqtt的连接设置
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            options.setUserName(userName);
            options.setPassword(password.toCharArray());
            //超时时间,单位s,默认30s,0代表永不超时
            options.setConnectionTimeout(10);
            //消息与消息之间的等待时间,超过时间会断开连接,0代表禁用keepalive
            //大约就是心跳检测
            options.setKeepAliveInterval(60);
            // 3.设置回调函数
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable throwable) {
                    System.out.println("connectionLost");
                    throwable.printStackTrace();
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    System.out.println("topic:" + topic);
                    System.out.println("Qos:" + message.getQos());
                    System.out.println("message content:" + new String(message.getPayload()));
                    System.out.println("thread id : " + Thread.currentThread().getName());
//                    Thread.sleep(500);
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    System.out.println("deliveryComplete---------" + token.isComplete());
                }
            });
            //设置等待时间,毫秒
            client.setTimeToWait(3000);
            // 4.建立连接
            client.connect(options);
            System.out.println("连接成功");
            // 5.订阅消息
            client.subscribe(TOPIC);
            System.out.println("订阅成功");
        } catch (Exception e) {
            try {
                client.disconnect();
                client.close();
            } catch (MqttException ee) {
                ee.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        new MqttReceive().aTest();
    }

    @After
    public void closeConnection(){
        try {
            client.disconnect();
            client.close();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


}
