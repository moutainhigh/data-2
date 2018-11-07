package com.hourz.common.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>日期工具类</p>
 * @author hourz
 * @since 2016-05-24
 */
public class DateUtils {
	
	/**
	 * <p>将生日转换为年龄</p>
	 * @param birthday 生日
	 * @return
	 */
	public static int getAge(Date birthday){
		Calendar cal = Calendar.getInstance();
        if (cal.before(birthday)) {
            throw new IllegalArgumentException("The birthday is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthday);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - yearBirth;
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                //monthNow==monthBirth
                if (dayOfMonthNow < dayOfMonthBirth) age--;
            } else {
                //monthNow>monthBirth
                age--;
            }
        }
        return age;
	}
	
	/**
	 * <p>日期字符串格式为日期对象</p>
	 * @param dateStr 日期时间字符串
	 * @param pattern 日期格式      不能为空
	 * @return	
	 */
	public static Date dateStrToDateObj(String dateStr, String pattern) {
		if (dateStr == null && "".equals(dateStr)) return null;
		if (pattern == null && "".equals(pattern)) return null;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date formate = null;
		try {
			formate = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return formate;
	}
	
	/**
	 * <p>将日起对象转换为日期字符串</p>
	 * @param date 日期对象
	 * @param pattern 日期字符串格式(具体格式请参考SimpleDateFormat)
	 * @return
	 */
	public static String dateObjToDateStr(Date date, String pattern) {
		if (date==null) return "";
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern(pattern);
		return sdf.format(date);
	}
	
	/**
	 * <p>获取指定日期指定天数前的日期</p>
	 * @param date 指定日期
	 * @param days 天数
	 * @return
	 */
	public static Date getDateBeforeDays(Date date, int days) {
		//获取一个星期前的日期
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) - days);
		SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd");
		Date startWeek = null;
		try {
			startWeek = sdft.parse(sdft.format(cal.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return startWeek;
	}	
	
	/**
	 * 获取指定日期指定天数后的日期
	 * @param date 指定日期
	 * @param days 天数
	 * @return
	 */
	public static Date getDateAfterDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, days);
		return cal.getTime();
	}
	
	/**
	 * 获取两个日期间的天数(包含startDate和endDate)
	 * @param startDate 起始日期
	 * @param endDate 结束日期
	 * @return
	 */
	public static int getDaysBetweenTwoDate(Date startDate, Date endDate) {
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(startDate);
		setCalendarLowestOfTime(startCal);
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endDate);
		setCalendarTopestOfTime(endCal);
		return Double.valueOf(Math.ceil(Math.abs(endCal.getTimeInMillis()-
				startCal.getTimeInMillis())/(24*3600*1000f))).intValue();
	}
	
	/**
	 * 判断日历是否为周末
	 * @param cal 日历
	 * @return 是否为周末
	 */
	public static Boolean isWeekend(Calendar cal) {
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		// 判断是否为周末
		if (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY) return true;
		else return false;
	}
	
	/**
	 * 是否为同年同月同日
	 * @param date1 日期1
	 * @param date2 日期2
	 * @return
	 */
	public static Boolean isSameDate(Date date1, Date date2){
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		if (cal1.get(Calendar.YEAR) != cal2.get(Calendar.YEAR)) return false;
		if (cal1.get(Calendar.MONTH) != cal2.get(Calendar.MONTH)) return false;
		if (cal1.get(Calendar.DAY_OF_MONTH) != cal2.get(Calendar.DAY_OF_MONTH)) return false;
		return true;
	}	
	
	/**
	 * 是否同年同月同周
	 * @param date1 日期1
	 * @param date2 日期2
	 * @return
	 */
	public static Boolean isSameWeek(Date date1, Date date2){
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
				&& cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)) return true;
		else return false;
	}
	
	/**
	 * <p>指定日期是否在两个日期之间</p>
	 * @param thisDate 指定日期
	 * @param startdate 起始日期
	 * @param enddate 结束日期
	 * @return
	 */
	public static Boolean isBetweenTwoDates(Date thisDate, Date startdate, Date enddate){
		Date lowestStartDate = getLowestDate(startdate);
		Date topestEndDate = getTopestDate(enddate);
		if (thisDate.getTime() >= lowestStartDate.getTime()
				&& thisDate.getTime() <= topestEndDate.getTime()) return true;
		else return false;
	}
	
	/**
	 * <p>比较两个日期对象(只比较到天, 即同年同月同日即为相同)</p>
	 * @param d1 日期1
	 * @param d2 日期2
	 * @return
	 *        -1  日期1 < 日期2
	 *         0  日期1 = 日期2
	 *         1  日期1 > 日期2
	 */
	public static int compareDate(Date d1, Date d2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(d1);
		setCalendarLowestOfTime(cal1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(d2);
		setCalendarLowestOfTime(cal2);
		return cal1.compareTo(cal2);
	}

	/**
	 * <p>将一个日历对象的时间置成0小时0分钟0秒</p>
	 * @param cal 日历对象
	 */
	private static void setCalendarLowestOfTime(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
	}
	
	/**
	 * <p>将一个日历对象的时间设置成23小时59分29秒</p>
	 * @param cal 日历对象
	 */
	private static void setCalendarTopestOfTime(Calendar cal){
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
	}
	
	/**
	 * <p>获取日期对象的最小值</p>
	 * @param date 日期对象
	 * @return 日期对象
	 */
	private static Date getLowestDate(Date date) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	setCalendarLowestOfTime(cal);
    	return cal.getTime();
	}
	
	/**
	 * <p>获取日期对象的最大值</p>
	 * @param date 日期对象
	 * @return 日期对象
	 */
	private static Date getTopestDate(Date date) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	setCalendarTopestOfTime(cal);
    	return cal.getTime();
	}	

	public static void main(String[] args) {
		Date date = new Date();
		System.out.println(DateUtils.dateObjToDateStr(date, "yyyyMMddHHmmssSSS"));
	}
}
