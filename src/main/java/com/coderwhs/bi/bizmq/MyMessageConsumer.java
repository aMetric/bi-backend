package com.coderwhs.bi.bizmq;

import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * @Author whs
 * @Date 2024/9/13 21:35
 * @description:
 */
// @Component
@Slf4j
public class MyMessageConsumer {

  @SneakyThrows
  @RabbitListener(queues = {"code_queue"},ackMode = "MANUAL")
  public void receiveMsg(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag){
    log.info("receiveMsg message = {}",message);
    channel.basicAck(deliveryTag,false);
  }
}
