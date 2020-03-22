package com.answerlyl.myfinance.entity;

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
 * @since 2020-02-12
 */
public class Notice extends Model<Notice> {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "NOTICE_ID", type = IdType.AUTO)
    private Integer noticeId;

    /**
     * 显示日期
     */
    @TableField("SHOW_DATE")
    private String showDate;

    /**
     * 显示内容
     */
    @TableField("SHOW_INFO")
    private String showInfo;

    /**
     * 背景颜色
     */
    @TableField("BKG_COLOR")
    private String bkgColor;

    /**
     * 字体颜色
     */
    @TableField("FONT_COLOR")
    private String fontColor;


    public Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    public String getShowDate() {
        return showDate;
    }

    public void setShowDate(String showDate) {
        this.showDate = showDate;
    }

    public String getShowInfo() {
        return showInfo;
    }

    public void setShowInfo(String showInfo) {
        this.showInfo = showInfo;
    }

    public String getBkgColor() {
        return bkgColor;
    }

    public void setBkgColor(String bkgColor) {
        this.bkgColor = bkgColor;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    @Override
    protected Serializable pkVal() {
        return this.noticeId;
    }

    @Override
    public String toString() {
        return "Notice{" +
        "noticeId=" + noticeId +
        ", showDate=" + showDate +
        ", showInfo=" + showInfo +
        ", bkgColor=" + bkgColor +
        ", fontColor=" + fontColor +
        "}";
    }
}
