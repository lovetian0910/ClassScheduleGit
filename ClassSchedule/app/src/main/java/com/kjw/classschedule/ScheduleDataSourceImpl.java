package com.kjw.classschedule;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.kjw.classschedule.util.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by jwkuang on 2016/9/14.
 */
public class ScheduleDataSourceImpl implements ScheduleDataSource{
    private String TAG = "ScheduleDataSourceImpl";
    private List<List<ClassInfo>> mMainScheduleList;
    private AtomicBoolean isDataReady = new AtomicBoolean(false);

    private final String MAIN_SCHEDULE_FILE_PATH = Environment.getExternalStorageDirectory() + File.separator
            + "kjw/ClassSchedule/MainSchedule.json";
    @Override
    public List<ClassInfo> getDataList(int day) {
        if(!isDataReady.get()){
            if(!initData()){
                return null;
            }
        }
        if(mMainScheduleList != null && mMainScheduleList.size() > day){
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
        return false;
    }

    @Override
    public boolean setMainData(ArrayList<ClassInfo> dataList) {
        return false;
    }

    private boolean initData(){
        File file = new File(MAIN_SCHEDULE_FILE_PATH);
        if(!file.exists()){
            return false;
        } else {
            String jsonStr = FileUtil.readFileContent(file);
            if(TextUtils.isEmpty(jsonStr)){
                return false;
            }
        }
        return false;
    }
}
