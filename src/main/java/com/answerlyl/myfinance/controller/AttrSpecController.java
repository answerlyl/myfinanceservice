package com.answerlyl.myfinance.controller;


import com.answerlyl.myfinance.common.annotation.LoginRequired;
import com.answerlyl.myfinance.common.consts.FinanceConsts;
import com.answerlyl.myfinance.common.result.Result;
import com.answerlyl.myfinance.entity.AttrSpec;
import com.answerlyl.myfinance.service.IAttrSpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 属性是事物固有的特性，是一种事物和其他事物相互联系中所表现出来的性质。由于事物的联系具有广泛性，同一事物就可以具有多方面的属性。比如ADSL的速率属性，数字电路的通讯协议、通达范围
 前端控制器
 * </p>
 *
 * @author answerlyl
 * @since 2020-01-30
 */
@RestController
@RequestMapping("/myfinance/attrSpec")
public class AttrSpecController {


    @Autowired
    private IAttrSpecService attrSpecService;


    /**
     * 初始化记账本数据
     * @param attrSpec
     * @return
     */
    @LoginRequired()
    @RequestMapping(value = "/findList")
    @ResponseBody
    public Result findList(AttrSpec attrSpec){
        return Result.success(attrSpecService.qryAttrSepcInit());

    }


    /**
     * 初始化参与人数据
     * @param attrSpec
     * @return
     */
    @RequestMapping(value = "/initPartyDist")
    @ResponseBody
    public Result initPartyDist(AttrSpec attrSpec){
        return Result.success(attrSpecService.qryAttrSepcInitParty());
    }




    @RequestMapping("/qryAttrSpecByParId")
    @ResponseBody
    public Result qryAttrSpecByParId(AttrSpec params){
        BigDecimal attrId = params.getAttrId();
        int i = attrId.intValue();
        List<AttrSpec> attrSpecs = attrSpecService.qryAttrSpecByParId(i);
        return Result.success(attrSpecs);

    }

}

