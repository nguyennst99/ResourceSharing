/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennst.utils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author nguyennst
 */
public class DateUtil implements Serializable{
    
    public static boolean checkDateAfter(String checkDate) throws Exception {
        boolean result = true;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateCheck = sdf.parse(checkDate);
        Date date = new Date();
        String dateStr = sdf.format(date);
        Date today = sdf.parse(dateStr);
        if (dateCheck.before(today)) {
            result = false;
        }
        return result;
    }

    public static boolean checkDateInOut(String checkDateIn, String checkDateOut) throws Exception {
        boolean result = true;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateIn = sdf.parse(checkDateIn);
        Date dateOut = sdf.parse(checkDateOut);
        int temp = dateIn.compareTo(dateOut);
        if (temp == 0) {
            result = false;
        }
        if (temp > 0) {
            result = false;
        }
        return result;
    }
    
    public static String getToday() {
        Date date = new Date();
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
        String timeStr = spf.format(date);

        return timeStr;
    }

    public static String getTomorrow() {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        date = c.getTime();
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
        String timeStr = spf.format(date);

        return timeStr;
    } 
}
