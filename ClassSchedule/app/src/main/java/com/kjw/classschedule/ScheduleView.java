package com.kjw.classschedule;

/**
 * Created by Administrator on 2016/9/11.
 */
public interface ScheduleView {
    void initViews();

    void showDatePicker(int year, int month);

    void changeDate(String date);

    void changeDay(String day);

    void updateList();

    void openEditTodayActivity();

    void openEditMainActivity();

}
