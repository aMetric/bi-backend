package com.yupi.springbootinit.model.dto.chart;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author whs
 * @Date 2024/8/5 22:48
 * @description:
 */
@Data
public class GenChatByAiRequest implements Serializable {
  /**
   * 名称
   */
  private String name;

  /**
   * 分析目标
   */
  private String goal;

  /**
   * 图标类型
   */
  private String chartType;

  private static final long serialVersionUID = 1L;
}
