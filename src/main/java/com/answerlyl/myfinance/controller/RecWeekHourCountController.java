package com.answerlyl.myfinance.controller;


import com.answerlyl.myfinance.common.result.CodeMsg;
import com.answerlyl.myfinance.common.result.Result;
import com.answerlyl.myfinance.entity.RecRecordsCount;
import com.answerlyl.myfinance.entity.RecWeekHourCount;
import com.answerlyl.myfinance.service.IRecWeekHourCountService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author answerlyl
 * @since 2020-02-15
 */
@Controller
@RequestMapping("/myfinance/recWeekHourCount")
public class RecWeekHourCountController {

    @Autowired
    private IRecWeekHourCountService recWeekHourCountService;


    @RequestMapping("/qryWeekHourCount")
    @ResponseBody
    public Result qryWeekHourCount(@RequestBody(required = false)RecWeekHourCount recWeekHourCount){

        QueryWrapper<RecWeekHourCount> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("week").orderByAsc("time");
        List<RecWeekHourCount> list = recWeekHourCountService.list(wrapper);

        return Result.success(list);
    }

    @RequestMapping("/fillWeekHourData")
    @ResponseBody
    public Result fillWeekHourData(@RequestBody(required = false)RecWeekHourCount recWeekHourCount){


        QueryWrapper<RecWeekHourCount> wrapper = new QueryWrapper<>();

        wrapper.select("week","count(1) cnt").groupBy("week");
        List<Map<String, Object>> maps = recWeekHourCountService.listMaps(wrapper);

        List<RecWeekHourCount> batchList = new ArrayList<>();
        for(Map res : maps ){
            Long cnt = (Long)res.get("cnt");
            if(cnt !=24){
                String week = (String)res.get("week");
                wrapper = new QueryWrapper<>();
                wrapper.eq("week",week).orderByAsc("time");
                List<RecWeekHourCount> list = recWeekHourCountService.list(wrapper);
                String[] ss = new String[24];
                for(int j = 0 ; j < 24 ; j++){
                    if(j < 10){
                        ss[j] = "0" + j;
                    }else{
                        ss[j] = ""+j;
                    }
                }
                Set<String > actionSet = new HashSet(Arrays.asList(ss));
                for(int i = 0 ; i < list.size() ; i++){
                    RecWeekHourCount recWeekHourCount1 = list.get(i);
                    String time = recWeekHourCount1.getTime();
                    actionSet.remove(time);
                }
                for(String hour : actionSet){
                    RecWeekHourCount temp = new RecWeekHourCount();
                    temp.setTime(hour);
                    temp.setWeek(week);
                    temp.setValue(0);
                    batchList.add(temp);
                }
            }
        }
        recWeekHourCountService.saveBatch(batchList);

        //wrapper.orderByAsc("week").orderByAsc("time");
//        List<RecWeekHourCount> list = recWeekHourCountService.list(wrapper);
//        for(int i = 0 ; i < list.size() ; i++){
//            RecWeekHourCount recWeekHourCount1 = list.get(i);
//            //recWeekHourCount1.getWeek()
//
//        }

        return Result.success(null);
    }

}

