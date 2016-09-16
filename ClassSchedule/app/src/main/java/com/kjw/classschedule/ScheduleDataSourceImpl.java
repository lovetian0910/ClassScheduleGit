package com.kjw.classschedule;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.kjw.classschedule.util.Constants;
import com.kjw.classschedule.util.FileUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Exchanger;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 课程表数据实现
 * Created by jwkuang on 2016/9/14.
 */
public class ScheduleDataSourceImpl implements ScheduleDataSource{
    private String TAG = "ScheduleDataSourceImpl";
    private Map<Integer, List<ClassInfo>> mMainScheduleList;
    private AtomicBoolean isDataReady = new AtomicBoolean(false);

    private final String MAIN_SCHEDULE_DIR = Environment.getExternalStorageDirectory() + File.separator
            + "kjw/ClassSchedule";
    private final String MAIN_SCHEDULE_FILE_PATH = MAIN_SCHEDULE_DIR + File.separator + "MainSchedule.json";
    @Override
    public List<ClassInfo> getDataList(int day) {
        if(!isDataReady.get()){
            if(!initData()){
                return null;
            }
        }
        if(mMainScheduleList != null){
            return mMainScheduleList.get(day);
        }
        Log.d(TAG, "getDataList fail mMainScheduleList = " + mMainScheduleList);
        return null;
    }

    @Override
    public List<ClassInfo> getTempDataList(int year, int month, int day) {
        return null;
    }

    @Override
    public boolean hasTempData(int year, int month, int day) {
        StringBuilder sb = new StringBuilder();
        sb.append(year);
        sb.append("-");
        sb.append(month).append("-");
        sb.append(day).append(".json");
        File file = new File(MAIN_SCHEDULE_FILE_PATH, sb.toString());
        return file.exists();
    }

    @Override
    public boolean setMainData(ArrayList<ClassInfo> dataList) {
        return false;
    }


    private boolean initData(){
        mMainScheduleList = new HashMap<>();
        File file = new File(MAIN_SCHEDULE_FILE_PATH);
        if(!file.exists()){
            initEmptyData();
            return false;
        } else {
            String jsonStr = FileUtil.readFileContent(file);
            if(TextUtils.isEmpty(jsonStr)){
                file.delete();
                return false;
            }
            try {
                JSONObject jsonObject = new JSONObject(jsonStr);
                for(int i=0; i<Constants.DAYS.length; i++){
                    JSONObject day = jsonObject.optJSONObject(Constants.DAYS[i]);
                    if (day != null) {
                        JSONArray classes = day.optJSONArray("class");
                        List<ClassInfo> classList = new ArrayList<>();
                        for (int j = 0; j < classes.length(); j++) {
                            JSONObject classInfoJson = classes.getJSONObject(j);
                            ClassInfo info = new ClassInfo();
                            info.startTime = Calendar.getInstance();
                            info.startTime.setTimeInMillis(classInfoJson.optLong("startTime"));
                            info.duration = classInfoJson.optInt("duration");
                            info.name = classInfoJson.optString("name");
                            info.type = classInfoJson.optInt("type");
                            info.isTemp = classInfoJson.optInt("isTemp") == 1 ? true : false;
                            classList.add(info);
                        }
                        mMainScheduleList.put(i, classList);
                    }
                }
                return true;
            }catch (Exception e){
                e.printStackTrace();
                file.delete();
            }
        }
        return false;
    }

    private void initEmptyData(){
        try {
            JSONObject jsonObject = new JSONObject();
            for(int i=0;i<Constants.DAYS.length;i++){
                JSONObject dayObjcet = new JSONObject();
                JSONArray jsonArray = new JSONArray();
                JSONObject classObj = new JSONObject();
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, 18);
                calendar.set(Calendar.MINUTE, 0);
                classObj.put("startTime", calendar.getTimeInMillis());
                classObj.put("duration", i*10);
                classObj.put("name", "周畅");
                classObj.put("type", i==0? 0:i%2);
                classObj.put("isTemp", i==0? 0:i%2);
                jsonArray.put(classObj);
                dayObjcet.put("class", jsonArray);
                jsonObject.put(Constants.DAYS[i], dayObjcet);
            }
            FileUtil.writeString2File(MAIN_SCHEDULE_FILE_PATH, jsonObject.toString());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
