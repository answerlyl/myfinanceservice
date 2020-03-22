package com.answerlyl.myfinance.service;

import com.answerlyl.myfinance.entity.AttrSpec;
import com.baomidou.mybatisplus.extension.service.IService;
import org.w3c.dom.Attr;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 属性是事物固有的特性，是一种事物和其他事物相互联系中所表现出来的性质。由于事物的联系具有广泛性，同一事物就可以具有多方面的属性。比如ADSL的速率属性，数字电路的通讯协议、通达范围
 服务类
 * </p>
 *
 * @author answerlyl
 * @since 2020-01-30
 */
public interface IAttrSpecService extends IService<AttrSpec> {

    List<AttrSpec> qryAttrSpecByParId(Integer parId);

    Map<String,List<AttrSpec>> qryAttrSepcInit();

    /**
     * 初始化参与人数据
     * @return
     */
    Map<String,List<AttrSpec>> qryAttrSepcInitParty();
}
