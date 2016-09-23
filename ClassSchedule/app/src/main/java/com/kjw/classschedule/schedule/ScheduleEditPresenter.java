package com.kjw.classschedule.schedule;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;

import com.kjw.classschedule.ClassInfo;
import com.kjw.classschedule.IPresenter;
import com.kjw.classschedule.R;
import com.kjw.classschedule.ScheduleDataSource;
import com.kjw.classschedule.ScheduleDataSourceImpl;
import com.kjw.classschedule.util.Constants;
import com.kjw.classschedule.util.ThreadUtil;

import java.util.List;

/**
 * Created by jwkuang on 2016/9/18.
 */
public class ScheduleEditPresenter implements View.OnClickListener, AdapterView.OnItemSelectedListener, IPresenter, AdapterView.OnItemClickListener{
    private static String TAG = "ScheduleEditPresenter";
    private ScheduleDataSource mDataSource;
    private ScheduleEditView mView;
    private int mCurrentDay;
    private Handler mFileHandler;
    private Handler mUIHandler;
    public ScheduleEditPresenter(ScheduleEditView view){
        mView = view;
        mDataSource = ScheduleDataSourceImpl.getInstance();
        mFileHandler = new Handler(ThreadUtil.getFileThread().getLooper());
        mUIHandler = new Handler(Looper.getMainLooper());
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
                mView.openEditClassActivity(mCurrentDay, -1);
                break;
            case R.id.edit_save:
                saveAllClass();
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
        mDataSource = null;
        mView = null;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        mView.openEditClassActivity(mCurrentDay, i);
    }

    public void onResume(){
        mView.updateList(mDataSource.getDataList(mCurrentDay));
    }

    private void saveAllClass(){
        mFileHandler.post(new Runnable() {
            @Override
            public void run() {
                final int result = mDataSource.saveMainData();
                mUIHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        switch (result){
                            case Constants.SAVE_CLASS_RESULT_SUCCESS:
                                mView.saveSuccess();
                                break;
                            case Constants.SAVE_CLASS_RESULT_EMPTY:
                                mView.showToast(R.string.save_empty);
                                break;
                            case Constants.SAVE_CLASS_RESULT_FAIL:
                                mView.showToast(R.string.save_fail);
                                break;
                            default:
                                break;
                        }
                    }
                });
            }
        });
    }
}
