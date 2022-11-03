package com.example.vantazii.messgingQueues;

import com.example.vantazii.core.config.MessgingQueueConfig;
import com.example.vantazii.gamble.GambleService;
import com.example.vantazii.match.MatchService;
import com.example.vantazii.messgingQueues.listener.CustomerGambleListener;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
@AllArgsConstructor
public class MessgingConfig {
    private MessgingQueueConfig messgingQueueConfig;
    @Bean
    Queue queue(){
        return new Queue(messgingQueueConfig.getQuqueName());
    }

    @Bean
    DirectExchange directExchange(){
        DirectExchange directExchange = new DirectExchange(messgingQueueConfig.getQuqueExchange());
        directExchange.setDelayed(true);
        return  directExchange;
    }

    @Bean
    Binding binding(Queue queue,DirectExchange directExchange){
        return BindingBuilder.bind(queue).to(directExchange).with(messgingQueueConfig.getQuqueRouting());
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setExchange(messgingQueueConfig.getQuqueExchange());
        rabbitTemplate.setReplyAddress(messgingQueueConfig.getQuqueName());
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory , GambleService gambleService, MatchService matchService) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueues(queue());
        simpleMessageListenerContainer.setMessageListener(new CustomerGambleListener(messageConverter(),gambleService,matchService));
        return simpleMessageListenerContainer;
    }
}
