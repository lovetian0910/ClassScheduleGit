package com.kjw.classschedule;

import java.util.Calendar;

/**
 * Created by jwkuang on 2016/9/13.
 */
public class ClassInfo {
    public Calendar startTime;
    public int duration;
    public String name;
    public int type;
    public boolean isTemp;

    public void setStartTime(int hour, int minute){
        startTime.set(Calendar.HOUR_OF_DAY, hour);
        startTime.set(Calendar.MINUTE, minute);
    }

}
