package com.answerlyl.myfinance.controller;


import com.answerlyl.myfinance.common.result.Result;
import com.answerlyl.myfinance.common.utils.DateUtil;
import com.answerlyl.myfinance.entity.Notice;
import com.answerlyl.myfinance.service.INoticeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author answerlyl
 * @since 2020-02-12
 */
@Controller
@RequestMapping("/myfinance/notice")
public class NoticeController {

    @Autowired
    private INoticeService noticeService;

    @RequestMapping("/qryNoticeInfo")
    @ResponseBody
    public Result qryNoticeInfo(){

        QueryWrapper<Notice> wrapper = new QueryWrapper<>();
        String date = DateUtil.getNowDateByPattern(DateUtil.DATE_PATTERN);
        wrapper.eq("SHOW_DATE",date);

        Notice one = noticeService.getOne(wrapper);

        return Result.success(one);
    }

}

