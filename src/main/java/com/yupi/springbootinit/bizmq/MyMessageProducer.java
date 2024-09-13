package com.yupi.springbootinit.bizmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author whs
 * @Date 2024/9/13 21:30
 * @description:
 */
@Component
public class MyMessageProducer {

  @Resource
  private RabbitTemplate rabbitTemplate;

  public void sendMessage(String exchange,String routingKey,String message){
    rabbitTemplate.convertAndSend(exchange,routingKey,message);
  }

}
