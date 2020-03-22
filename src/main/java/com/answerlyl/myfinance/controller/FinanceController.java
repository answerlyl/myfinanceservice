package com.answerlyl.myfinance.controller;


import com.answerlyl.myfinance.common.consts.FinanceConsts;
import com.answerlyl.myfinance.common.exception.GlobalException;
import com.answerlyl.myfinance.common.result.BusinessCodeMsg;
import com.answerlyl.myfinance.common.result.CodeMsg;
import com.answerlyl.myfinance.common.result.Result;
import com.answerlyl.myfinance.common.utils.DateUtil;
import com.answerlyl.myfinance.entity.AttrSpec;
import com.answerlyl.myfinance.entity.Finance;
import com.answerlyl.myfinance.service.IAttrSpecService;
import com.answerlyl.myfinance.service.IFinanceService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Attr;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author answerlyl
 * @since 2020-01-30
 */
@Controller
@RequestMapping("/myfinance/finance")
public class FinanceController {

    @Autowired
    private IFinanceService financeService;

    @Autowired
    private IAttrSpecService attrSpecService;


    @RequestMapping("/save")
    @ResponseBody
    public ResponseEntity<CodeMsg> save(@RequestBody Finance finance){

        if(StringUtils.isNotBlank(finance.getFinDate()) && StringUtils.isNotBlank(finance.getFinTime())){
            String dateTime = finance.getFinDate() + " " + finance.getFinTime();
            finance.setInputDate(dateTime);
        }

        //如果数据为空，则获取系统时间
        if(StringUtils.isBlank(finance.getInputDate())){
            finance.setInputDate(DateUtil.getNowDataForStandard());
        }

        finance.setYear(finance.getInputDate().substring(0,4));
        finance.setDay(finance.getInputDate().substring(0,10));


        if(finance.getAmount() == null || finance.getAmount() <= 0){
            throw new GlobalException(BusinessCodeMsg.BUSINESS_AMOUNT_IS_NOT_NULL);
        }
        try{
            float v = Float.parseFloat(finance.getAmount().toString());
        }catch (Exception e){
            throw new GlobalException(BusinessCodeMsg.BUSINESS_AMOUNT_IS_NOT_FLOAT);
        }

        finance.setCreateDate(DateUtil.getNowDataForStandard());

        boolean save = false;
        if(finance.getFinId() != null){
            save = financeService.updateById(finance);
        }else{
            save = financeService.save(finance);
        }
        if(save){
            return ResponseEntity.ok(CodeMsg.SUCCESS);
        }else{
            throw new GlobalException(BusinessCodeMsg.SAVE_FAILD);
        }

    }

    @RequestMapping("/qryFinReport")
    @ResponseBody
    public Result qryFinReport(@RequestBody(required = false) Page page){

        if(page == null){
            page = new Page();
        }

        IPage<Map> pageFinance =  financeService.qryFinReportPage(page);


        return Result.success(pageFinance);
    }
    @RequestMapping("/qryFinList")
    @ResponseBody
    public Result qryFinList(@RequestBody(required = false) Finance fin){

//        if(StringUtils.isBlank(fin.getMonth())){
//            throw new GlobalException(BusinessCodeMsg.MONTH_IS_NULL);
//        }
        Page page = new Page();
        page.setCurrent(fin.getCurrent());
        page.setSize(fin.getSize());
        QueryWrapper<Finance> wrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(fin.getMonth())){
            wrapper.eq("MONTH",fin.getMonth());
        }
        if(StringUtils.isNotBlank(fin.getDay())){
            wrapper.eq("DAY",fin.getDay());
        }
        if(fin.getFinType() != null && BigDecimal.ZERO.compareTo(fin.getFinType()) == -1){
            wrapper.eq("FIN_TYPE",fin.getFinType());
        }
        if(fin.getSortDetail() != null && BigDecimal.ZERO.compareTo(fin.getSortDetail()) == -1){
            wrapper.eq("SORT_DETAIL",fin.getSortDetail());
        }
        wrapper.orderByDesc("INPUT_DATE");

        IPage<Finance> pageFinance =  financeService.qryFinListPage(page,wrapper);
        return Result.success(pageFinance);
    }

    @RequestMapping("/qryFinDetail")
    @ResponseBody
    public Result qryFinDetail(@RequestBody(required = false) Finance fin){
        Page page = new Page();
        QueryWrapper<Finance> wrapper = new QueryWrapper<>();
        wrapper.eq("FIN_ID",fin.getFinId());
        IPage<Finance> pageFinance =  financeService.qryFinListPage(page,wrapper);
        Finance finance = pageFinance.getRecords().get(0);

        Map<String, List<AttrSpec>> attrSepc = attrSpecService.qryAttrSepcInit();

        List<Integer> initIndex = new ArrayList<>();

        List<AttrSpec> belongList = attrSepc.get(FinanceConsts.BELONG);
        getAttrSepcIndex(initIndex,belongList,finance.getBelong(),null);

        List<AttrSpec> finTypeList = attrSepc.get(FinanceConsts.FINTYPE);
        getAttrSepcIndex(initIndex,finTypeList,finance.getFinType(),null);

        List<AttrSpec> sortList = attrSepc.get(FinanceConsts.SORT);
        getAttrSepcIndex(initIndex,sortList,finance.getSort(),finance.getSortDetail());

        Integer sortIndex = initIndex.get(2);
        Integer sortDetailIndex = initIndex.get(3);

        List<AttrSpec> resSortList = new ArrayList<>();

        if(!sortIndex.equals(0) || !sortDetailIndex.equals(0)){
            AttrSpec attrSpec = sortList.get(sortIndex);
            attrSpec.setDefaultIndex(sortDetailIndex);
            resSortList.add(attrSpec);
            sortList.remove(sortIndex);
            resSortList.addAll(sortList);
            attrSepc.put(FinanceConsts.SORT,resSortList);
        }

        Map res = new HashMap();
        res.put("finance",finance);
        res.put("attrSepc",attrSepc);
        res.put("initIndex",initIndex);

        return Result.success(res);
    }

    @RequestMapping("/qryDayCount")
    @ResponseBody
    public Result qryDayCount(@RequestBody(required = false) Map params){

        Integer finType = (Integer)params.get("finType");
        Integer limit = (Integer)params.get("limit");
        String date = (String)params.get("date");

        List<Map> maps = financeService.qryDayCount(finType, limit,date);

        return Result.success(maps);

    }

    @RequestMapping("/qryDayInfo")
    @ResponseBody
    public Result qryDayInfo(@RequestBody(required = false)Map params){
        Map res = financeService.qryDayInfo(params);
        return Result.success(res);
    }

    @RequestMapping("/qryMonthInfo")
    @ResponseBody
    public Result qryMonthInfo(@RequestBody(required = false)Map params){
        Map res = financeService.qryMonthInfo(params);
        return Result.success(res);
    }

    @RequestMapping("/qrySortPercent")
    @ResponseBody
    public Result qrySortPercent(@RequestBody(required = false) Map params){

        Integer finType = (Integer)params.get("finType");
        String month = (String)params.get("month");

        List<Map> maps = financeService.qrySortPercent(finType,month);

        return Result.success(maps);

    }
    @RequestMapping("/qryBelongPercent")
    @ResponseBody
    public Result qryBelongPercent(@RequestBody(required = false) Map params){

        Integer finType = (Integer)params.get("finType");
        String month = (String)params.get("month");

        List<Map> maps = financeService.qryBelongPercent(finType,month);

        return Result.success(maps);

    }

    @RequestMapping("/qrySortAmtList")
    @ResponseBody
    public Result qrySortAmtList(@RequestBody(required = false) Map params){

        List<Map> list = financeService.qrySortAmtList(params);
        if(list.size() > 0){
            for(int i = 0 ; i < list.size() ; i++){
                Map map = list.get(i);
                Object sort = map.get("sort");
                 params.put("sort", sort);
                List<Map> detailAmtList = financeService.qrySortDetailAmtList(params);
                if(detailAmtList.size() > 0){
                    map.put("detailList",detailAmtList);
                }
            }
        }

        return Result.success(list);

    }

    @RequestMapping("/qrySortDetailAmtList")
    @ResponseBody
    public Result qrySortDetailAmtList(@RequestBody(required = false) Map params){
        List<Map> maps = financeService.qrySortDetailAmtList(params);

        return Result.success(maps);

    }



    private void getAttrSepcIndex(List<Integer> initIndex,List<AttrSpec> listAttr, BigDecimal fatherId, BigDecimal childId) {
        for(int i = 0 ; i < listAttr.size() ; i ++){
            BigDecimal attrId = listAttr.get(i).getAttrId();
            if(attrId.equals(fatherId)){
                initIndex.add(i);
                listAttr.get(i).setDefaultIndex(i);
                if(childId != null){
                    List<AttrSpec> children = listAttr.get(i).getChildren();
                    for(int j = 0 ; j < children.size() ; j++){
                        BigDecimal attrId1 = children.get(j).getAttrId();
                        if(attrId1.equals(childId)){
                            children.get(j).setDefaultIndex(j);
                            initIndex.add(j);
                        }
                    }
                }
            }
        }

    }



}

