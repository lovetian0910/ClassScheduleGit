package com.kjw.classschedule;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jwkuang on 2016/9/13.
 */
public interface ScheduleDataSource {

    List<ClassInfo> getDataList(int day);

    List<ClassInfo> getTempDataList(int year, int month, int day);

    boolean hasTempData(int year, int month, int day);

    boolean setMainData(ArrayList<ClassInfo> dataList);

    int saveMainData();

    void onDestroy();
}
