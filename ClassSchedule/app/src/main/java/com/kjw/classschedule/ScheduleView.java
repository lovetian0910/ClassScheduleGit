package com.kjw.classschedule;

/**
 * Created by Administrator on 2016/9/11.
 */
public interface ScheduleView {
    void initViews();

    void showDatePicker();

    void changeDate();

    void updateList();

    void openEditTodayActivity();

    void openEditMainActivity();

}
