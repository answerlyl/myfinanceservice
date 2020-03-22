package com.answerlyl.myfinance.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtil {
    /**
     * yyyy-MM-dd HH:mm:ss标准日期+时间格式
     */
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * yyyy-MM-dd 标准日期格式
     */
    public static final String DATE_PATTERN = "yyyy-MM-dd";

    /**
     * yyyy-MM 标准日期格式
     */
    public static final String DATE_PATTERN_NO_DAY = "yyyy-MM";

    /**
     * yyyyMMdd 紧凑日期格式
     */
    public static final String DATE_PATTERN_MIN = "yyyyMMdd";

    /**
     * yyyy年MM月dd日 中文日期格式
     */
    public static final String DATE_PATTERN_CN = "yyyy年MM月dd日";

    public static Map<Integer,String> WEEK_MAP = new HashMap<Integer,String>(){{
        put(1,"Mon");
        put(2,"Tues");
        put(3,"Wed");
        put(4,"Thur");
        put(5,"Fri");
        put(6,"Sat");
        put(7,"Sun");
    }};



    /**
     * 获取:<br/>
     * 获取标准日期格式:yyyy-MM-dd HH:mm:ss
     * @return 返回字符串
     */
    public static String getNowDataForStandard(){
        Date dateTime = new Date();
        return  dateFormat(dateTime, DateUtil.DATE_TIME_PATTERN);
    }

    /**
     * 获取:<br/>
     * 获取自定义格式的日期
     * @param pattern 日期格式
     * @return 返回字符串
     */
    public static String getNowDateByPattern(String pattern) {

        return dateFormat(new Date(),pattern);
    }

    /**
     * 获取:<br/>
     * 获取参数日期后的七天数据,包括参数日期
     * @param date  日期
     * @param pattern 日期格式
     * @return 返回字符串
     */
    public static String[] get7DaysDate(String date,String pattern) {
        Date dateTemp = formatStringToDate(date, pattern);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTemp);
        String[] days = new String[7];
        days[0] = date;
        for(int i = 1 ; i < 7 ; i++){
            calendar.add(Calendar.DATE,1);

            days[i] = dateFormat(calendar.getTime(),pattern);
        }

        return days;
    }


    /**
     * 获取:
     * 获取日期差值
     * @param date 日期
     * @param pattern 格式
     * @param differenceValue 差的天数
     * @return
     */
    public static String getDifferenceValueDate(String date,String pattern,int differenceValue) {
        Date dateTemp = formatStringToDate(date, pattern);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTemp);
        calendar.add(Calendar.DATE,differenceValue);

        return formatDateToString(calendar.getTime(), pattern);
    }



    public static void main(String[] args) {
        //String[] daysDate = DateUtil.get7DaysDate("20190417", DateUtil.DATE_PATTERN_MIN);

        String differenceValueDate = getDifferenceValueDate("20190417", DateUtil.DATE_PATTERN_MIN, 0);
        String differenceValueDate1 = getDifferenceValueDate("20190417", DateUtil.DATE_PATTERN_MIN, 1);
        String differenceValueDate2 = getDifferenceValueDate("20190417", DateUtil.DATE_PATTERN_MIN, -1);

        System.out.println(differenceValueDate);
    }

    /**
     * 内部私有:格式化日期
     * @param date 日期
     * @param pattern 格式
     * @return
     */
    private static String dateFormat(Date date, String pattern) {
        if(StringUtils.isBlank(pattern)){
            pattern = DateUtil.DATE_PATTERN;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }




    /**
     * 格式化:
     * 格式化字符串成为日期
     * @param date
     * @param pattern
     * @return
     */
    public static Date formatStringToDate(String date,String pattern){

        if(StringUtils.isBlank(pattern)){
            pattern = DATE_TIME_PATTERN;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("格式化异常");
        }

    }

    /**
     * 格式化:
     * 格式化日期为字符串
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDateToString(Date date,String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);

    }


    /**
     * 格式化:
     * 将字符串由一种格式变为另一种
     * @param date 字符串日期
     * @param srcPattern 原格式
     * @param descPattern 目标格式
     * @return
     */
    public static String formatStringToString(String date,String srcPattern,String descPattern){
        Date temp = formatStringToDate(date, srcPattern);
        return formatDateToString(temp, descPattern);

    }



    /**
     * 通过数字返回星期
     * @param num String数字1-7
     * @return
     */
    public static String getWeek(Integer num ){
        return WEEK_MAP.get(num);
    }

}
