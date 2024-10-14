package com.coderwhs.bi.bizmq;

import com.coderwhs.bi.common.ErrorCode;
import com.coderwhs.bi.constant.BiMqConstant;
import com.coderwhs.bi.exception.BusinessException;
import com.coderwhs.bi.exception.ThrowUtils;
import com.coderwhs.bi.manager.AI2Manager;
import com.coderwhs.bi.model.entity.Chart;
import com.coderwhs.bi.service.ChartService;
import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author whs
 * @Date 2024/9/13 21:35
 * @description:
 */
// @Component
@Slf4j
public class BiMessageConsumer {

  @Resource
  private ChartService chartService;

  // @Resource
  // private AiManager aiManager;

  @Resource
  private AI2Manager aiManager;

  @SneakyThrows
  @RabbitListener(queues = {BiMqConstant.BI_QUEUE_NAME},ackMode = "MANUAL")
  public void receiveMsg(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag){

    if(StringUtils.isBlank(message)){
      //消息拒绝
      channel.basicNack(deliveryTag,false,false);
      throw new BusinessException(ErrorCode.SYSTEM_ERROR,"消息为空");
    }

    long chartId = Long.parseLong(message);
    Chart chart = chartService.getById(chartId);
    if(ObjectUtils.isEmpty(chart)){
      channel.basicNack(deliveryTag,false,false);
      throw new BusinessException(ErrorCode.SYSTEM_ERROR,"图表为空");
    }

    //先修改图标状态为"执行中"，等执行成功之后修改为"已完成"，保存执行结果；执行失败修改为"失败",记录失败信息
    Chart updateChart = new Chart();
    updateChart.setId(chart.getId());
    updateChart.setStatus("running");
    boolean updated = chartService.updateById(updateChart);
    if(!updated){
      handleChartUpdateError(chart.getId(), "更新图表执行中状态失败");
      return;
    }

    String result = aiManager.sendMsgToXingHuo(true, buildUserInput(chart));
    String[] splits = result.replace("'","").split("#########");
    if (splits.length < 3) {
      channel.basicNack(deliveryTag,false,false);
      handleChartUpdateError(chart.getId(), "AI 生成错误");
      return;
    }
    String genChart = splits[1].trim();
    String genResult = splits[2].trim();

    Chart updateChartResult = new Chart();
    updateChartResult.setId(chart.getId());
    updateChartResult.setStatus("succeed");
    updateChartResult.setGenChart(genChart);
    updateChartResult.setGenResult(genResult);
    boolean resUpdated = chartService.updateById(updateChartResult);
    if(!resUpdated){
      channel.basicNack(deliveryTag,false,false);
      handleChartUpdateError(chart.getId(), "更新图表成功状态失败");
    }

    //消息确认
    log.info("receiveMsg message = {}",message);
    channel.basicAck(deliveryTag,false);
  }

  private String buildUserInput(Chart chart){
     String goal = chart.getGoal();
     String csvData = chart.getChartData();
     String chartType = chart.getChartType();

    // 拼接分析目标
    String userGoal = goal;
    if (StringUtils.isNotBlank(chartType)) {
      userGoal += "，请使用" + chartType;
    }
    StringBuilder userInput = new StringBuilder();
    userInput.append(userGoal).append("\n");
    userInput.append("原始数据：").append("\n");
    userInput.append(csvData).append("\n");

    return userInput.toString();
  }

  private void handleChartUpdateError(long chartId,String execMessage){
    Chart chart = new Chart();
    chart.setId(chartId);
    chart.setStatus("failed");
    chart.setExecMessage(execMessage);
    boolean updated = chartService.updateById(chart);
    ThrowUtils.throwIf(!updated, ErrorCode.OPERATION_ERROR,"更新图标失败状态失败");
  }
}
