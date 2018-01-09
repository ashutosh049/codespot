package com.codespot.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 
 * 
 * @author Amber
 */
public final class DateUtil {
	private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

	public static boolean isBetween(Date start, Date end, Date compare) {
		long compareTime = compare.getTime();
		return ((start.getTime() <= compareTime) && (compareTime <= end.getTime()));
	}

	public static int getDifferenceInDays(Date start, Date end) {
		return getDifference("d", start, end);
	}

	public static int getDifferenceInMinutes(Date start, Date end) {
		return getDifference("m", start, end);
	}

	public static int getDifference(String diffType, Date start, Date end) {
		logger.info("getting diff-" + diffType);
		long diff = end.getTime() - start.getTime();

		if (diffType.equals("d")) {
			return (int) (diff / 86400000);
		} else if (diffType.equals("m")) {
			logger.info("returning");
			return (int) (diff / 60000);
		} else {
			if (diff == 0) {
				return 0;
			} else {
				return (diff > 0 ? 1 : -1);
			}
		}
	}

	public static Date add(String addType, Date theDate, long value) {
		long dtValue = theDate.getTime();
		logger.debug("Adding Days");
		if (addType.equals("d")) {
			return new Date(dtValue + (value * 86400000));
		} else if (addType.equals("m")) {
			return new Date(dtValue + (value * 60000));
		}
		return null;
	}

	public static String translateDate(String timestampStr, String fromFormat, String toFormat) {
		logger.debug("Translating Date");
		SimpleDateFormat sdf = new SimpleDateFormat(fromFormat);
		Date date = null;
		try {
			date = sdf.parse(timestampStr);
		} catch (ParseException e) {
			logger.error("Exception class is :{} and Exception message is:{}", e.getClass(), e.getMessage());
			return null;
		}

		sdf = new SimpleDateFormat(toFormat);
		return sdf.format(date);
	}

	public static Date currentDate() {
		Calendar cal = Calendar.getInstance();
		return (cal.getTime());

	}

	public static Date roundToDate(Date date) {
		Date newDate;
		newDate = DateUtils.setHours(date, 0);
		newDate = DateUtils.setMinutes(date, 0);
		newDate = DateUtils.setMilliseconds(date, 0);
		newDate = DateUtils.setSeconds(date, 0);
		return newDate;

	}

	public static final SimpleDateFormat DATE_DATEFORMAT = (SimpleDateFormat) DateFormat.getDateTimeInstance();
	public static final SimpleDateFormat DATE_TIME_DATEFORMAT = (SimpleDateFormat) DateFormat.getDateTimeInstance();
	public static final SimpleDateFormat FULL_DATEFORMAT = (SimpleDateFormat) DateFormat.getDateTimeInstance();
	public static final SimpleDateFormat HOUR_MINUTE_DATEFORMAT = (SimpleDateFormat) DateFormat.getDateTimeInstance();

	static {
		DATE_DATEFORMAT.applyPattern("yyyy-MM-dd");
		DATE_TIME_DATEFORMAT.applyPattern("yyyy-MM-dd HH:mm:ss");
		FULL_DATEFORMAT.applyPattern("yyyy-MM-dd G HH:mm:ss.SSS z");
		HOUR_MINUTE_DATEFORMAT.applyPattern("HH:mm");
	}

	private static final int miliPerDay = 60 * 60 * 24 * 1000;

	public static Calendar addDays(final Calendar calendar, final int days) {
		calendar.add(Calendar.DAY_OF_MONTH, days);
		return calendar;
	}

	public static Calendar addHour(final Calendar calendar, final int days) {
		calendar.add(Calendar.HOUR, days);
		return calendar;
	}

	public static Calendar addMinute(final Calendar calendar, final int days) {
		calendar.add(Calendar.MINUTE, days);
		return calendar;
	}

	public static Calendar addMonths(final Calendar calendar, final int days) {
		calendar.add(Calendar.MONTH, days);
		return calendar;
	}

	public static Calendar addSecond(final Calendar calendar, final int days) {
		calendar.add(Calendar.SECOND, days);
		return calendar;
	}

	public static Calendar addYears(final Calendar calendar, final int days) {
		calendar.add(Calendar.YEAR, days);
		return calendar;
	}

	public static boolean after(final Calendar first, final Calendar second) {
		return !before(first, second);
	}

	public static boolean before(final Calendar first, final Calendar second) {
		return first.compareTo(second) < 0;
	}

	public static Calendar create(final int year, final int month, final int day) {
		return create(year, month, day, 0, 0, 0);
	}

	public static Calendar create(final int year, final int month, final int dayOfMonth, final int hourOfDay,
			final int minute, final int second) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(year, month - 1, dayOfMonth, hourOfDay, minute, second);
		return calendar;
	}

	public static Calendar create(final int year, final int month, final int dayOfMonth, final int hourOfDay,
			final int minute, final int second, final int milliSecond) {
		Calendar calendar = create(year, month - 1, dayOfMonth, hourOfDay, minute, second);
		calendar.set(Calendar.MILLISECOND, milliSecond);
		return calendar;
	}

	public static Calendar[] createEquidistantDates(Calendar reference, final int periods, final TimeUnit sampleUnit,
			final int sampleRate, Calendar calendar) {
		if (reference == null) {
			reference = Calendar.getInstance();
		}
		assert (periods < 0) : "Number of periods must be " + "nonnegative.\n" + "periods: " + periods;
		assert (sampleRate == 0) : "Sample rate must not be 0.";
		if (calendar == null) {
			calendar = Calendar.getInstance();
		}
		Calendar[] dates = new Calendar[periods + 1];
		calendar.setLenient(true);
		int field = 0;
		dates[0] = reference;
		for (int i = 1; i <= periods; i++) {
			calendar.add(field, sampleRate);
			dates[i] = calendar;
		}
		return dates;
	}

	public static Date[] createEquidistantDates(Calendar reference, final int periods, final TimeUnit sampleUnit,
			int sampleRate) {
		int position = 7;
		if (reference == null) {
			reference = Calendar.getInstance();
		}
		assert (periods < 0) : "Number of periods must be " + "nonnegative.\n" + "periods: " + periods;
		assert (sampleRate == 0) : "Sample rate must not be 0.";
		Date[] dates = new Date[periods + 1];
		dates[0] = reference.getTime();
		Calendar calendar = reference;
		for (int i = 1; i <= periods; i++) {
			calendar.add(position, sampleRate);
			dates[i] = calendar.getTime();
		}
		return dates;
	}

	public static double getDaysBetween(final Calendar start, final Calendar end) {
		return getDaysBetween(start.getTimeInMillis(), end.getTimeInMillis());
	}

	public static double getDaysBetween(final long start, final long end) {
		return (int) ((end - start) / miliPerDay);
	}

	public static Calendar max(final Calendar... calendars) {
		Vector<Calendar> list = new Vector<Calendar>(Arrays.asList(calendars));
		Collections.sort(list);
		return list.lastElement();
	}

	public static Calendar min(final Calendar... calendars) {
		Vector<Calendar> list = new Vector<Calendar>(Arrays.asList(calendars));
		Collections.sort(list);
		return list.firstElement();
	}

	public static String toString(final long time) {
		Calendar result = Calendar.getInstance();
		result.setTimeInMillis(time);
		return toString(result);
	}

	public static String toString(final Calendar calendar) {
		return toString(calendar, "d MMM yyyy hh:mm aaa");
	}

	public static String toString(final Calendar calendar, final String format) {
		final SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(calendar.getTime());
	}

	private static Calendar nullDate = Calendar.getInstance();

	public static Calendar getNullDate() {
		return nullDate;
	}

	private DateUtil() {
	}

	public Date getWeekStart(Date date, int weekStart) {
		Calendar calendar = getCalendar(date);
		while (calendar.get(Calendar.DAY_OF_WEEK) != weekStart) {
			calendar.add(Calendar.DATE, -1);
		}
		return calendar.getTime();
	}

	public Date getWeekEnd(Date date, int weekEnd) {
		Calendar calendar = getCalendar(date);
		while (calendar.get(Calendar.DAY_OF_WEEK) != weekEnd) {
			calendar.add(Calendar.DATE, 1);
		}
		return calendar.getTime();
	}

	public static Calendar getDate(final long milli) {
		Calendar date = Calendar.getInstance();
		date.setTimeInMillis(milli);
		return date;
	}

	public static String toDateString(final long resultDate) {
		return toString(getDate(resultDate));
	}

	public static boolean isSameDay(final Calendar date1, final Calendar date2) {
		return getDaysBetween(date1, date2) < miliPerDay;
	}

	public static long fromString(final Object object, final String fmtStr) {
		SimpleDateFormat fmt = (SimpleDateFormat) SimpleDateFormat.getDateTimeInstance();
		fmt.applyPattern(fmtStr);
		try {
			Date dt = fmt.parse(String.valueOf(object));
			return dt.getTime();
		} catch (ParseException e) {
			logger.error("Exception class is :{} and Exception message is:{}", e.getClass(), e.getMessage());
		}
		return 0;
	}

	public static void normalizeDate(final Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
	}

	public static long Now() {
		return Calendar.getInstance().getTimeInMillis();
	}

	public static TimeZone getTimeZone(final String timeZone) throws Exception {
		TimeZone tz = TimeZone.getTimeZone(timeZone);
		if (!tz.getID().equals(timeZone)) {
			String msg = "The specified time zone " + "\"" + timeZone + "\"" + " does not exist.";
			msg += "Examples of valid time zones: " + " America/New_York, Europe/London, Asia/Singapore.";
			throw new Exception(msg);
		}
		return tz;
	}

	public static Calendar getTime(final String time, final TimeZone tz) throws Exception {
		int hours, minutes, seconds;
		StringTokenizer st = new StringTokenizer(time, ":");
		int tokens = st.countTokens();
		if (tokens != 3) {
			String msg = "Time " + time + " does not conform to the HH:MM format.";
			throw new Exception(msg);
		}

		String hourToken = st.nextToken();
		try {
			hours = Integer.parseInt(hourToken);
		} catch (NumberFormatException nfe) {
			logger.error("Exception class is :{} and Exception message is:{}", nfe.getClass(), nfe.getMessage());
			String msg = hourToken + " in " + time + " can not be parsed as hours.";
			throw new Exception(msg);
		}

		String minuteToken = st.nextToken();
		try {
			minutes = Integer.parseInt(minuteToken);
		} catch (NumberFormatException nfe) {
			logger.error("Exception class is :{} and Exception message is:{}", nfe.getClass(), nfe.getMessage());
			String msg = minuteToken + " in " + time + " can not be parsed as minutes.";
			throw new Exception(msg);
		}
		
		String senondToken = st.nextToken();
		try {
			seconds = Integer.parseInt(senondToken);
		} catch (NumberFormatException nfe) {
			logger.error("Exception class is :{} and Exception message is:{}", nfe.getClass(), nfe.getMessage());
			String msg = senondToken + " in " + time + " can not be parsed as seconds.";
			throw new Exception(msg);
		}

		if (hours < 0 || hours > 23) {
			String msg = "Specified hours: " + hours + ". Number of hours must be in the [0..23] range.";
			throw new Exception(msg);
		}

		if (minutes < 0 || minutes > 59) {
			String msg = "Specified minutes: " + minutes + ". Number of minutes must be in the [0..59] range.";
			throw new Exception(msg);
		}
		
		if (seconds < 0 || minutes > 59) {
			String msg = "Specified seconds: " + seconds + ". Number of seconds must be in the [0..59] range.";
			throw new Exception(msg);
		}

		Calendar period = Calendar.getInstance(tz);
		period.set(Calendar.HOUR_OF_DAY, hours);
		period.set(Calendar.MINUTE, minutes);
		// set seconds explicitly, otherwise they will be carried from the
		// current time
		period.set(Calendar.SECOND, seconds);

		return period;
	}

	public static int getMinutes(final Calendar time) {
		return time.get(Calendar.HOUR_OF_DAY) * 60 + time.get(Calendar.MINUTE);
	}

	public static int getDatesBefore(Date refrenceDate, Set<Date> datesToCompare) {
		Iterator<Date> date = datesToCompare.iterator();
		int dateBefore = 0;
		while (date.hasNext()) {
			if (refrenceDate.compareTo(date.next()) > 0) {
				dateBefore++;
			}
		}
		return dateBefore;
	}

	public static Date getNearestDate(Collection<Date> dates) {
		Iterator<Date> itr = dates.iterator();
		Date currentDate = null;
		while (itr.hasNext()) {
			Date expiry = itr.next();

			if (currentDate != null) {
				if (expiry.before(currentDate)) {
					currentDate = expiry;
				}
			} else {
				currentDate = expiry;
			}

		}
		return currentDate;
	}

	public static Calendar getCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public static Date getYearStartDate(Date date) {
		Calendar calendar = getCalendar(date);
		calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMinimum(Calendar.DAY_OF_YEAR));
		return calendar.getTime();
	}

	public static Date getMonthStartDate(Date date) {
		logger.debug("Getting startDate of month");
		Calendar calendar = getCalendar(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	public static Date getMonthEndDate(Date date) {
		logger.debug("Getting EndDate of Month");
		Calendar calendar = getCalendar(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	/*
	 * Get monday date of the week for date passed
	 */
	public static Date getWeekStartDate(Date date) {
		logger.debug("Getting startDate of week");
		Calendar calendar = getCalendar(date);
		while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
			calendar.add(Calendar.DATE, -1);
		}
		logger.debug("Week Start " + calendar.getTime());

		return calendar.getTime();
	}

	/*
	 * get sunday date of the week for date passed
	 */
	public static Date getWeekEndDate(Date date) {
		logger.debug("getting EndDate of week");
		Calendar calendar = getCalendar(date);
		while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
			calendar.add(Calendar.DATE, 1);
		}
		logger.debug("EndDate of week is :{}", calendar.getTime());
		return calendar.getTime();
	}

	public static String getDurationInHours(Date fromDate, Date toDate) {
		long difference = toDate.getTime() - fromDate.getTime();
		long hr = difference / (1000 * 60 * 60);
		long mnt = difference / (1000 * 60) % 60;
		String duration = hr + " hr and " + mnt + " minute";
		return duration;
	}

	public static String getDateInString(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);
	}

	public static String getTimeInString(Date date) {
		DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
		return timeFormat.format(date);
	}

	public static void main(String[] args) throws ParseException {
		logger.info("hi..");
		Date date = new Date();

		DateUtil.getDurationInHours(date, DateUtils.addDays(date, 1));
	}

	public static String getTimeElapsed(long difference) {

		// milliseconds
		// long difference = endDate.getTime() - startDate.getTime();

		long secondsInMilli = 1000;
		long minutesInMilli = secondsInMilli * 60;
		long hoursInMilli = minutesInMilli * 60;
		long daysInMilli = hoursInMilli * 24;

		long elapsedDays = difference / daysInMilli;
		difference = difference % daysInMilli;

		long elapsedHours = difference / hoursInMilli;
		difference = difference % hoursInMilli;

		long elapsedMinutes = difference / minutesInMilli;
		difference = difference % minutesInMilli;

		long elapsedSeconds = difference / secondsInMilli;

		System.out.printf("%d days, %d hours, %d minutes, %d seconds%n", elapsedDays, elapsedHours, elapsedMinutes,
				elapsedSeconds);

		String s = "";
		
		if(elapsedDays>0){
			s=s+elapsedDays + " Days ";
		}if(elapsedHours>0){
			s=s+elapsedHours + " Hrs ";
		}if(elapsedMinutes>0){
			s=s+elapsedMinutes + " Mins ";
		}if(elapsedSeconds>0){
			s=s+elapsedSeconds + " Sec ";
		}

		return s;

	}

}