package com.kjw.classschedule.edit;

import com.kjw.classschedule.ClassInfo;

import java.util.List;

/**
 * Created by jwkuang on 2016/9/18.
 */
public interface EditView {
    void initViews();

    void changeDay(int day);

    void updateList(List<ClassInfo> classList);
}
