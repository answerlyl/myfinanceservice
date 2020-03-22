package com.answerlyl.myfinance.controller;


import com.answerlyl.myfinance.common.annotation.PassToken;
import com.answerlyl.myfinance.common.result.CodeMsg;
import com.answerlyl.myfinance.common.result.Result;
import com.answerlyl.myfinance.common.utils.DateUtil;
import com.answerlyl.myfinance.entity.Notice;
import com.answerlyl.myfinance.service.IIkanalyzerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author answerlyl
 * @since 2020-02-28
 */
@Controller
@RequestMapping("/myfinance/ikanalyzer")
public class IkanalyzerController {

    @Autowired
    private IIkanalyzerService ikanalyzerService;


    @PassToken
    @RequestMapping("/touchIK")
    @ResponseBody
    public ResponseEntity<CodeMsg> touchIK(){

        ikanalyzerService.touchIK();

        return ResponseEntity.ok(CodeMsg.SUCCESS);
    }

}

