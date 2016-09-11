package com.kjw.classschedule;

/**
 * Created by Administrator on 2016/9/11.
 */
public class SchedulePresenter {
    private static final String TAG = "SchedulePresenter";
    private ScheduleView mView;

    public SchedulePresenter(ScheduleView view){
        mView = view;
        mView.initViews();
    }
    public void onClick(int id){
        switch (id){
            case R.id.main_date:
                mView.showDatePicker();
                break;
            case R.id.main_prevday:
                break;
            case R.id.main_nextday:
                break;
            case R.id.main_menu_edit:
                break;
            case R.id.main_menu_schedule:
                break;
            case R.id.main_day:
                break;
            default:
                break;
        }
    }
}
