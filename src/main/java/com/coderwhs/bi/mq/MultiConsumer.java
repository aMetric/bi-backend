package com.coderwhs.bi.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class MultiConsumer {

  private static final String TASK_QUEUE_NAME = "task_queue";

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    final Connection connection = factory.newConnection();

    for (int i = 0; i < 2; i++) {
      final Channel channel = connection.createChannel();

      channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
      System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

      //控制单个消费者的处理任务积压数，每个消费者最多同时处理一个积压任务
      channel.basicQos(1);

      int finalI = i;
      DeliverCallback deliverCallback = (consumerTag, delivery) -> {
        String message = new String(delivery.getBody(), "UTF-8");

        try {
          try {
            System.out.println(" [x] Received '"+ finalI +"::" + message + "'");
            //第二个参数：是否一次性确认所有
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
            Thread.sleep(20000);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
            //发生异常后，拒绝确认消息，并发送拒绝消息，并不重新投递该消息
          }
        } finally {
          System.out.println(" [x] Done");
          channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
      };
      //开启消息监听
      channel.basicConsume(TASK_QUEUE_NAME, false, deliverCallback, consumerTag -> { });
    }
  }
}
