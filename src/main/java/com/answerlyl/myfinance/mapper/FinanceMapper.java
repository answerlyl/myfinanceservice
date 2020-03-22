package com.answerlyl.myfinance.mapper;

import com.answerlyl.myfinance.entity.Finance;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 描述:
 *
 * @Author liyl21
 * @Date 2020/1/30 14:21
 **/
public interface FinanceMapper extends BaseMapper<Finance> {
    /**
     * 查询账本统计分页信息
     * @param page
     * @return
     */
    IPage<Map> qryFinReportPage(IPage page,@Param(Constants.WRAPPER) Wrapper<Finance> queryWrapper);

    /**
     * 查询账单列表
     * @param page
     * @param wrapper
     * @return
     */
    IPage<Finance> qryFinListPage(IPage page,@Param(Constants.WRAPPER) Wrapper<Finance> wrapper);

    /**
     * 查询日消费统计
     * @param params
     * @return
     */
    List<Map> qryDayCount(Map params);

    /**
     * 按日查询当天的收支情况
     * @return
     */
    Map qryDayInfo(Map params);
    /**
     * 按月查询当天的收支情况
     * @return
     */
    Map qryMonthInfo(Map params);

    /**
     * 按月和归属统计收支情况
     * @param params
     * @return
     */
    List<Map> qryBelongPercent(Map params);
    /**
     * 按月和分类统计收支情况
     * @param params
     * @return
     */
    List<Map> qrySortPercent(Map params);

    /**
     * 按日期和分类查询统计列表
     * @param params
     * @return
     */
    List<Map> qrySortAmtList(Map params);

    /**
     * 按日期和类型和分类查询明细金额列表
     * @param params
     * @return
     */
    List<Map> qrySortDetailAmtList(Map params);



}
