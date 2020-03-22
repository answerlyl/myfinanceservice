package com.answerlyl.myfinance.service.impl;

import com.answerlyl.myfinance.common.utils.IkanalyzerUtil;
import com.answerlyl.myfinance.entity.Ikanalyzer;
import com.answerlyl.myfinance.entity.Records;
import com.answerlyl.myfinance.mapper.IkanalyzerMapper;
import com.answerlyl.myfinance.mapper.RecordsMapper;
import com.answerlyl.myfinance.service.IIkanalyzerService;
import com.answerlyl.myfinance.service.IRecordsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author answerlyl
 * @since 2020-02-28
 */
@Service
public class IkanalyzerServiceImpl extends ServiceImpl<IkanalyzerMapper, Ikanalyzer> implements IIkanalyzerService {

    @Autowired
    private IRecordsService recordsService;

    @Autowired
    private RecordsMapper recordsMapper;

    @Autowired
    private IkanalyzerMapper ikanalyzerMapper;

    @Override
    public void touchIK() {
        QueryWrapper<Records> wrapper = new QueryWrapper<>();
        wrapper.select("message");
        wrapper.eq("content_type","1");
        int count = recordsService.count(wrapper);

        if(count > 0){
            Page<Records> page = new Page();
            page.setTotal(count);
            page.setSize(1000);
            long current = 0L;
            page.setCurrent(current);
            while (page.hasNext()){
                current ++ ;
                page.setCurrent(current);
                Page<Records> recordsPage = recordsMapper.selectPage(page, wrapper);

                List<Records> records = recordsPage.getRecords();
                List<Ikanalyzer> list = new ArrayList<>();
                for(int i = 0 ; i < records.size() ; i ++){
                    if(records.get(i) != null ){
                        String message = records.get(i).getMessage();
                        if(StringUtils.isNotBlank(message)){
                            IkanalyzerUtil.cutWord(message,list);
                        }
                    }
                }
                this.saveBatch(list);
            }

        }

    }
}
