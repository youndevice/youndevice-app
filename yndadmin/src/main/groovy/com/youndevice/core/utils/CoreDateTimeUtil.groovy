package com.youndevice.core.utils

import com.youndevice.core.constants.DTDateTimeFormats
import com.youndevice.core.enums.TimeInterval

import java.sql.Time
import java.text.DateFormat
import java.text.SimpleDateFormat

class CoreDateTimeUtil {

    public final static String DUBAI_TIME_ZONE = "Asia/Dubai"
    public final static String UTC_TIME_ZONE = "UTC"
    final static TimeZone dubaiTimeZone = TimeZone.getTimeZone(DUBAI_TIME_ZONE)
    final static TimeZone utcTimeZone = TimeZone.getTimeZone(UTC_TIME_ZONE)
    public static final String DATE_FORMAT = "dd/MM/yyyy"
    public static final String DATE_FORMAT_FRONTEND = "dd-MMM-yyyy"
    public static final String REFERENCE_CODE_DATE_FORMAT = "ddMMyy"
    public static final String NEW_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    public static final String STANDARD_DATE_FORMAT = "HH:mm:ss dd/MM/yyyy"
    public static final String DNATA_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"
    public static final String BOOKING_MANAGER_DATE_FORMAT = "dd-MMM-yyyy"
    public static final String COUPON_ERROR_DATE_FORMAT = "dd-MMM-yyyy HH:mm:ss"
    public static final String INSTAGRAM_DATE_FORMAT = "dd/MM/yyyy HH:mm:ss"

    public static Date getCurrentDate() {
        new Date()
    }

    public static Long getCurrentDateTimestamp() {
        new Date().getTime()
    }

    public static TimeZone getDefaultTimeZone(){
        return TimeZone.getDefault()
    }

    public static void setDubaiTimeZoneAsDefault() {
//        TimeZone.setDefault(dubaiTimeZone)
        TimeZone.setDefault(utcTimeZone)
    }

    public static void setUTCTimeZoneAsDefault() {
        TimeZone.setDefault(utcTimeZone)
    }

    public static Long convertToNewTimeZone(Long time, TimeZone oldTimeZone, TimeZone newTimeZone) {

        long newMilliSeconds = time - oldTimeZone.rawOffset + newTimeZone.rawOffset
        Date convertedDate = new Date(newMilliSeconds)
        return convertedDate.getTime()
    }

    public static Date convertToNewTimeZone(Date date, TimeZone oldTimeZone, TimeZone newTimeZone) {

        long newMilliSeconds = date.time - oldTimeZone.rawOffset + newTimeZone.rawOffset
        Date convertedDate = new Date(newMilliSeconds)
        return convertedDate
    }

    public static Long convertDubaiTimeToUTCTime(Long time) {
        return convertToNewTimeZone(time, dubaiTimeZone, utcTimeZone)
    }

    public static Long convertUTCTimeToDubaiTime(Long time) {
        return convertToNewTimeZone(time, utcTimeZone, dubaiTimeZone)
    }

    public static Date convertDubaiTimeToUTCTime(Date date) {
        return convertToNewTimeZone(date, dubaiTimeZone, utcTimeZone)
    }

    public static Date convertUTCTimeToDubaiTime(Date date) {
        return convertToNewTimeZone(date, utcTimeZone, dubaiTimeZone)
    }

    public static Date convertStringToDateFormat(String date) {
        Date formattedDate
        if (date) {
            DateFormat formatter = new SimpleDateFormat(NEW_DATE_FORMAT)
            formattedDate = formatter.parse(date)
        }
        return formattedDate
    }

    public static Date convertStringToDateFormat(String date, String format) {
        Date formattedDate
        if (date) {
            DateFormat formatter = new SimpleDateFormat(format)
            formattedDate = formatter.parse(date)
        }
        return formattedDate
    }

    public static String convertDateToStringFormat(Date date) {
        return date.format(NEW_DATE_FORMAT)
    }

    public static String convertDateToUTCStringFormat(Date date) {
        return date.format(STANDARD_DATE_FORMAT) + " " + UTC_TIME_ZONE
    }

    public static String getCurrentDateInUTCStringFormat() {
        return new Date().format(STANDARD_DATE_FORMAT) + " " + UTC_TIME_ZONE
    }

    public static String convertDateToString(Date date) {
        return date.format(DATE_FORMAT)
    }

    public static String convertDateToString(Date date, String format) {
        return date.format(format)
    }

    public static String convertUTCTimeStampToStringFormat(Long time) {
        Date date = new Date(convertUTCTimeToDubaiTime(time))
        return date.format(DATE_FORMAT)
    }

    public static Date convertUTCTimeStampToDateFormat(Long time) {
        return new Date(convertUTCTimeToDubaiTime(time))
    }

    public static String convertDubaiTimeStampToStringFormat(Long time) {
        return new Date(time).format(DATE_FORMAT)
    }

    public static String convertDateToBookingManagerDateFormat(Date date) {
        return (date != null) ? date.format(BOOKING_MANAGER_DATE_FORMAT) : null
    }

    public static Date fetchDateWithMaximumTimeStamp(Date date) {
        date.set(hourOfDay: 23, minute: 59, second: 59)
        return date
    }

    public static Date fetchDateWithMinimumTimeStamp(Date date) {
        date.set(hourOfDay: 0, minute: 0, second: 0)
        return date
    }

    public static Date getDateAfterDays(Date date, int day) {
        Calendar calendar = Calendar.getInstance()
        calendar.setTime(date)
        calendar.add(Calendar.DATE, day)
        calendar.getTime()
    }

    public static Date getDateBeforeDays(Date date, int day) {
        Calendar calendar = Calendar.getInstance()
        calendar.setTime(date)
        calendar.add(Calendar.DATE, -day)
        calendar.getTime()
    }

    public static Date getDateBeforeHours(Date date = new Date(), int hours) {
        Calendar calendar = Calendar.getInstance()
        calendar.setTime(date)
        calendar.add(Calendar.HOUR_OF_DAY, -hours)
        calendar.getTime()
    }

    public static String getDNataSpecificDate(Long timeInMillSeconds) {
        if (!timeInMillSeconds) {
            throw new IllegalArgumentException("The TimeInMillSeconds must not be null");
        }
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(UTC_TIME_ZONE))
        calendar.setTimeInMillis(timeInMillSeconds)
        calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH)
    }

    public static String getDNataSpecificDate(Date date) {
        if (!date) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(UTC_TIME_ZONE))
        calendar.setTime(date)
        calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH)
    }

    public static Date getDateAfterMonth(Integer month, Date date) {
        Calendar calendar = Calendar.getInstance()
        calendar.setTime(date)
        calendar.add(Calendar.MONTH, month)
        calendar.getTime()
    }

    public static boolean compareDays(Date date1, Date date2, String comparator) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        if (comparator.equals("equals")) return isSameDay(cal1, cal2);
        else if (comparator.equals("earlier")) return isEarlierDay(cal1, cal2);
        else if (comparator.equals("later")) return isLaterDay(cal1, cal2);
    }

    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }

    public static boolean isEarlierDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return ((cal1.get(Calendar.YEAR) < cal2.get(Calendar.YEAR)) ||
                (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) < cal2.get(Calendar.DAY_OF_YEAR)));
    }

    public static boolean isLaterDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return ((cal1.get(Calendar.YEAR) > cal2.get(Calendar.YEAR)) ||
                (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) > cal2.get(Calendar.DAY_OF_YEAR)));
    }

    public static boolean le(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return ((cal1.get(Calendar.YEAR) < cal2.get(Calendar.YEAR)) ||
                (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) <= cal2.get(Calendar.DAY_OF_YEAR)));
    }

    public static boolean ge(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return ((cal1.get(Calendar.YEAR) > cal2.get(Calendar.YEAR)) ||
                (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) >= cal2.get(Calendar.DAY_OF_YEAR)));
    }

    public static boolean liesInRange(Date date, Date fromDate, Date toDate) {
        if (date == null || fromDate == null || toDate == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Calendar fromCal = Calendar.getInstance();
        fromCal.setTime(fromDate);
        Calendar toCal = Calendar.getInstance();
        toCal.setTime(toDate);
        return (ge(cal, fromCal) && le(cal, toCal));
    }

    public static Date getDateWithEndTimeOfTheDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, 23)
        cal.add(Calendar.MINUTE, 59)
        cal.add(Calendar.SECOND, 59)
        return cal.getTime()
    }

    public static Time parseToSqlTime(String localTime, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern); //if 24 hour format
//        SimpleDateFormat format1 = new SimpleDateFormat("HH:mm"); // 12 hour format
        Date date = format.parse(localTime);
        Time time = new Time(date.getTime());
        return time;
    }

    public static Time parseToSqlTime(Date date) {
        SimpleDateFormat timeFormat = new SimpleDateFormat(DTDateTimeFormats.EVENT_LOCAL_TIME_FORMAT);
        String time = timeFormat.format(date);
        date = timeFormat.parse(time);
        return new Time(date.getTime());
    }

    public static String parseSqlTimeToString(Time time) {
        Date date = new Date(time.getTime())
        SimpleDateFormat timeFormat = new SimpleDateFormat(DTDateTimeFormats.EVENT_LOCAL_TIME_FORMAT);
        String timeInString = timeFormat.format(date)
        return timeInString
    }

    public static Date convertMillisToDate(Long millis) {
        new Date(millis)
    }

    public static Integer getTimeInterval(final Date startDate, final Date endDate, final TimeInterval timeInterval) {
        def duration
        if (startDate && endDate) {
            use(groovy.time.TimeCategory) {
                duration = endDate - startDate
            }
            if (timeInterval.equals(TimeInterval.DAYS)) {
                return duration.days
            }

            if (timeInterval.equals(TimeInterval.HOURS)) {
                return duration.hours
            }
        }
        return null
    }

    public static String getDNataSpecificDate(Date date, Integer hourOfDay, Integer minute) {
        if (!date) {
            throw new IllegalArgumentException("The date must not be null");
        }
        hourOfDay = hourOfDay ?: 0
        minute = minute ?: 0
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(UTC_TIME_ZONE))
        calendar.setTime(date)
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)
        calendar.format(DNATA_DATE_FORMAT)
    }
}
