package com.t3w;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.junit.After;
import org.junit.Test;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
public class ServerMQTT {

    //tcp://MQTT安装的服务器地址:MQTT定义的端口号
    public static final String HOST = "tcp://127.0.0.1:1884";
    //定义一个主题
    public static final String TOPIC = "pos_message_all";
    //定义MQTT的ID，可以在MQTT服务配置中指定
    private static final String clientid = "server01";

    private MqttClient client;
    private MqttTopic topic11;
    private String userName = "super";  //非必须
    private String passWord = "super";  //非必须
    /**
     *  启动入口
     */
    @Test
    public  void testMain() {
        try {
            client = new MqttClient(HOST, clientid, new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            options.setUserName(userName);
            options.setPassword(passWord.toCharArray());
            // 设置超时时间
            options.setConnectionTimeout(10);
            // 设置会话心跳时间
//            options.setKeepAliveInterval(20);
//            options.setMaxInflight(10000);

//            client.setTimeToWait(3000);
            client.setCallback(new MqttCallback() {
                int index = 0;
                @Override
                public void connectionLost(Throwable cause) {
                    cause.printStackTrace();
                    System.out.println("connection lost!");
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    System.out.println("topic:" + topic);
                    System.out.println("Qos:" + message.getQos());
                    System.out.println("message content:" + new String(message.getPayload()));
                    System.out.println("thread id : " + Thread.currentThread().getName());
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    System.out.println("publish finish!"+index++);
                }
            });
            client.connect(options);
            String feihua = "这是一条废话,发送的消息序号为:";
            int index = 0;
            long t = System.currentTimeMillis();
            for (int i = 0;i<10000;i++,index++){
                MqttMessage message = new MqttMessage();
                long t0 = System.currentTimeMillis();
                message.setQos(1);
                message.setPayload((feihua+index).getBytes());
                message.setRetained(false);

                client.publish(TOPIC,message);
                long t1 = System.currentTimeMillis();
//                System.out.println("publish cost :"+(t1-t0));
//                Thread.sleep(3);
            }
            System.out.println("**************  publish finish all **************");
            long t2 = System.currentTimeMillis();
            System.out.println("publish all cost : "+(t2-t));
            if (client.isConnected()){
                client.disconnect();
            }
            client.close();
        }catch (Exception e){
            if (client.isConnected()){
                try {
                    client.disconnect();
                } catch (MqttException e1) {
                    e1.printStackTrace();
                }
            }
            try {
                client.close();
            } catch (MqttException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    @After
    public void closeClient(){
        if (client!=null){
            if (client.isConnected()){
                try {
                    client.disconnect();
                } catch (MqttException e1) {
                    e1.printStackTrace();
                }
            }
            try {
                client.close();
            } catch (MqttException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Test
    public void sha256() throws NoSuchAlgorithmException {

        byte[] password = "3057".getBytes();
        MessageDigest instance = MessageDigest.getInstance("SHA-256");
        byte[] digest = instance.digest(password);
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i=0;i<digest.length;i++){
            temp = Integer.toHexString(digest[i] & 0xFF);
            if (temp.length()==1){
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        String s = stringBuffer.toString();
        System.out.println(Arrays.toString(digest));
        System.out.println(s);
    }

}

