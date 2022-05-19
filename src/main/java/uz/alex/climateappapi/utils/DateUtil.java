package uz.alex.climateappapi.utils;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {
    private static String pattern = "yyyy-MM-dd HH:mm:ss";
    private static String pattern2 = "yyyy-MM-dd";
    private static String pattern3 = "dd-MM-yyyy";
    private static String pattern4 = "dd-MMMM";
    private static String pattern5 = "HH:mm";
    private static String pattern6 = "yyyy/MM/dd";
    private static String pattern7 = "yyyyMMddHHmmss";
    private static String pattern8 = "dd-MM-yyyy HH:mm";
    private static String pattern9 = "MMM_dd_YYYY";
    private static SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    private static SimpleDateFormat sdf2 = new SimpleDateFormat(pattern2);
    private static SimpleDateFormat sdf3 = new SimpleDateFormat(pattern3);
    private static SimpleDateFormat sdf4 = new SimpleDateFormat(pattern4);
    private static SimpleDateFormat sdf5 = new SimpleDateFormat(pattern5);
    private static SimpleDateFormat sdf6 = new SimpleDateFormat(pattern6);
    private static SimpleDateFormat sdf7 = new SimpleDateFormat(pattern7);
    private static SimpleDateFormat sdf8 = new SimpleDateFormat(pattern8);
    private static SimpleDateFormat sdf9 = new SimpleDateFormat(pattern9);


    public static String format(Date date) {
        if (date == null) return null;
        return sdf.format(date);
    }

    public static String format2(Date date) {
        if (date == null) return null;
        return sdf2.format(date);
    }

    public static String format3(Date date) {
        if (date == null) return null;
        return sdf3.format(date);
    }

    public static String format4(Date date) {
        if (date == null) return null;
        return sdf4.format(date);
    }

    public static String convertToStr_dd_MM_yyyy_HH_mm(Date date) {
        if (date == null) return null;
        return sdf8.format(date);
    }

    public static String format4(Date date, Locale locale) {
        if (date == null) return null;
        return new SimpleDateFormat(pattern4, locale).format(date);
    }

    public static String format5(Date date) {
        if (date == null) return null;
        return sdf5.format(date);
    }

    public static String format6(Date date) {
        if (date == null) return null;
        return sdf6.format(date);
    }

    public static String format7(Date date) {
        if (date == null) return null;
        return sdf7.format(date);
    }

    public static Date parse(String value) {
        if (value == null) return null;
        try {
            return sdf.parse(value);
        } catch (ParseException e) {
        }
        return null;
    }

    public static Date parse2(String value) {
        if (value == null) return null;
        try {
            return sdf2.parse(value);
        } catch (ParseException e) {
        }
        return null;
    }

    public static Date parse3(String value) {
        if (value == null) return null;
        try {
            return sdf3.parse(value);
        } catch (ParseException e) {
        }
        return null;
    }

    public static Date parse8(String value) {
        if (value == null) return null;
        try {
            return sdf8.parse(value);
        } catch (ParseException e) {
        }
        return null;
    }

    public static String format9(Date date) {
        if (date == null) return null;
        return sdf9.format(date);
    }

    public static Date parse(String value, String format, Date defaultDate) {
        if (value == null) return null;

        SimpleDateFormat dateFormat = new SimpleDateFormat(format);

        try {
            return dateFormat.parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return defaultDate;

    }

    public static Date atEndOfDay(Date date) {
        if (date == null) return null;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static Date atStartOfDay(Date date) {
        if (date == null) return null;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date atStartOfMonth(Date date) {
        if (date == null) return null;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    public static Date earlyMonth(Date date) {
        if (date == null) return null;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTime();
    }

    public static Date today() {
        Date now = new Date();

        return new Date(now.getDate(), now.getMonth(), now.getDate());
    }

    public static Date addDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        date = c.getTime();
        return date;
    }

    public static Date addMonths(Date date, int month) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, month);
        date = c.getTime();
        return date;
    }

    public static int year(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    public static int dayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    public static String[] weekdays() {
        Locale usersLocale = Locale.ENGLISH;

        DateFormatSymbols dfs = new DateFormatSymbols(usersLocale);
        return dfs.getWeekdays();
    }

    /**
     * hafta kunlarini quyidagi tartibda qaytaradi
     * 0=""
     * 1=SUNDAY
     * 2=MONDAY
     * 3=TUESDAY
     * 4=WEDNESDAY
     * 5=THURSDAY
     * 6=FRIDAY
     * 7=SATURDAY
     *
     * @return
     */
    public static Map<Integer, String> weekdaysAsMap() {
        Map<Integer, String> result = new TreeMap<>();
        for (int i = 0; i < weekdays().length; i++) {
            result.put(i, weekdays()[i]);
        }
        return result;
    }

    /**
     * hafta kunlarini startDayOfWeek kunidan boshlab qaytaradi
     * masalan: startDayOfWeek=3 bo`lsa
     * 3=TUESDAY
     * 4=WEDNESDAY
     * 5=THURSDAY
     * 6=FRIDAY
     * 7=SATURDAY
     * 0=""
     * 1=SUNDAY
     * 2=MONDAY
     *
     * @param startDayOfWeek
     * @return
     */
    public static Map<Integer, String> weekdaysAsMap2(int startDayOfWeek) {
        Map<Integer, String> result = new LinkedHashMap<>();
        for (int i = startDayOfWeek; i < weekdays().length; i++) {
            result.put(i, weekdays()[i]);
        }
        for (int i = 0; i < startDayOfWeek; i++) {
            result.put(i, weekdays()[i]);
        }
        return result;
    }

    public static Map<Integer, Map<String, Object>> weekdaysAsMap3(int startDayOfWeek, Locale locale) {
        Map<Integer, Map<String, Object>> result = new LinkedHashMap<>();
        for (int i = startDayOfWeek; i < weekdays().length; i++) {
            Map<String, Object> weekday = new HashMap<>();
            weekday.put("name", weekdays()[i]);
            weekday.put("date", DateUtil.format4(DateUtil.futureFirstDay(i), locale));
            result.put(i, weekday);
        }
        for (int i = 0; i < startDayOfWeek; i++) {
            Map<String, Object> weekday = new HashMap<>();
            weekday.put("name", weekdays()[i]);
            weekday.put("date", DateUtil.format4(DateUtil.futureFirstDay(i), locale));
            result.put(i, weekday);
        }
        return result;
    }

    /**
     * dayOfWeek ni qiymati 0 - 7 gacha, quyida kunlar tartibi
     * 0=""
     * 1=SUNDAY
     * 2=MONDAY
     * 3=TUESDAY
     * 4=WEDNESDAY
     * 5=THURSDAY
     * 6=FRIDAY
     * 7=SATURDAY
     *
     * @param dayOfWeek
     * @return dayOfWeek ga mos, oldinda kelayotgan kunni sanasini qaytaradi
     */
    public static Date futureFirstDay(int dayOfWeek) {
        if (dayOfWeek <= 0) return new Date();

        int currentDayOfWeek = DateUtil.dayOfWeek(new Date());
        if (dayOfWeek >= currentDayOfWeek)
            return DateUtil.addDays(new Date(), dayOfWeek - currentDayOfWeek);
        else
            return DateUtil.addDays(new Date(), 7 + dayOfWeek - currentDayOfWeek);
    }


    public static int getLastWeekInMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
    }

    public static Date getCalendarNextDayFromNow(Date date, int c) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Integer hour = cal.get(Calendar.HOUR_OF_DAY);

        Calendar calNext = Calendar.getInstance();
        calNext.set(Calendar.MINUTE, 0);
        calNext.set(Calendar.SECOND, 0);
        calNext.set(Calendar.HOUR_OF_DAY, hour);
        calNext.add(Calendar.DAY_OF_MONTH, c);

        calNext.getTime();
        return calNext.getTime();
    }
}
