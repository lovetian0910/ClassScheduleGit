package com.kjw.classschedule;

import org.json.JSONObject;

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

    public ClassInfo copy(){
        ClassInfo result = new ClassInfo();
        result.startTime = Calendar.getInstance();
        result.startTime.setTime(startTime.getTime());
        result.duration = duration;
        result.name = name;
        result.type = type;
        result.isTemp = isTemp;
        return result;
    }

    public JSONObject toJSONObject() throws Exception{
        JSONObject classObj = new JSONObject();
        classObj.put("startTime", startTime.getTimeInMillis());
        classObj.put("duration", duration);
        classObj.put("name", name);
        classObj.put("type", type);
        classObj.put("isTemp", isTemp ? 1 : 0);
        return classObj;
    }

}
