package com.kjw.classschedule;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/9/11.
 */
public class SchedulePresenter {
    private static final String TAG = "SchedulePresenter";
    private ScheduleView mView;
    private int mYear;
    private int mMonth;
    private int mDay;

    public SchedulePresenter(ScheduleView view){
        mView = view;
        mView.initViews();
        mView.changeDate(initDate());
        mView.changeDay(calDay());
    }

    public void changeDate(String dateStr){
        SimpleDateFormat dateFormat = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
        dateFormat.applyPattern("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(dateStr);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            if(cal.get(Calendar.YEAR) == mYear && cal.get(Calendar.MONTH) == mMonth
                    && cal.get(Calendar.DAY_OF_MONTH) == mDay){
                return;
            }else {
                mYear = cal.get(Calendar.YEAR);
                mMonth = cal.get(Calendar.MONTH);
                mDay = cal.get(Calendar.DAY_OF_MONTH);
                dateFormat.applyPattern("yyyy年MM月dd日");
                String resultDate = dateFormat.format(date);
                mView.changeDate(resultDate);
                mView.changeDay(calDay());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void onClick(int id){
        switch (id){
            case R.id.main_date:
                mView.showDatePicker(mYear, mMonth);
                break;
            case R.id.main_prevday:
                prevDay();
                break;
            case R.id.main_nextday:
                nextDay();
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

    private void updateDateField(Calendar cal){
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);
    }
    private String initDate(){
        long currTime = System.currentTimeMillis();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(currTime);
        updateDateField(cal);
        SimpleDateFormat format = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
        format.applyPattern("yyyy年MM月dd日");
        return format.format(cal.getTime());
    }

    private void nextDay(){
        Calendar cal = Calendar.getInstance();
        cal.set(mYear, mMonth, mDay);
        cal.add(Calendar.DAY_OF_MONTH , 1);
        updateDateField(cal);
        mView.changeDate(currentDate2String(cal));
        mView.changeDay(calDay());
    }

    private void prevDay(){
        Calendar cal = Calendar.getInstance();
        cal.set(mYear, mMonth, mDay);
        cal.add(Calendar.DAY_OF_MONTH , -1);
        updateDateField(cal);
        mView.changeDate(currentDate2String(cal));
        mView.changeDay(calDay());
    }
    private static String[] DAYS_IN_CHN = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    private String calDay(){
        Calendar cal = Calendar.getInstance();
        cal.set(mYear, mMonth, mDay);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        if(day > 0 && day <=7){
            return DAYS_IN_CHN[day-1];
        }
        return null;
    }

    private String currentDate2String(Calendar cal){
        SimpleDateFormat format = (SimpleDateFormat) DateFormat.getDateInstance();
        format.applyPattern("yyyy年MM月dd日");
        return format.format(cal.getTime());
    }
}
