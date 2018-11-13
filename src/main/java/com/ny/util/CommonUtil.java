package com.ny.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

/**
 *
 *
 */
public class CommonUtil {
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy年MM月dd日");
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT_1 = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT_2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static String timestampToString(String timestamp) {
        Date date = new Date(Long.parseLong(timestamp));
        synchronized (SIMPLE_DATE_FORMAT) {
            return SIMPLE_DATE_FORMAT.format(date);
        }
    }

    public static long stringToTimestamp(String date) {
        long result = 0L;
        try {
            synchronized (SIMPLE_DATE_FORMAT_1) {
                Date date1 = SIMPLE_DATE_FORMAT_1.parse(date);
                return date1.getTime();
            }
        } catch (Exception e) {
            return result;
        }
    }

    public static long stringToTimestamp2(String date) {
        long result = 0L;
        try {
            synchronized (SIMPLE_DATE_FORMAT_2) {
                Date date1 = SIMPLE_DATE_FORMAT_2.parse(date);
                return date1.getTime();
            }
        } catch (Exception e) {
            return result;
        }
    }

    public static String matchPhrase(String param) {
        param = param.trim();
        String quotes = "\"＂“”“”";
        if (quotes.contains(param.substring(0, 1)) && quotes.contains(param.substring(param.length() - 1))) {
            return param.substring(1, param.length() - 1);
        }
        return null;
    }

    public static void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Object subString(Object param) {
        if (!StringUtils.isEmpty(param) && param.toString().length() > ConstsUtil.CONSTS_210) {
            param = param.toString().substring(0, ConstsUtil.CONSTS_210);
        }
        return param;
    }
}
