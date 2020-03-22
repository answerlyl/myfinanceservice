package com.answerlyl.myfinance.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 属性是事物固有的特性，是一种事物和其他事物相互联系中所表现出来的性质。由于事物的联系具有广泛性，同一事物就可以具有多方面的属性。比如ADSL的速率属性，数字电路的通讯协议、通达范围

 * </p>
 *
 * @author answerlyl
 * @since 2020-01-30
 */
public class AttrSpec extends Model<AttrSpec> {

    private static final long serialVersionUID=1L;

    /**
     * 记录属性的主键
     */
    @TableId("ATTR_ID")
    private BigDecimal attrId;

    private BigDecimal id;

    /**
     * 记录父级属性的标识
     */
    @TableField("PAR_ATTR_ID")
    private BigDecimal parAttrId;

    /**
     * 记录属性编码
     */
    @TableField("ATTR_NBR")
    private String attrNbr;

    @TableField("ATTR_NAME")
    private String attrName;

    private String text;

    @TableField("ATTR_DESC")
    private String attrDesc;

    /**
     * 记录属性规格默认取值
     */
    @TableField("DEFAULT_VALUE")
    private String defaultValue;

    /**
     * 记录状态。LOVB=PUB-C-0001。
     */
    @TableField("STATUS_CD")
    private String statusCd;

    /**
     * 记录创建的时间
     */
    @TableField("CREATE_DATE")
    private String createDate;

    private Integer defaultIndex = 0;

    private List<AttrSpec> children;



    public BigDecimal getAttrId() {
        return attrId;
    }

    public void setAttrId(BigDecimal attrId) {
        this.attrId = attrId;
    }

    public BigDecimal getParAttrId() {
        return parAttrId;
    }

    public void setParAttrId(BigDecimal parAttrId) {
        this.parAttrId = parAttrId;
    }

    public String getAttrNbr() {
        return attrNbr;
    }

    public void setAttrNbr(String attrNbr) {
        this.attrNbr = attrNbr;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttrDesc() {
        return attrDesc;
    }

    public void setAttrDesc(String attrDesc) {
        this.attrDesc = attrDesc;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<AttrSpec> getChildren() {
        return children;
    }

    public void setChildren(List<AttrSpec> children) {
        this.children = children;
    }

    @Override
    protected Serializable pkVal() {
        return this.attrId;
    }

    public Integer getDefaultIndex() {
        return defaultIndex;
    }

    public void setDefaultIndex(Integer defaultIndex) {
        this.defaultIndex = defaultIndex;
    }

    @Override
    public String toString() {
        return "AttrSpec{" +
        "attrId=" + attrId +
        ", parAttrId=" + parAttrId +
        ", attrNbr=" + attrNbr +
        ", attrName=" + attrName +
        ", attrDesc=" + attrDesc +
        ", defaultValue=" + defaultValue +
        ", statusCd=" + statusCd +
        ", createDate=" + createDate +
        "}";
    }
}
