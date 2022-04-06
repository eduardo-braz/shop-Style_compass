package com.compass.ms.config;

import com.compass.ms.config.utils.QueueNames;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

import static com.compass.ms.config.utils.QueueNames.*;

@Component
public class RMQConfig {

    @Autowired
    private AmqpAdmin amqpAdmin;

    private Queue queue(String queueName){
        return new Queue(queueName, true, false, false);
    }

    private DirectExchange directExchange(){
        return new DirectExchange(CUSTOMER_QUEUE);
    }

    private Binding binding(Queue queue, DirectExchange directExchange){
       // return new Binding(queue.getName(), Binding.DestinationType.QUEUE,directExchange.getName(), queue.getName(), null);
       return BindingBuilder.bind(queue).to(directExchange).with(CUSTOMER_ROUTER_KEY);
    }


}
