package com.hhgs.plantshows.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateUtil extends Date {

    private static DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static Date getDateTime(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date1 = sdf.parse(date);
            return date1;
        } catch (Exception e) {
            e.printStackTrace();
            return new Date();
        }


    }

    public static Date getDateTimeFromInfluxdb(String date) {
        //String[] strings1 = date.split("T");
        //String[] strings2 = strings1[1].split("\\+");
        //String finalStringDate = strings1[0]+" "+strings2[0];
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date date1 = sdf.parse(date);
            return date1;
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    public static long getTime(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date1 = sdf.parse(date);
            return date1.getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }


    }

    public static String getCurrentTime(Date date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String currentTime = sdf.format(date);
            return currentTime;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getCurrentDate(Date date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            String currentTime = sdf.format(date);
            return currentTime;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date getBeginTime(String date) {
        String[] dateStrings = date.split("-");
        int year = Integer.parseInt(dateStrings[0]);
        int month = Integer.parseInt(dateStrings[1]);

        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate localDate = yearMonth.atDay(1);
        LocalDateTime startOfDay = localDate.atStartOfDay();
        ZonedDateTime zonedDateTime = startOfDay.atZone(ZoneId.of("Asia/Shanghai"));
        return Date.from(zonedDateTime.toInstant());
    }

    public static Date getInfluxdbStartTime(String date) throws ParseException {
        String resultDate = date + " 00:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = sdf.parse(resultDate);
        return startDate;
    }


    public static Date getInfluxdbEndTime(String date) throws ParseException {
        String resultDate = date + " 23:59:59";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date endDate = sdf.parse(resultDate);
        return endDate;
    }


    public static Date getEndTime(String date) {
        String[] dateStrings = date.split("-");
        int year = Integer.parseInt(dateStrings[0]);
        int month = Integer.parseInt(dateStrings[1]);

        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();
        LocalDateTime localDateTime = endOfMonth.atTime(23, 59, 59, 999);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Asia/Shanghai"));
        return Date.from(zonedDateTime.toInstant());
    }

    public static long getCurrentTime() {
        long currentTimeMillis = System.currentTimeMillis();
        return currentTimeMillis;
    }

    public static Date getCurrentDate() {
        return new Date();
    }


    public static String getSdfTime(long date) {
        try {

            //SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    public static Date formatStringToDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date parse = sdf.parse(date);
            return parse;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date fromatTime(long date) {
        Date date2 = new Date(date);
        return date2;
    }

    /**
     * 获取从昨天开始，近一周的日期起始值
     *
     * @return
     */
    public static String[] getRecentWeekStartAndEnd() {
        LocalDate now = LocalDate.now();
        String end = format.format(now.plusDays(-1)) + " 00:00:00";
        String start = format.format(now.plusDays(-7)) + " 00:00:00";
        String[] result = {start, end};
        return result;
    }


//    public static void main(String[] args) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = new Date();
//        String currentTime = getCurrentDate(date);
//        Date beginTime = getBeginTime(currentTime);
//        Date endTime = getEndTime(currentTime);
//        System.out.println(sdf.format(beginTime));
//        System.out.println(sdf.format(endTime));
//        // String date="2019-07-13 17:10:00";
//        //String date1="2019-11-10T15:31:16.469+08:00";
//
//
//
//        /*System.out.println(getTime(date));
//        String date1="2019-07-13 17:15:00";
//        System.out.println(getTime(date1));
//
//
//        long start=1559628730265l;
//        System.out.println(getSdfTime(start));
//
//        long s=System.currentTimeMillis();
//        long s2=1000*60*60*24*100;
//
//        System.out.println(s+"  "+s2);
//        System.out.println(s-s2);*/
//        //Date dateTime = getDateTimeFromInfluxdb(date1);
//        //long time = dateTime.getTime();
//
//        // System.out.println(currentTime);
//        // System.out.println(time);
//
//
//    }


    /**
     * 获取前日期是本月第几天
     *
     * @param date
     * @return
     */
    public static int getDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取某一天开始的时间戳
     *
     * @return
     */
    public static long getDayStartTimestap() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatStr = DateUtil.format.format(LocalDate.now()) + " 00:00:00";
        try {
            return sdf.parse(formatStr).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    /**
     * 判断传入字符串日期是年的第一天还是月的第一天
     *
     * @param str
     * @return 0 年的第一天
     * 1，月的第一天
     * -1 既不是月的第一天也不是年的第一天
     */
    public static int isFirstDay(String str) {
        int index = str.indexOf("-01");
        int lastIndex = str.lastIndexOf("-01");
        if (index == lastIndex) {
            if (index != -1) {
                return 1;
            }
            return -1;
        } else {
            return 0;
        }
    }

    public static String getYesterdayStr(long ms){
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(new Date().getTime()/1000 , 0, ZoneOffset.ofHours(8));
        LocalDate localDate = localDateTime.toLocalDate();
        return format.format(localDate.plusDays(-1));
    }


    public static void main(String[] args) {
        String sdfTime = getSdfTime(1602526132939L);
        System.out.println(sdfTime);
    }
}
