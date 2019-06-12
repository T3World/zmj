package com.t3w;

import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;


public class MqttReceiveCallback implements MqttCallback {

    private static Logger logger = Logger.getLogger(MqttReceiveCallback.class);

    @Override
    public void connectionLost(Throwable cause) {
        //打印异常
        cause.printStackTrace();
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.out.println("Client 接收消息主题 : " + topic);
        System.out.println("Client 接收消息Qos : " + message.getQos());
        System.out.println("Client 接收消息内容 : " + new String(message.getPayload()));


    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        logger.info("test for when");
    }

}
