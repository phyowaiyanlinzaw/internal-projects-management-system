package team.placeholder.internalprojectsmanagementsystem.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class ManMonthCalculator {

    public static String getStartMonthFromDate(long timestamp){
        Date date = new Date(timestamp);
        return date.toString().substring(4,7);
    }

    public static String getEndMonthFromDate(long timestamp){
        Date date = new Date(timestamp);
        return date.toString().substring(4,7);
    }

    public static int getStartYearFromDate(long timestamp){
        Date date = new Date(timestamp);
        return Integer.parseInt(date.toString().substring(24,28));
    }

    public static int getEndYearFromDate(long timestamp){
        Date date = new Date(timestamp);
        return Integer.parseInt(date.toString().substring(24,28));
    }

    public static String getMonthYearFromDate(long timestamp) {
        // Assuming 'timestamp' is the number of milliseconds since Epoch
        Date date = new Date(timestamp);
        return new SimpleDateFormat("yyyy-MM", Locale.ENGLISH).format(date);
    }

    public static boolean isMonthYearInRange(int taskYear, int taskMonth, int startYear, int startMonth, int endYear, int endMonth) {
        // Check if the task's month-year is within the specified range
        return (taskYear > startYear || (taskYear == startYear && taskMonth >= startMonth))
                && (taskYear < endYear || (taskYear == endYear && taskMonth <= endMonth));
    }

    public static boolean isMonthInRange (String startMonth, String endMonth, String month){
        String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        int start = 0;
        int end = 0;
        int current = 0;
        for(int i = 0; i < months.length; i++){
            if(months[i].equals(startMonth)){
                start = i;
            }
            if(months[i].equals(endMonth)){
                end = i;
            }
            if(months[i].equals(month)){
                current = i;
            }
        }
        return current >= start && current <= end;
    }

    public static long calculateManHours(long startTime,long endTime){
        long difference = endTime - startTime;
        return TimeUnit.MILLISECONDS.toHours(difference);
    }

    public static long getEndOfMonth(Calendar calendar) {
        // Set the calendar to the last day of the month
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        // Set the time to the end of the day
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        // Return the time in milliseconds
        return calendar.getTimeInMillis();
    }
}
