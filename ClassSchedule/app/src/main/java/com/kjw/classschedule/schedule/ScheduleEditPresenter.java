package com.kjw.classschedule.schedule;

import android.view.View;
import android.widget.AdapterView;

import com.kjw.classschedule.ClassInfo;
import com.kjw.classschedule.IPresenter;
import com.kjw.classschedule.R;
import com.kjw.classschedule.ScheduleDataSource;
import com.kjw.classschedule.ScheduleDataSourceImpl;

import java.util.List;

/**
 * Created by jwkuang on 2016/9/18.
 */
public class ScheduleEditPresenter implements View.OnClickListener, AdapterView.OnItemSelectedListener, IPresenter{
    private static String TAG = "ScheduleEditPresenter";
    private ScheduleDataSource mDataSource;
    private ScheduleEditView mView;
    private int mCurrentDay;
    public ScheduleEditPresenter(ScheduleEditView view){
        mView = view;
        mDataSource = ScheduleDataSourceImpl.getInstance();
    }

    public void init(){
        mView.initViews();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.edit_prev_day:
                mCurrentDay--;
                if(mCurrentDay < 0){
                    mCurrentDay = 6;
                }
                mView.changeDay(mCurrentDay);
                break;
            case R.id.edit_next_day:
                mCurrentDay++;
                if(mCurrentDay > 6){
                    mCurrentDay = 0;
                }
                mView.changeDay(mCurrentDay);
                break;
            case R.id.edit_add_class:
                break;
            case R.id.edit_save:
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mCurrentDay = position;
        List<ClassInfo> classInfoList = mDataSource.getDataList(position);
        mView.updateList(classInfoList);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onDestroy() {

    }
}
