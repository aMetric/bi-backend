package com.coderwhs.bi.mapper;

import com.coderwhs.bi.model.entity.Chart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * @Entity com.coderwhs.bi.model.entity.Chart
 */
public interface ChartMapper extends BaseMapper<Chart> {
  List<Map<String, Object>> queryChartData(String querySql);

}




