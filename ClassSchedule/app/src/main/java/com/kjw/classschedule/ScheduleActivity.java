package com.kjw.classschedule;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.List;

import cn.aigestudio.datepicker.cons.DPMode;
import cn.aigestudio.datepicker.views.DatePicker;

public class ScheduleActivity extends AppCompatActivity implements View.OnClickListener, ScheduleView{
    private FloatingActionButton mEditButton;
    private FloatingActionButton mScheduleButton;
    private TextView mDate;
    private TextView mPrevDay;
    private TextView mNextDay;
    private TextView mDay;
    private ListView mClassList;
    private ClassListAdapter mAdapter;

    private SchedulePresenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        getSupportActionBar().hide();
        mPresenter = new SchedulePresenter(this);
    }

    @Override
    public void initViews(){
        mEditButton = (FloatingActionButton) super.findViewById(R.id.main_menu_edit);
        mEditButton.setOnClickListener(this);
        mScheduleButton = (FloatingActionButton) super.findViewById(R.id.main_menu_schedule);
        mScheduleButton.setOnClickListener(this);

        mDate = (TextView)super.findViewById(R.id.main_date);
        mDate.setOnClickListener(this);
        mPrevDay = (TextView)super.findViewById(R.id.main_prevday);
        mPrevDay.setOnClickListener(this);
        mNextDay = (TextView)super.findViewById(R.id.main_nextday);
        mNextDay.setOnClickListener(this);
        mDay = (TextView)super.findViewById(R.id.main_day);
        mDay.setOnClickListener(this);

        mClassList = (ListView)super.findViewById(R.id.main_class_list);
        mAdapter = new ClassListAdapter(this);
        mClassList.setAdapter(mAdapter);
    }
    @Override
    public void onClick(View v) {
        mPresenter.onClick(v.getId());
    }

    @Override
    public void showDatePicker(int year, int month) {
        final AlertDialog dialog = new AlertDialog.Builder(ScheduleActivity.this).create();
        dialog.show();
        DatePicker picker = new DatePicker(ScheduleActivity.this);
        picker.setDate(2016, 9);
        picker.setMode(DPMode.SINGLE);
        picker.setOnDatePickedListener(new DatePicker.OnDatePickedListener() {
            @Override
            public void onDatePicked(String date) {
                mPresenter.changeDate(date);
                dialog.dismiss();
            }
        });
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setContentView(picker, params);
        dialog.getWindow().setGravity(Gravity.CENTER);
    }

    @Override
    public void changeDate(String date) {
        mDate.setText(date);
    }

    @Override
    public void changeDay(String day) {
        mDay.setText(day);
    }

    @Override
    public void updateList(List<ClassInfo> classList) {
        mAdapter.setData(classList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void openEditTodayActivity() {

    }

    @Override
    public void openEditMainActivity() {

    }
}
