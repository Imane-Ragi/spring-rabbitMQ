package org.springrabbitmq.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    public static final String MQ_QUEUE = "mq_queue";
    public static final String MQ_QEXCHANGE = "mq_qexchange";
    public static final String MQ_ROUTINGKEY = "mq_routingkey";

    @Bean
    public Queue queue()
    {
        return new Queue(MQ_QUEUE);
    }
    @Bean
    public TopicExchange exchange()
    {
        return new TopicExchange(MQ_QEXCHANGE);
    }
    @Bean
    public Binding binding(Queue queue , TopicExchange exchange)
    {
    return BindingBuilder.bind(queue).to(exchange).with(MQ_ROUTINGKEY);
    }
    @Bean
    public MessageConverter converter()
    {
        return  new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory)
    {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate( connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
