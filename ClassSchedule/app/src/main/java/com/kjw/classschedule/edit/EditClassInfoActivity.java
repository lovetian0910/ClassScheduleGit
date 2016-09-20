package com.kjw.classschedule.edit;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.kjw.classschedule.R;
import com.kjw.classschedule.util.Constants;

/**
 * Created by jwkuang on 2016/9/18.
 */
public class EditClassInfoActivity extends AppCompatActivity implements EditClassInfoView{
    private EditClassInfoPresenter mPresenter;

    private EditText mName;
    private TextView mStartTime;
    private EditText mDuration;
    private Spinner mTypeSpinner;
    private Button mSaveButton;
    @Override
    public void initViews() {
        mName = (EditText) super.findViewById(R.id.edit_class_name);
        mStartTime = (TextView) super.findViewById(R.id.edit_class_start_time);
        mStartTime.setOnClickListener(mPresenter);
        mDuration = (EditText) super.findViewById(R.id.edit_class_duration);
        mTypeSpinner = (Spinner) super.findViewById(R.id.edit_class_type);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Constants.CLASS_TYPE_CHN);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mTypeSpinner.setAdapter(adapter);
        mTypeSpinner.setOnItemSelectedListener(mPresenter);
        mSaveButton = (Button) super.findViewById(R.id.edit_class_save);
        mSaveButton.setOnClickListener(mPresenter);
    }

    @Override
    public void openTimePicker(int hour, int minute) {
        if(hour == 0){
            hour = 16;
        }
        TimePickerDialog dialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                mPresenter.setStartTime(hourOfDay, minute);
            }
        }, hour, minute, true);
        dialog.show();
    }

    @Override
    public void updateTime(String time) {
        mStartTime.setText(time);
    }

    @Override
    public void setName(String name) {
        mName.setText(name);
    }

    @Override
    public void setType(int type) {
        mTypeSpinner.setSelection(type);
    }

    @Override
    public void setDuration(int duration) {
        mDuration.setText(String.valueOf(duration));
    }

    @Override
    public void showToast(int stringRes) {
        Toast.makeText(this, getString(stringRes), Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getName() {
        return mName.getText().toString();
    }

    @Override
    public String getDuration() {
        return mDuration.getText().toString();
    }

    @Override
    public void close() {
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_class);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        Intent intent = getIntent();
        int day = intent.getIntExtra("day", -1);
        int index = intent.getIntExtra("index", -1);
        mPresenter = new EditClassInfoPresenter(this);
        mPresenter.init(day, index);
    }
}
