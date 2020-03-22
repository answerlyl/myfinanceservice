package com.answerlyl.myfinance.service;

import com.answerlyl.myfinance.entity.Ikanalyzer;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author answerlyl
 * @since 2020-02-28
 */
public interface IIkanalyzerService extends IService<Ikanalyzer> {

    /**
     * 出发分词
     */
    void touchIK();
}
