package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static Date parseDate(String dateStr) {
        try {
            return DATE_FORMAT.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use the yyyy-mm-dd format.");
            return null;
        }
    }

    public static String addDays(String dateStr, int days) {
        try {
            Date date = DATE_FORMAT.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, days);
            return DATE_FORMAT.format(calendar.getTime());
        } catch (ParseException e) {
            System.out.println("Invalid date format.");
            return dateStr; // Return the original date if parsing fails
        }
    }
}
