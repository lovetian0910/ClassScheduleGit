package com.kjw.classschedule;

import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

public class ScheduleActivity extends AppCompatActivity implements View.OnClickListener, ScheduleView{
    private FloatingActionButton mEditButton;
    private FloatingActionButton mScheduleButton;
    private TextView mDate;
    private TextView mPrevDay;
    private TextView mNextDay;
    private TextView mDay;
    private ListView mClassList;

    private SchedulePresenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
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
    }
    @Override
    public void onClick(View v) {
        mPresenter.onClick(v.getId());
    }

    @Override
    public void showDatePicker() {

    }

    @Override
    public void changeDate() {

    }

    @Override
    public void updateList() {

    }

    @Override
    public void openEditTodayActivity() {

    }

    @Override
    public void openEditMainActivity() {

    }
}
