package org.springrabbitmq.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springrabbitmq.configuration.MessagingConfig;
import org.springrabbitmq.domain.Order;
import org.springrabbitmq.domain.OrderStatus;
import org.springrabbitmq.domain.Status;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderPublisher {
    @Autowired
    private RabbitTemplate template;

    @PostMapping("/{restaurantName}")
    public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName)
    {
        order.setOrderId(UUID.randomUUID().toString());
        //others microservices like restaurantService and paymentService
        OrderStatus orderStatus = new OrderStatus(order, Status.progess , "order placed succesfully in " + restaurantName);
        template.convertAndSend(MessagingConfig.MQ_QEXCHANGE, MessagingConfig.MQ_ROUTINGKEY, orderStatus);
        return "Success !! ";
    }


}
