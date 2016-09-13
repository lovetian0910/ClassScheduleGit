package com.kjw.classschedule;

import java.util.Calendar;

/**
 * Created by jwkuang on 2016/9/13.
 */
public class ClassInfo {
    public Calendar startTime;
    public Calendar endTime;
    public String name;
    public int type;
    public boolean isTemp;

    public void setStartTime(int hour, int minute){
        startTime.set(Calendar.HOUR_OF_DAY, hour);
        startTime.set(Calendar.MINUTE, minute);
    }

    public void setEndTime(int hour, int minute){
        endTime.set(Calendar.HOUR_OF_DAY, hour);
        endTime.set(Calendar.MINUTE, minute);
    }
}
