package com.example.mq.pro;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MqProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void send(String key) {
        kafkaTemplate.send("zzero", key);
    }

}
