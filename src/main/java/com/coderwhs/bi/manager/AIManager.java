package com.coderwhs.bi.manager;

import com.coderwhs.bi.constant.ChartExampleJson;
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

    String resTemplate = "根据数据分析可得，该网站用户数量在观察期内波动较大，其中第13天达到峰值1000人，而第11天和第14天用户数为0，显示出明显的用户活跃度波动。";


    /**
     * 向 AI 发送请求
     *
     * @param isNeedTemplate 是否使用模板，进行 AI 生成； true 使用 、false 不使用
     * @param content  用户要求生成的内容
     * @return AI 返回的内容
     * '#########'
     * <p>
     * '#########'
     */
    public String sendMsgToXingHuo(boolean isNeedTemplate, String content) {
        if (isNeedTemplate) {
            // AI 生成问题的预设条件
          content = "你是一个数据分析师和前端开发专家，接下来我会按照以下固定格式给你提供内容：" +"\n"
                    + content
                    + "请根据以上内容，严格按照如下要求生成内容" +"\n"
                    + "下面是一个具体案例的返回结果模板供参考，一共由四部分组成，首先是#########，然后是json具体内容，再是#########，最后是结论具体内容"+"\n"
                    + "#########" +"\n"
                    + getExampleJson(content)
                    + "#########" +"\n"
                    + "结论："+ resTemplate +"\n"
                    + "生成结果的格式要求如下：" +"\n"
                    + "1.如果最后生成的内容中包含单引号，一定要将单引号替换成空格"+"\n"
                    + "2.请按照用户要求生成对应图标类型的结果"+"\n"
                    + "3.不要输出任何多余的开头、结尾、注释"+"\n"
                    + "4.生成的json代码需要符合前端 Echarts V5 的 option 配置对象json代码，json代码的键值对都一定要加上英文双引号，跟下面的参考一样"+"\n"
                    + "5.生成的结论尽可能详细"+"\n"
                    + "6.生成结果一定要包含两个'#########'"+"\n"
          ;
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

    /**
     * 获取参考json格式
     * @param content
     * @return
     */
    private String getExampleJson(String content){
        String exampleJson = "";
        if (content.contains("折线图")){
            exampleJson = ChartExampleJson.lineChartJson;
        }else if (content.contains("柱状图")){
            exampleJson = ChartExampleJson.histogramChartJson;
        }else if (content.contains("堆叠图")){
            exampleJson = ChartExampleJson.stackedDiagramsChartJson;
        }else if (content.contains("饼图")){
            exampleJson = ChartExampleJson.pieChartJson;
        }else if (content.contains("雷达图")){
            exampleJson = ChartExampleJson.radarChartJson;
        }else{
            exampleJson = ChartExampleJson.defaultJson;
        }
        return exampleJson;
    }
}

