package com.kjw.classschedule.edit;

import com.kjw.classschedule.IPresenter;
import com.kjw.classschedule.ScheduleDataSource;
import com.kjw.classschedule.ScheduleDataSourceImpl;

/**
 * Created by jwkuang on 2016/9/18.
 */
public class EditClassInfoPresenter implements IPresenter{
    private static String TAG = "EditClassInfoPresenter";
    private EditClassInfoView mView;
    private ScheduleDataSource mDataSource;

    public EditClassInfoPresenter(EditClassInfoView view){
        mView = view;
        mDataSource = ScheduleDataSourceImpl.getInstance();
    }

    public void init(){
        mView.initViews();
    }
    @Override
    public void onDestroy() {

    }
}
