package com.answerlyl.myfinance.service.impl;

import com.answerlyl.myfinance.common.exception.GlobalException;
import com.answerlyl.myfinance.common.result.BusinessCodeMsg;
import com.answerlyl.myfinance.common.result.CodeMsg;
import com.answerlyl.myfinance.common.utils.DateUtil;
import com.answerlyl.myfinance.entity.Finance;
import com.answerlyl.myfinance.mapper.FinanceMapper;
import com.answerlyl.myfinance.service.IFinanceService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author answerlyl
 * @since 2020-01-30
 */
@Service
public class FinanceServiceImpl extends ServiceImpl<FinanceMapper, Finance> implements IFinanceService {

    @Autowired
    private FinanceMapper financeMapper;

    @Override
    public IPage<Map> qryFinReportPage(Page page) {

        Wrapper<Finance>  wrapper = new QueryWrapper<>();

        IPage<Map> res = financeMapper.qryFinReportPage(page,wrapper);

        return res;
    }

    @Override
    public IPage<Finance> qryFinListPage(Page page,Wrapper wrapper) {

        IPage<Finance> res = financeMapper.qryFinListPage(page,wrapper);

        return res;
    }

    @Override
    public List<Map> qryDayCount(Integer finType,Integer limit,String date) {
        //如果没有值,默认查支出
        if(finType == null){
            finType = 12101;
        }
        //默认查30天的数据
        if(limit == null){
            limit = 30;
        }
        if(date == null){
            date = DateUtil.getNowDateByPattern(DateUtil.DATE_PATTERN);
        }

        Map paramMap = new HashMap();
        paramMap.put("finType",finType);
        paramMap.put("limit",limit);
        paramMap.put("date",date);
        return financeMapper.qryDayCount(paramMap);
    }

    @Override
    public Map qryDayInfo( Map params) {
        String day = DateUtil.getNowDateByPattern(DateUtil.DATE_PATTERN);
        if(params == null){
            params = new HashMap();
            params.put("day",day);
        }else{
            String paramsDay = (String)params.get("day");
            if(StringUtils.isBlank(paramsDay)){
                params.put("day",day);
            }
        }
        return financeMapper.qryDayInfo(params);
    }

    @Override
    public Map qryMonthInfo(Map params) {
        String month = DateUtil.getNowDateByPattern(DateUtil.DATE_PATTERN_NO_DAY);
        if(params == null){
            params = new HashMap();
            params.put("month",month);
        }else{
            String paramsDay = (String)params.get("month");
            if(StringUtils.isBlank(paramsDay)){
                params.put("month",month);
            }
        }
        return financeMapper.qryMonthInfo(params);
    }

    @Override
    public List<Map> qryBelongPercent(Integer finType,String month) {
        //如果没有值,默认查支出
        if(finType == null){
            finType = 12101;
        }

        if(month == null){
            month = DateUtil.getNowDateByPattern(DateUtil.DATE_PATTERN_NO_DAY);
        }

        Map paramMap = new HashMap();
        paramMap.put("finType",finType);
        paramMap.put("month",month);
        return financeMapper.qryBelongPercent(paramMap);
    }

    @Override
    public List<Map> qrySortPercent(Integer finType, String month) {
        //如果没有值,默认查支出
        if(finType == null){
            finType = 12101;
        }
        if(month == null){
            month = DateUtil.getNowDateByPattern(DateUtil.DATE_PATTERN_NO_DAY);
        }

        Map paramMap = new HashMap();
        paramMap.put("finType",finType);
        paramMap.put("month",month);
        return financeMapper.qrySortPercent(paramMap);
    }

    @Override
    public List<Map> qrySortAmtList(Map params) {
        if(params == null){
            throw new GlobalException(BusinessCodeMsg.PARAMS_IS_NULL);
        }

        Object finType = params.get("finType");
        if(finType == null || StringUtils.isBlank(finType.toString())){
            throw new GlobalException(BusinessCodeMsg.PARAMS_IS_NULL.fillAges("finType"));
        }
        Object day = params.get("day");
        Object month = params.get("month");
        if(day == null && month == null){
            throw new GlobalException(BusinessCodeMsg.PARAMS_IS_NULL.fillAges("day,month不能同时为空"));
        }

        //如果没有值,默认查支出
//        if(finType == null){
//            finType = 12101;
//        }
//        if(month == null){
//            month = DateUtil.getNowDateByPattern(DateUtil.DATE_PATTERN_NO_DAY);
//        }
//
//        Map paramMap = new HashMap();
//        paramMap.put("finType",finType);
//        paramMap.put("month",month);
        return financeMapper.qrySortAmtList(params);
    }


    @Override
    public List<Map> qrySortDetailAmtList(Map params) {
        if(params == null){
            throw new GlobalException(BusinessCodeMsg.PARAMS_IS_NULL);
        }

        Object finType = params.get("finType");
        if(finType == null || StringUtils.isBlank(finType.toString())){
            throw new GlobalException(BusinessCodeMsg.PARAMS_IS_NULL.fillAges("finType"));
        }
        Object sort = params.get("sort");
        if(sort == null || StringUtils.isBlank(sort.toString())){
            throw new GlobalException(BusinessCodeMsg.PARAMS_IS_NULL.fillAges("sort"));
        }
        Object day = params.get("day");
        Object month = params.get("month");
        if(day == null && month == null){
            throw new GlobalException(BusinessCodeMsg.PARAMS_IS_NULL.fillAges("day,month不能同时为空"));
        }

        return financeMapper.qrySortDetailAmtList(params);
    }
}
