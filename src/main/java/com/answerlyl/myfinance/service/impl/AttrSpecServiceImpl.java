package com.answerlyl.myfinance.service.impl;

import com.answerlyl.myfinance.common.consts.FinanceConsts;
import com.answerlyl.myfinance.entity.AttrSpec;
import com.answerlyl.myfinance.mapper.AttrSpecMapper;
import com.answerlyl.myfinance.service.IAttrSpecService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 属性是事物固有的特性，是一种事物和其他事物相互联系中所表现出来的性质。由于事物的联系具有广泛性，同一事物就可以具有多方面的属性。比如ADSL的速率属性，数字电路的通讯协议、通达范围
 服务实现类
 * </p>
 *
 * @author answerlyl
 * @since 2020-01-30
 */
@Service
public class AttrSpecServiceImpl extends ServiceImpl<AttrSpecMapper, AttrSpec> implements IAttrSpecService {

    @Autowired
    private AttrSpecMapper attrSpecMapper;

    @Override
    public List<AttrSpec> qryAttrSpecByParId( Integer parId) {

        List<AttrSpec> attrSpecs = attrSpecMapper.qryAttrSpecByParId(parId);

        return attrSpecs;
    }

    @Override
    public Map<String, List<AttrSpec>> qryAttrSepcInit() {
        Map<String, Integer> initDesc = FinanceConsts.INIT_DESC;
        Map<String,List<AttrSpec>> res = new HashMap();
        qryInitDist(initDesc, res);
        List<AttrSpec> attrSpecs = res.get(FinanceConsts.SORT);
        for(AttrSpec attrTemp : attrSpecs){
            List<AttrSpec> children = this.qryAttrSpecByParId(attrTemp.getAttrId().intValue());
            attrTemp.setChildren(children);
        }

        return res;
    }

    private void qryInitDist(Map<String, Integer> initDesc, Map<String, List<AttrSpec>> res) {
        for(String key : initDesc.keySet()){
            Integer parId = initDesc.get(key);
            List<AttrSpec> attrSpecs = this.qryAttrSpecByParId(parId);
            res.put(key,attrSpecs);
        }
    }

    @Override
    public Map<String, List<AttrSpec>> qryAttrSepcInitParty() {
        Map<String, Integer> initParty = FinanceConsts.INIT_PARTY_DIST;
        Map<String,List<AttrSpec>> res = new HashMap();
        qryInitDist(initParty, res);
        return res;
    }
}
