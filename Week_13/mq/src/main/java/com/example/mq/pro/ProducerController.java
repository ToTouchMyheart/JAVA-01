package com.example.mq.pro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
public class ProducerController {

    @Autowired
    MqProducer mqProducer;



    @GetMapping("send")
    public String send(String index) {
        mqProducer.send(index);
        return index;
    }

}
