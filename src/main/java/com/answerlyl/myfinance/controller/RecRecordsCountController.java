package com.answerlyl.myfinance.controller;


import com.answerlyl.myfinance.common.result.Result;
import com.answerlyl.myfinance.entity.Notice;
import com.answerlyl.myfinance.entity.RecMonthCount;
import com.answerlyl.myfinance.entity.RecRecordsCount;
import com.answerlyl.myfinance.service.IRecRecordsCountService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
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
@RequestMapping("/myfinance/recRecordsCount")
public class RecRecordsCountController {

    @Autowired
    private IRecRecordsCountService recRecordsCountService;

    @RequestMapping("/qryRecRecordsCount")
    @ResponseBody
    public Result qryRecRecordsCount(@RequestBody(required = false) RecRecordsCount recRecordsCount){

        QueryWrapper<RecRecordsCount> wrapper = new QueryWrapper<>();

        wrapper.eq("sortType",recRecordsCount.getSortType());

        List<RecRecordsCount> list = recRecordsCountService.list(wrapper);

        return Result.success(list);
    }

}

