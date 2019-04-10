package com.springboot.framework.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 * 
 * @author song
 */
public class DateUtil {

	public static final String yyyyMMdd = "yyyyMMdd";
	public static final String yyyyMMddHH = "yyyyMMddHH";
	public static final String yyyy_MM_dd = "yyyy-MM-dd";
	public static final String yyyyMMddHHmmss = "yyyyMMddHHmmssSSS";

	public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

	private static SimpleDateFormat DEFAULT_FORMAT = new SimpleDateFormat(DEFAULT_PATTERN);
	private static SimpleDateFormat YYYYMMDD_FORMAT = new SimpleDateFormat(yyyyMMdd);
	private static SimpleDateFormat YYYY_MM_DD_FORMAT = new SimpleDateFormat(yyyy_MM_dd);
	private static SimpleDateFormat yyyyMMddHH_FORMAT = new SimpleDateFormat(yyyyMMddHH);
	private static SimpleDateFormat yyyyMMddHHmmss_FORMAT = new SimpleDateFormat(yyyyMMddHHmmss);

	private static Calendar calendar; // Calendar实例

	private static Calendar getCalendarInstance() {
		if (calendar != null) {
			return calendar;
		} else {
			return Calendar.getInstance();
		}
	}

	public static Date getCurrentTime() {
		return new Date();
	}

	/**
	 * 转成默认格式yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String format(Date date) {
		if (date == null) {
			return null;
		}
		return DEFAULT_FORMAT.format(date);
	}

	/**
	 * 将Date类型的日期转换为参数定义的格式的字符串。
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date, String pattern) {
		if (date == null || pattern == null) {
			return null;
		}
		if (pattern.equalsIgnoreCase(yyyyMMdd)) {
			return YYYYMMDD_FORMAT.format(date);
		}
		if (pattern.equalsIgnoreCase(yyyy_MM_dd)) {
			return YYYY_MM_DD_FORMAT.format(date);
		}
		if (pattern.equalsIgnoreCase(yyyyMMddHH)) {
			return yyyyMMddHH_FORMAT.format(date);
		}
		if (pattern.equalsIgnoreCase(yyyyMMddHHmmss)) {
			return yyyyMMddHHmmss_FORMAT.format(date);
		}
		SimpleDateFormat dateFromat = new SimpleDateFormat(pattern);
		return dateFromat.format(date);
	}

	public static Date getCurrDate(String format) {
		DateFormat df = new SimpleDateFormat(format);
		try {
			String currDateStr = df.format(new Date());
			return df.parse(currDateStr);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 封装开始时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date beginDate(Date date) {
		if (null == date) {
			return null;
		}
		String beginDate = new SimpleDateFormat(yyyy_MM_dd).format(date) + " 00:00:00";
		try {
			return new SimpleDateFormat(DEFAULT_PATTERN).parse(beginDate);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 封装结束时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date endDate(Date date) {
		if (null == date) {
			return null;
		}
		String endDate = new SimpleDateFormat(yyyy_MM_dd).format(date) + " 23:59:59";
		try {
			return new SimpleDateFormat(DEFAULT_PATTERN).parse(endDate);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @param nowDate
	 * @param day
	 *            负数表示减
	 * @return
	 */
	public static Date addDateByDay(Date nowDate, int day) {
		Calendar calendar = getCalendarInstance();
		calendar.setTime(nowDate);
		calendar.add(Calendar.DATE, day);
		return calendar.getTime();
	}

	/**
	 * @param nowDate
	 * @param hour
	 *            负数表示减
	 * @return
	 */
	public static Date addDateByHour(Date nowDate, int hour) {
		Calendar calendar = getCalendarInstance();
		calendar.setTime(nowDate);
		calendar.add(Calendar.HOUR_OF_DAY, hour);
		return calendar.getTime();
	}

	/**
	 * 添加秒
	 * @param nowDate
	 * @param secod
	 * @return
	 */
	public static Date addDateBySecond(Date nowDate, int secod) {
		Calendar calendar = getCalendarInstance();
		calendar.setTime(nowDate);
		calendar.add(Calendar.SECOND, secod);
		return calendar.getTime();
	}

	/**
	 * String转Date
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date stringToDate(String strDate, String pattern) {
		if (null == strDate) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			return sdf.parse(strDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 上周一统计开始时间00:00:00
	 * 
	 * @return
	 */
	public static Date lastMonday() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(addDateByDay(new Date(), -1));
		cal.add(Calendar.WEEK_OF_YEAR, -1);// 一周
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		String endDate = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()) + " 00:00:00";
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 上周日统计结束时间 12:00:00
	 * 
	 * @return
	 */
	public static Date lastSundayNoon() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		String endDate = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()) + " 12:00:00";
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 当天开始时间
	 * 
	 * @return
	 */
	public static Date getTodayStart() {
		Calendar todayStart = Calendar.getInstance();
		todayStart.set(Calendar.HOUR, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		return todayStart.getTime();
	}

	/**
	 * 当天结束时间
	 * 
	 * @return
	 */
	public static Date getTodayEnd() {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.set(Calendar.HOUR, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		todayEnd.set(Calendar.MILLISECOND, 999);
		return todayEnd.getTime();
	}
}
