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

    //雷达图格式
    String radarChart = "{\n" +
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
      "\"radar\": {\n" +
      "\"indicator\": [\n" +
      "{ \"name\": \"1号\", \"max\": 10 },\n" +
      "{ \"name\": \"2号\", \"max\": 20 },\n" +
      "{ \"name\": \"3号\", \"max\": 30 },\n" +
      "{ \"name\": \"4号\", \"max\": 5 },\n" +
      "{ \"name\": \"5号\", \"max\": 100 },\n" +
      "{ \"name\": \"6号\", \"max\": 600 },\n" +
      "{ \"name\": \"7号\", \"max\": 60 },\n" +
      "{ \"name\": \"8号\", \"max\": 11 },\n" +
      "{ \"name\": \"9号\", \"max\": 60 },\n" +
      "{ \"name\": \"10号\", \"max\": 200 },\n" +
      "{ \"name\": \"11号\", \"max\": 0 },\n" +
      "{ \"name\": \"12号\", \"max\": 50 },\n" +
      "{ \"name\": \"13号\", \"max\": 1000 },\n" +
      "{ \"name\": \"14号\", \"max\": 0 },\n" +
      "{ \"name\": \"15号\", \"max\": 100 }\n" +
      "]\n" +
      "},\n" +
      "\"series\": [{\n" +
      "\"name\": \"用户数\",\n" +
      "\"type\": \"radar\",\n" +
      "\"data\": [\n" +
      "{ \"value\": [10, 20, 30, 5, 100, 600, 60, 11, 60, 200, 0, 50, 1000, 0, 100], \"name\": \"用户数\" }\n" +
      "]\n" +
      "}]\n" +
      "}";

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
          content = "你是一个数据分析师和前端开发专家，接下来我会按照以下固定格式给你提供内容：\n" +
                    content +
                    "请根据这两部分内容，严格按照以下指定格式生成内容（此外不要输出任何多余的开头、结尾、注释，如果最后生成的内容中包含单引号，一定要将单引号替换成空格）\n" +
                    "请按照提供内容里的图表类型进行生成并按照模板的格式严格返回，"+
                    "另外，雷达图的json格式，请参考这个"+radarChart+
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
        }
        System.out.println("星火 AI 的预设是：-----------------------content----------------------------------"+content);
        List<SparkMessage> messages = new ArrayList<>();
        messages.add(SparkMessage.userContent(content));
        // 构造请求
        SparkRequest sparkRequest = SparkRequest.builder()
                // 消息列表
                .messages(messages)
                // 模型回答的tokens的最大长度,非必传,取值为[1,4096],默认为2048
                .maxTokens(4096)
                // 核采样阈值。用于决定结果随机性,取值越高随机性越强即相同的问题得到的不同答案的可能性越高 非必传,取值为[0,1],默认为0.5
                .temperature(0.2)
                // 指定请求版本
                .apiVersion(SparkApiVersion.V4_0)
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

