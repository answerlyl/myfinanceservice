package com.answerlyl.myfinance.mapper;

import com.answerlyl.myfinance.entity.AttrSpec;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 属性是事物固有的特性，是一种事物和其他事物相互联系中所表现出来的性质。由于事物的联系具有广泛性，同一事物就可以具有多方面的属性。比如ADSL的速率属性，数字电路的通讯协议、通达范围
 Mapper 接口
 * </p>
 *
 * @author answerlyl
 * @since 2020-01-30
 */
public interface AttrSpecMapper extends BaseMapper<AttrSpec> {

    /**
     * 通过父id查询列表数据
     * @param parId
     * @return
     */
     List<AttrSpec> qryAttrSpecByParId(Integer parId);

}
