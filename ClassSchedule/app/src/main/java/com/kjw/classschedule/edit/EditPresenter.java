package com.kjw.classschedule.edit;

import android.view.View;
import android.widget.AdapterView;

import com.kjw.classschedule.R;

/**
 * Created by jwkuang on 2016/9/18.
 */
public class EditPresenter implements View.OnClickListener, AdapterView.OnItemSelectedListener{
    private static String TAG = "EditPresenter";

    private EditView mView;
    private int mCurrentDay;
    public EditPresenter(EditView view){
        mView = view;
        mView.initViews();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.edit_prev_day:
                mCurrentDay--;
                if(mCurrentDay < 0){
                    mCurrentDay = 6;
                }
                mView.changeDay(mCurrentDay);
                break;
            case R.id.edit_next_day:
                mCurrentDay++;
                if(mCurrentDay > 6){
                    mCurrentDay = 0;
                }
                mView.changeDay(mCurrentDay);
                break;
            case R.id.edit_add_class:
                break;
            case R.id.edit_save:
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mCurrentDay = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
