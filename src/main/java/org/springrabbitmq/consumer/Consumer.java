package org.springrabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springrabbitmq.configuration.MessagingConfig;
import org.springrabbitmq.domain.OrderStatus;

@Component
public class Consumer {

    @RabbitListener(queues = MessagingConfig.MQ_QUEUE)
    public  void consumerMessageFromQueue(OrderStatus orderStatus)
    {

        System.out.println("Message recieved from queue :"  + orderStatus);
    }
}
