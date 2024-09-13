package com.yupi.springbootinit.bizmq;

import com.yupi.springbootinit.constant.BiMqConstant;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author whs
 * @Date 2024/9/13 21:30
 * @description:
 */
@Component
public class BiMessageProducer {

  @Resource
  private RabbitTemplate rabbitTemplate;

  public void sendMessage(String message){
    rabbitTemplate.convertAndSend(BiMqConstant.BI_EXCHANGE_NAME,BiMqConstant.BI_ROUTING_KEY,message);
  }

}
