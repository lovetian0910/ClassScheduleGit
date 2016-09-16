package com.kjw.classschedule;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import com.kjw.classschedule.util.Constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/9/11.
 */
public class SchedulePresenter {
    private static final String TAG = "SchedulePresenter";
    private ScheduleView mView;
    private ScheduleDataSource mDataSource;
    private int mYear;
    private int mMonth;
    private int mDay;
    private HandlerThread mFileThread;
    private Handler mFileHandler;
    private Handler mUIHandler;
    public SchedulePresenter(ScheduleView view){
        mFileThread = new HandlerThread("file");
        mFileThread.start();
        mFileHandler = new Handler(mFileThread.getLooper());
        mUIHandler = new Handler(Looper.getMainLooper());
        mView = view;
        mDataSource = new ScheduleDataSourceImpl();
        mView.initViews();
        mView.changeDate(initDate());
        mView.changeDay(calDayString());
        getData();
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
                mView.changeDay(calDayString());
                getData();
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
        mView.changeDay(calDayString());
        getData();
    }

    private void prevDay(){
        Calendar cal = Calendar.getInstance();
        cal.set(mYear, mMonth, mDay);
        cal.add(Calendar.DAY_OF_MONTH , -1);
        updateDateField(cal);
        mView.changeDate(currentDate2String(cal));
        mView.changeDay(calDayString());
        getData();
    }

    private String calDayString(){
        Calendar cal = Calendar.getInstance();
        cal.set(mYear, mMonth, mDay);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        if(day > 0 && day <=7){
            return Constants.DAYS_IN_CHN[day-1];
        }
        return null;
    }
    private int calDayInt(){
        Calendar cal = Calendar.getInstance();
        cal.set(mYear, mMonth, mDay);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        return day - 1;
    }

    private String currentDate2String(Calendar cal){
        SimpleDateFormat format = (SimpleDateFormat) DateFormat.getDateInstance();
        format.applyPattern("yyyy年MM月dd日");
        return format.format(cal.getTime());
    }

    private void getData(){
        mFileHandler.post(new Runnable() {
            @Override
            public void run() {
                final List<ClassInfo> classList = mDataSource.getDataList(calDayInt());
                mUIHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mView.updateList(classList);
                    }
                });
            }
        });
    }
}
