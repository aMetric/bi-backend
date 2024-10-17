package com.coderwhs.bi.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author whs
 * @Date 2024/9/5 22:09
 * @description: 自定义线程池工厂
 */
@Configuration
public class ThreadPoolExecutorConfig {

  @Bean
  public ThreadPoolExecutor threadPoolExecutor(){
    ThreadFactory threadFactory = new ThreadFactory() {
      private int count = 1;
      @Override
      public Thread newThread(@NotNull Runnable r) {
        Thread thread = new Thread(r);
        thread.setName("线程"+count);
        count++;
        return thread;
      }
    };


    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
      Runtime.getRuntime().availableProcessors(),
      Runtime.getRuntime().availableProcessors()*2+1,
      100,
      TimeUnit.SECONDS,
      new ArrayBlockingQueue<>(4),
      threadFactory);
    return threadPoolExecutor;
  }

}
