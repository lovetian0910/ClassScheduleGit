package com.kjw.classschedule.edit;

/**
 * Created by jwkuang on 2016/9/18.
 */
public interface EditClassInfoView {
    void initViews();

    void openTimePicker(int hour, int minute);

    void updateTime(String time);

    void setName(String name);

    void setType(int type);

    void setDuration(int duration);

    void showToast(int stringRes);

    String getName();
    String getDuration();

    void close();
}
