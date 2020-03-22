package com.answerlyl.myfinance.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author answerlyl
 * @since 2020-02-28
 */
public class Records extends Model<Records> {

    private static final long serialVersionUID=1L;

    private String id;

    private String createtime;

    private String summary;

    private String message;

    private String contentType;

    private String contentPosition;

    private String month;

    private String week;

    private String dayOfWeek;

    private String day;

    private String date;

    private String hour;

    private String hourFormat;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentPosition() {
        return contentPosition;
    }

    public void setContentPosition(String contentPosition) {
        this.contentPosition = contentPosition;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getHourFormat() {
        return hourFormat;
    }

    public void setHourFormat(String hourFormat) {
        this.hourFormat = hourFormat;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "Records{" +
        "id=" + id +
        ", createtime=" + createtime +
        ", summary=" + summary +
        ", message=" + message +
        ", contentType=" + contentType +
        ", contentPosition=" + contentPosition +
        ", month=" + month +
        ", week=" + week +
        ", dayOfWeek=" + dayOfWeek +
        ", day=" + day +
        ", date=" + date +
        ", hour=" + hour +
        ", hourFormat=" + hourFormat +
        "}";
    }
}
