package com.yupi.springbootinit.manager;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author whs
 * @Date 2024/8/13 21:58
 * @description:
 */
@SpringBootTest
class AiManagerTest {

  @Resource
  private AiManager aiManager;

  @Test
  void doChat() {
    String chat = aiManager.doChat(1651468516836098050L,"邓紫棋");
    System.out.println("chat = " + chat);
  }
}
