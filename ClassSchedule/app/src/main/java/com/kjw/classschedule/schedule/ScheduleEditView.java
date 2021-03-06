package com.kjw.classschedule.schedule;

import com.kjw.classschedule.ClassInfo;

import java.util.List;

/**
 * Created by jwkuang on 2016/9/18.
 */
public interface ScheduleEditView {
    void initViews();

    void changeDay(int day);

    void updateList(List<ClassInfo> classList);

    void openEditClassActivity(int day, int index);

    void saveSuccess();

    void showToast(int stringID);
}
