package com.example.mq.con;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class MqConsumer {


    @KafkaListener(topics = {"zzero"})
    public void onMessage(String message) {
        System.out.printf("消费" + message);
    }
}
