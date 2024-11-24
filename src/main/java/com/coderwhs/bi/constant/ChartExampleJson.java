package com.coderwhs.bi.constant;

/**
 * @Author whs
 * @Date 2024/11/24 20:25
 * @description: 各类图表参考json格式
 */
public interface ChartExampleJson {
  //折线图json格式
  String lineChartJson = "{\n" +
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
    "\"data\": [\"1号\", \"2号\", \"3号\", \"4号\", \"5号\", \"6号\", \"7号\", \"8号\", \"9号\", \"10号\", \"11号\", \"12号\", \"13号\", \"14号\", \"15号\"]\n" +
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
    "\"data\": [10, 20, 30, 5, 100, 600, 60, 11, 60, 200, 0, 50, 1000, 0, 100]\n" +
    "}]\n" +
    "}";
  //柱状图json格式
  String histogramChartJson = "{\n" +
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
    "\"data\": [\"1号\", \"2号\", \"3号\", \"4号\", \"5号\", \"6号\", \"7号\", \"8号\", \"9号\", \"10号\", \"11号\", \"12号\", \"13号\", \"14号\", \"15号\"]\n" +
    "},\n" +
    "\"yAxis\": {\n" +
    "\"type\": \"value\",\n" +
    "\"axisLabel\": {\n" +
    "\"formatter\": \"{value} 人\"\n" +
    "}\n" +
    "},\n" +
    "\"series\": [{\n" +
    "\"name\": \"用户数\",\n" +
    "\"type\": \"bar\",\n" +
    "\"data\": [10, 20, 30, 5, 100, 600, 60, 11, 60, 200, 0, 50, 1000, 0, 100]\n" +
    "}]\n" +
    "}";
  //堆叠图json格式
  String stackedDiagramsChartJson = "{  \n" +
    "  \"xAxis\": {  \n" +
    "    \"type\": \"category\",  \n" +
    "    \"data\": [\"Mon\", \"Tue\", \"Wed\", \"Thu\", \"Fri\", \"Sat\", \"Sun\"]  \n" +
    "  },  \n" +
    "  \"yAxis\": {  \n" +
    "    \"type\": \"value\"  \n" +
    "  },  \n" +
    "  \"series\": [  \n" +
    "    {  \n" +
    "      \"name\": \"系列1\",  \n" +
    "      \"type\": \"bar\",  \n" +
    "      \"stack\": \"总量\",  \n" +
    "      \"data\": [120, 132, 101, 134, 90, 230, 210]  \n" +
    "    },  \n" +
    "    {  \n" +
    "      \"name\": \"系列2\",  \n" +
    "      \"type\": \"bar\",  \n" +
    "      \"stack\": \"总量\",  \n" +
    "      \"data\": [220, 182, 191, 234, 290, 330, 310]  \n" +
    "    },  \n" +
    "    {  \n" +
    "      \"name\": \"系列3\",  \n" +
    "      \"type\": \"bar\",  \n" +
    "      \"stack\": \"总量\",  \n" +
    "      \"data\": [150, 232, 201, 154, 190, 330, 410]  \n" +
    "    }  \n" +
    "  ]  \n" +
    "}";
  //饼图json格式
  String pieChartJson = "\n" +
    "{\n" +
    "\"title\": {\n" +
    "\"text\": \"网站用户增长情况\",\n" +
    "\"subtext\": \"\"\n" +
    "},\n" +
    "\"tooltip\": {\n" +
    "\"trigger\": \"item\",\n" +
    "\"formatter\": \"{a} <br/>{b} : {c} ({d}%)\"\n" +
    "},\n" +
    "\"legend\": {\n" +
    "\"orient\": \"vertical\",\n" +
    "\"left\": \"left\",\n" +
    "\"data\": [\"用户数\"]\n" +
    "},\n" +
    "\"series\": [{\n" +
    "\"name\": \"用户数\",\n" +
    "\"type\": \"pie\",\n" +
    "\"radius\": \"50%\",\n" +
    "\"data\": [\n" +
    "{\"value\": 10, \"name\": \"1号\"},\n" +
    "{\"value\": 20, \"name\": \"2号\"},\n" +
    "{\"value\": 30, \"name\": \"3号\"},\n" +
    "{\"value\": 5, \"name\": \"4号\"},\n" +
    "{\"value\": 100, \"name\": \"5号\"},\n" +
    "{\"value\": 600, \"name\": \"6号\"},\n" +
    "{\"value\": 60, \"name\": \"7号\"},\n" +
    "{\"value\": 11, \"name\": \"8号\"},\n" +
    "{\"value\": 60, \"name\": \"9号\"},\n" +
    "{\"value\": 200, \"name\": \"10号\"},\n" +
    "{\"value\": 0, \"name\": \"11号\"},\n" +
    "{\"value\": 50, \"name\": \"12号\"},\n" +
    "{\"value\": 1000, \"name\": \"13号\"},\n" +
    "{\"value\": 0, \"name\": \"14号\"},\n" +
    "{\"value\": 100, \"name\": \"15号\"}\n" +
    "],\n" +
    "\"emphasis\": {\n" +
    "\"itemStyle\": {\n" +
    "\"shadowBlur\": 10,\n" +
    "\"shadowOffsetX\": 0,\n" +
    "\"shadowColor\": \"rgba(0, 0, 0, 0.5)\"\n" +
    "}\n" +
    "}\n" +
    "}]\n" +
    "}\n";

  //雷达图json格式
  String radarChartJson = "{\n" +
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

  String defaultJson = "{\n" +
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
}
