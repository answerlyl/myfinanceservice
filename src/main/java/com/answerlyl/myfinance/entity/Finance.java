package com.answerlyl.myfinance.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author answerlyl
 * @since 2020-01-30
 */
public class Finance extends Model<Finance> {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "FIN_ID", type = IdType.AUTO)
    private Integer finId;

    /**
     * 录入日期
     */
    @TableField("INPUT_DATE")
    private String inputDate;

    @TableField("YEAR")
    private String year;
    /**
     * 录入月份
     */
    @TableField("MONTH")
    private String month;

    @TableField("DAY")
    private String day;

    @TableField(exist=false)
    private String finDate;

    @TableField(exist=false)
    private String finTime;

    @TableField(exist=false)
    private long current;

    @TableField(exist=false)
    private long size;

    @TableField(exist=false)
    private String finTypeName;
    @TableField(exist=false)
    private String sortName;
    @TableField(exist=false)
    private String sortDetailName;
    @TableField(exist=false)
    private String belongName;


    /**
     * 金额
     */
    @TableField("AMOUNT")
    private Float amount;

    /**
     * 类型：收入/支出
     */
    @TableField("FIN_TYPE")
    private BigDecimal finType;

    /**
     * 创建日期
     */
    @TableField("CREATE_DATE")
    private String createDate;

    /**
     * 分类
     */
    @TableField("SORT")
    private BigDecimal sort;

    /**
     * 详情
     */
    @TableField("SORT_DETAIL")
    private BigDecimal sortDetail;

    /**
     * 归属
     */
    @TableField("BELONG")
    private BigDecimal belong;

    /**
     * 备注
     */
    @TableField("REMARK")
    private String remark;


    public Integer getFinId() {
        return finId;
    }

    public void setFinId(Integer finId) {
        this.finId = finId;
    }

    public String getInputDate() {
        return inputDate;
    }

    public void setInputDate(String inputDate) {
        this.inputDate = inputDate;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public BigDecimal getFinType() {
        return finType;
    }

    public void setFinType(BigDecimal finType) {
        this.finType = finType;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public BigDecimal getSort() {
        return sort;
    }

    public void setSort(BigDecimal sort) {
        this.sort = sort;
    }

    public BigDecimal getSortDetail() {
        return sortDetail;
    }

    public void setSortDetail(BigDecimal sortDetail) {
        this.sortDetail = sortDetail;
    }

    public BigDecimal getBelong() {
        return belong;
    }

    public void setBelong(BigDecimal belong) {
        this.belong = belong;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFinDate() {
        return finDate;
    }

    public void setFinDate(String finDate) {
        this.finDate = finDate;
    }

    public String getFinTime() {
        return finTime;
    }

    public void setFinTime(String finTime) {
        this.finTime = finTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.finId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getFinTypeName() {
        return finTypeName;
    }

    public void setFinTypeName(String finTypeName) {
        this.finTypeName = finTypeName;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortDetailName() {
        return sortDetailName;
    }

    public void setSortDetailName(String sortDetailName) {
        this.sortDetailName = sortDetailName;
    }

    public String getBelongName() {
        return belongName;
    }

    public void setBelongName(String belongName) {
        this.belongName = belongName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "Finance{" +
        "finId=" + finId +
        ", inputDate=" + inputDate +
        ", amount=" + amount +
        ", finType=" + finType +
        ", createDate=" + createDate +
        ", sort=" + sort +
        ", sortDetail=" + sortDetail +
        ", belong=" + belong +
        ", remark=" + remark +
        "}";
    }
}
