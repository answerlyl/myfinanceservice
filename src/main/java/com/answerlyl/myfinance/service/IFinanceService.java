package com.answerlyl.myfinance.service;

import com.answerlyl.myfinance.entity.Finance;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author answerlyl
 * @since 2020-01-30
 */
public interface IFinanceService extends IService<Finance> {

    /**
     * 分页查询统计量
     * @param page
     * @return
     */
    IPage<Map> qryFinReportPage(Page page);

    /**
     * 查询账单详情
     * @param page
     * @return
     */
    IPage<Finance> qryFinListPage(Page page, Wrapper wrapper);


    /**
     * 查询日消费统计
     * @param finType
     * @return
     */
    List<Map> qryDayCount(Integer finType,Integer limit,String date);

    /**
     * 按日查询当天收支信息
     * @param params
     * @return
     */
    Map qryDayInfo(Map params);

    /**
     * 按月查询当天收支信息
     * @param params
     * @return
     */
    Map qryMonthInfo(Map params);


    /**
     * 按月,归属统计收支情况
     * @param finType
     * @param month
     * @return
     */
    List<Map> qryBelongPercent(Integer finType,String month) ;
    /**
     * 按月,分类统计收支情况
     * @param finType
     * @param month
     * @return
     */
    List<Map> qrySortPercent(Integer finType,String month) ;


    /**
     * 按日期(日,月,年)和收支类型查询金额列表
     * @param params
     * @return
     */
    List<Map> qrySortAmtList(Map params);

    /**
     * 按日期(日,月,年)和收支类型查询分类明细金额列表
     * @param params
     * @return
     */
    List<Map> qrySortDetailAmtList(Map params);

}
