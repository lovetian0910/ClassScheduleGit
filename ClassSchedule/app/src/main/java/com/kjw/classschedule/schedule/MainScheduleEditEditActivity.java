package com.kjw.classschedule.schedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kjw.classschedule.ClassInfo;
import com.kjw.classschedule.ClassListAdapter;
import com.kjw.classschedule.R;
import com.kjw.classschedule.edit.EditClassInfoActivity;
import com.kjw.classschedule.util.Constants;

import java.util.List;

/**
 * Created by jwkuang on 2016/9/16.
 */
public class MainScheduleEditEditActivity extends AppCompatActivity implements ScheduleEditView {
    private ScheduleEditPresenter mPresenter;
    private AppCompatSpinner mDay;
    private TextView mPrevDay;
    private TextView mNextDay;
    private ListView mList;
    private Button mAddButton;
    private Button mSaveButton;

    private ClassListAdapter mAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        mPresenter = new ScheduleEditPresenter(this);
        mPresenter.init();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        mPresenter = null;
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        mPresenter.onResume();
        super.onResume();
    }

    @Override
    public void initViews() {
        mDay = (AppCompatSpinner) super.findViewById(R.id.edit_day);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Constants.DAYS_IN_CHN);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mDay.setAdapter(adapter);
        mDay.setOnItemSelectedListener(mPresenter);
        mPrevDay = (TextView) super.findViewById(R.id.edit_prev_day);
        mPrevDay.setOnClickListener(mPresenter);
        mNextDay = (TextView) super.findViewById(R.id.edit_next_day);
        mNextDay.setOnClickListener(mPresenter);
        mList = (ListView) super.findViewById(R.id.edit_list);
        mAdapter = new ClassListAdapter(this);
        mList.setAdapter(mAdapter);
        mList.setOnItemClickListener(mPresenter);
        mAddButton = (Button) super.findViewById(R.id.edit_add_class);
        mAddButton.setOnClickListener(mPresenter);
        mSaveButton = (Button) super.findViewById(R.id.edit_save);
        mSaveButton.setOnClickListener(mPresenter);
    }

    @Override
    public void changeDay(int day) {
        mDay.setSelection(day);
    }

    @Override
    public void updateList(List<ClassInfo> classList) {
        mAdapter.setData(classList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void openEditClassActivity(int day, int index) {
        Intent intent = new Intent(this, EditClassInfoActivity.class);
        intent.putExtra("day", day);
        intent.putExtra("index", index);
        startActivity(intent);
    }

    @Override
    public void saveSuccess() {
        Toast.makeText(this, R.string.save_success, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void showToast(int stringID) {
        Toast.makeText(this, stringID, Toast.LENGTH_SHORT).show();
    }
}
