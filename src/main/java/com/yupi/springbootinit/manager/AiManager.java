package com.yupi.springbootinit.manager;

import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.exception.ThrowUtils;
import com.yupi.yucongming.dev.client.YuCongMingClient;
import com.yupi.yucongming.dev.common.BaseResponse;
import com.yupi.yucongming.dev.model.DevChatRequest;
import com.yupi.yucongming.dev.model.DevChatResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author whs
 * @Date 2024/8/13 21:51
 * @description:
 */
@Service
public class AiManager {

  @Resource
  private YuCongMingClient yuCongMingClient;

  public String doChat(long biModelId,String message){
    // 构造请求
    DevChatRequest devChatRequest = new DevChatRequest();
    devChatRequest.setModelId(biModelId);
    // devChatRequest.setModelId(1651468516836098050L);
    devChatRequest.setMessage(message);

    // 获取响应
    BaseResponse<DevChatResponse> response = yuCongMingClient.doChat(devChatRequest);
    ThrowUtils.throwIf(response.getData()==null, ErrorCode.SYSTEM_ERROR);
    return response.getData().getContent();
  }

}
