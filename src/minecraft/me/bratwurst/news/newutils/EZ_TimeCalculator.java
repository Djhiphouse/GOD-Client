package me.bratwurst.news.newutils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EZ_TimeCalculator {
    public static String getCurrentTime() {
        SimpleDateFormat dff = new SimpleDateFormat("HH:mm");
        Date todayy = Calendar.getInstance().getTime();
        String renderTime = dff.format(todayy);
        return renderTime;
    }

    public static String getCurrentDate() {
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Date today = Calendar.getInstance().getTime();
        String renderDate = df.format(today);
        return renderDate;
    }
}
