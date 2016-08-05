package utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Yao on 2016/8/5.
 */
public class DateUtils {

    public static Date changeDateMinute(int time){
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, time);
        date = c.getTime();
        return date;
    }
}
