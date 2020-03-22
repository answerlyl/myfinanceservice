package com.answerlyl.myfinance.controller;


import com.answerlyl.myfinance.common.result.Result;
import com.answerlyl.myfinance.entity.RecMonthCount;
import com.answerlyl.myfinance.service.IRecMonthCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author answerlyl
 * @since 2020-02-15
 */
@Controller
@RequestMapping("/myfinance/recMonthCount")
public class RecMonthCountController {

    @Autowired
    private IRecMonthCountService recMonthCountService;

    @RequestMapping("/qryRecMonthCount")
    @ResponseBody
    public Result qryRecMonthCount(@RequestBody(required = false) RecMonthCount recMonthCount){


        List<RecMonthCount> list = recMonthCountService.list();

        return Result.success(list);
    }

}

