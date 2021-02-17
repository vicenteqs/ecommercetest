package es.vicenteqs.ecommercetest.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtils {

	private static final Date EMPTY_DATE = new Date(0);

	private DateUtils() {

	}

	public static Date now() {
		return new Date();
	}

	public static Date emptyDate() {
		return EMPTY_DATE;
	}

	public static boolean isEmptyDate(Date date) {
		return date != null && DateUtils.equalsNoTime(EMPTY_DATE, date);
	}

	public static Date nowNoTime() {
		return DateUtils.dateNoTime(DateUtils.now());
	}

	public static Date dateNoTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	public static Date sub(Date now, int days) {
		final int days1 = days < 0 ? days : -days;
		return DateUtils.add(now, days1);
	}

	public static Date add(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, days);
		return calendar.getTime();
	}

	public static Date subMin(final Date date, int mins) {
		final int min = mins < 0 ? mins : -mins;
		return DateUtils.add(date, min);
	}

	public static Date addMins(final Date date, int mins) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, mins);
		return calendar.getTime();
	}

	public static Date addMonth(Date date, int mouth) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, mouth);
		return calendar.getTime();
	}

	public static boolean beforeDays(Date date, int days) {
		return DateUtils.beforeDays(date, DateUtils.now(), days);
	}

	public static boolean beforeDays(Date date, Date now, int days) {
		Date when = DateUtils.sub(now, days);
		return date.before(when);
	}

	public static String toString(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}

	public static String toString(Date date) {
		return DateUtils.toString(date, "dd/MM/yyyy");
	}

	public static String toStringDateTime(Date date) {
		return DateUtils.toString(date, "dd/MM/yyyy HH:mm:ss");
	}

	public static Date date(int year, int month, int date) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	public static Date dateTime(int year, int month, int date, int hourOfDay, int minute, int second) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, date, hourOfDay, minute, second);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static boolean equalsNoTime(Date date1, Date date2) {
		Date dateNoTime1 = DateUtils.dateNoTime(date1);
		Date dateNoTime2 = DateUtils.dateNoTime(date2);
		return dateNoTime1.equals(dateNoTime2);
	}

	public static boolean beforeNoTime(Date date1, Date date2) {
		Date dateNoTime1 = DateUtils.dateNoTime(date1);
		Date dateNoTime2 = DateUtils.dateNoTime(date2);
		return dateNoTime1.before(dateNoTime2);
	}

	public static boolean afterNoTime(Date date1, Date date2) {
		Date dateNoTime1 = DateUtils.dateNoTime(date1);
		Date dateNoTime2 = DateUtils.dateNoTime(date2);
		return dateNoTime1.after(dateNoTime2);
	}

	public static boolean beforeToday(Date date) {
		Date dateNoTime = DateUtils.dateNoTime(date);
		return dateNoTime.before(DateUtils.nowNoTime());
	}

	public static Date max(Date date1, Date date2) {
		if (date1 == null && date2 == null)
			return null;
		if (date1 == null)
			return date2;
		if (date2 == null)
			return date1;
		return (date1.after(date2)) ? date1 : date2;
	}

	public static Integer getPeriodInDays(Date date1, Date date2) {
		long diffInMillies = Math.abs(DateUtils.dateNoTime(date1).getTime() - DateUtils.dateNoTime(date2).getTime());
		long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		return Integer.valueOf((int) diff);
	}

	public static Integer getPeriodInSeconds(Date date1, Date date2) {
		long diffInMillies = Math.abs(date1.getTime() - date2.getTime());
		long diff = TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		return Integer.valueOf((int) diff);
	}

	public static Date lastMonthDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1);
		int numOfDaysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		c.add(Calendar.DAY_OF_MONTH, numOfDaysInMonth - 1);
		return c.getTime();
	}

	public static Calendar getCalendarWithNextDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 1);

		return calendar;
	}

	public static Integer hourToInteger(String hour) {
		Integer hourWithFormat = null;
		if (hour != null) {
			hourWithFormat = Integer.parseInt(hour.replace(":", ""));
		}
		return hourWithFormat;
	}

	public static String hourToString(Integer hour) {
		String hourWithFormat = null;
		if (hour != null) {
			hourWithFormat = String.format("%02d:%02d", hour / 100, hour % 100);
		}
		return hourWithFormat;
	}

	public static boolean dateIsBefore(Date date1, Date date2) {
		return date1.compareTo(date2) <= 0;
	}

	public static Date addDaysToDateNow(Integer days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		calendar.add(Calendar.DATE, days);

		return calendar.getTime();
	}
}
