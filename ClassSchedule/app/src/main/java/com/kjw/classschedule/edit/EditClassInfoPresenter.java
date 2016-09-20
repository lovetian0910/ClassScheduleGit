package com.kjw.classschedule.edit;

import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import com.kjw.classschedule.ClassInfo;
import com.kjw.classschedule.IPresenter;
import com.kjw.classschedule.R;
import com.kjw.classschedule.ScheduleDataSource;
import com.kjw.classschedule.ScheduleDataSourceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jwkuang on 2016/9/18.
 */
public class EditClassInfoPresenter implements IPresenter, AdapterView.OnItemSelectedListener, View.OnClickListener{
    private static String TAG = "EditClassInfoPresenter";
    private EditClassInfoView mView;
    private ScheduleDataSource mDataSource;
    private int mCurrStartHour;
    private int mCurrStartMinute;

    private int mDay;
    private int mIndex;

    private ClassInfo mInfo;

    public EditClassInfoPresenter(EditClassInfoView view){
        mView = view;
        mDataSource = ScheduleDataSourceImpl.getInstance();
    }

    public void init(int day, int index){
        mView.initViews();
        mDay = day;
        mIndex = index;
        if(index >= 0){
            mInfo = mDataSource.getDataList(day).get(index).copy();
            setStartTime(mInfo.startTime.get(Calendar.HOUR_OF_DAY), mInfo.startTime.get(Calendar.MINUTE));
            mView.setDuration(mInfo.duration);
            mView.setName(mInfo.name);
            mView.setType(mInfo.type);
        }else {
            mInfo = new ClassInfo();
        }
    }

    public void setStartTime(int hour, int minute){
        mCurrStartHour = hour;
        mCurrStartMinute = minute;
        Date date = new Date(1970, 1, 1, hour, minute);
        SimpleDateFormat format = (SimpleDateFormat) DateFormat.getDateInstance();
        format.applyPattern("HH:mm");
        mView.updateTime(format.format(date));
    }

    @Override
    public void init() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(mInfo != null) {
            mInfo.type = position;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.edit_class_start_time:
                mView.openTimePicker(mCurrStartHour, mCurrStartMinute);
                break;
            case R.id.edit_class_save:
                saveInfo();
                break;
            default:
                break;
        }
    }

    private void saveInfo(){
        String name = mView.getName();
        if(TextUtils.isEmpty(name)){
            mView.showToast(R.string.no_name);
            return;
        }
        String duration = mView.getDuration();
        if(TextUtils.isEmpty(duration)){
            mView.showToast(R.string.no_duration);
            return;
        }
        if(mCurrStartHour == 0){
            mView.showToast(R.string.no_time);
            return;
        }

    }
}
