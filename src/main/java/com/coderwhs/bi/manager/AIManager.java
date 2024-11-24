package com.coderwhs.bi.manager;

import io.github.briqt.spark4j.SparkClient;
import io.github.briqt.spark4j.constant.SparkApiVersion;
import io.github.briqt.spark4j.model.SparkMessage;
import io.github.briqt.spark4j.model.SparkSyncChatResponse;
import io.github.briqt.spark4j.model.request.SparkRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AIManager {

    @Resource
    private SparkClient sparkClient;

    String jsonData = "{\n" +
      "\"title\": {\n" +
      "\"text\": \"网站用户增长情况\",\n" +
      "\"subtext\": \"\"\n" +
      "},\n" +
      "\"tooltip\": {\n" +
      "\"trigger\": \"axis\",\n" +
      "\"axisPointer\": {\n" +
      "\"type\": \"shadow\"\n" +
      "}\n" +
      "},\n" +
      "\"legend\": {\n" +
      "\"data\": [\"用户数\"]\n" +
      "},\n" +
      "\"xAxis\": {\n" +
      "\"data\": [\"1号\", \"2号\", \"3号\"]\n" +
      "},\n" +
      "\"yAxis\": {\n" +
      "\"type\": \"value\",\n" +
      "\"axisLabel\": {\n" +
      "\"formatter\": \"{value} 人\"\n" +
      "}\n" +
      "},\n" +
      "\"series\": [{\n" +
      "\"name\": \"用户数\",\n" +
      "\"type\": \"line\",\n" +
      "\"data\": [10, 20, 30]\n" +
      "}]\n" +
      "}";

    String resTemplate = "根据数据分析可得，该网站用户数量逐日增长，时间越长，用户数量增长越多。";


    /**
     * 向 AI 发送请求
     *
     * @param isNeedTemplate 是否使用模板，进行 AI 生成； true 使用 、false 不使用 ，false 的情况是只想用 AI 不只是生成前端代码
     * @param content        内容
     *                       分析需求：
     *                       分析网站用户的增长情况
     *                       原始数据：
     *                       日期,用户数
     *                       1号,10
     *                       2号,20
     *                       3号,30
     * @return AI 返回的内容
     * '#########'
     * <p>
     * '#########'
     */
    public String sendMsgToXingHuo(boolean isNeedTemplate, String content) {
        if (isNeedTemplate) {
            // AI 生成问题的预设条件
            String predefinedInformation = "你是一个数据分析师和前端开发专家，接下来我会按照以下固定格式给你提供内容：\n" +
                    "分析需求：\n" +
                    "{数据分析的需求或者目标}\n" +
                    "原始数据：\n" +
                    "{csv格式的原始数据，用,作为分隔符}\n" +
                    "请根据这两部分内容，严格按照以下指定格式生成内容（此外不要输出任何多余的开头、结尾、注释，如果最后生成的内容中包含单引号，一定要将单引号替换成空格）\n" +
                    "'#########'\n" +
                    "{前端 Echarts V5 的 option 配置对象json代码，json代码的键值对都一定要加上英文双引号，跟下面的示例一样。合理地将数据进行可视化，不要生成任何多余的内容，比如注释}\n" +
                    "'#########'\n" +
                    "{明确的数据分析结论、越详细越好，不要生成多余的注释} \n"
                    + "下面是一个具体的例子的模板(严格注意：这个例子只包含三部分内容，如果最后生成的内容中包含单引号，一定要将单引号替换成空格)，本例是折线图的案例，请按照用户要求生成对应图标类型的结果："
                    + "#########\n"
                    + jsonData
                    + "#########\n" +
                    "结论："
                    + resTemplate;
            content = predefinedInformation + "\n" + content;
        }
        List<SparkMessage> messages = new ArrayList<>();
        messages.add(SparkMessage.userContent(content));
        // 构造请求
        SparkRequest sparkRequest = SparkRequest.builder()
                // 消息列表
                .messages(messages)
                // 模型回答的tokens的最大长度,非必传,取值为[1,4096],默认为2048
                .maxTokens(2048)
                // 核采样阈值。用于决定结果随机性,取值越高随机性越强即相同的问题得到的不同答案的可能性越高 非必传,取值为[0,1],默认为0.5
                .temperature(0.2)
                // 指定请求版本，默认使用最新2.0版本
                .apiVersion(SparkApiVersion.V4_0)
                // .apiVersion(SparkApiVersion.V3_5)
                .build();
        // 同步调用
        SparkSyncChatResponse chatResponse = sparkClient.chatSync(sparkRequest);
        String responseContent = chatResponse.getContent();
        System.out.println("星火 AI 返回的结果：-----------------------start----------------------------------");
        log.info(responseContent.replace("'",""));
        System.out.println("星火 AI 返回的结果：------------------------end-----------------------------------");
        return responseContent;
    }
}

