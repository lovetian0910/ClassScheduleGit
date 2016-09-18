package com.kjw.classschedule.edit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.kjw.classschedule.R;

/**
 * Created by jwkuang on 2016/9/18.
 */
public class EditClassInfoActivity extends AppCompatActivity implements EditClassInfoView{
    private EditClassInfoPresenter mPresenter;
    @Override
    public void initViews() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_class);
        mPresenter = new EditClassInfoPresenter(this);
        mPresenter.init();
    }
}
