package com.zeeq.movies.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import com.zeeq.movies.MoviesApp;
import com.zeeq.movies.di.component.ActivityComponent;
import com.zeeq.movies.di.component.DaggerActivityComponent;
import com.zeeq.movies.di.module.ActivityModule;

/**
 * Created by Zeeshan on 2/19/2018.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView{
    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(MoviesApp.get(this).getComponent())
                    .build();

    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    @Override
    public void showErrorMsg(@StringRes int error_msg) {
        Snackbar.make(findViewById(android.R.id.content), getString(error_msg), Snackbar.LENGTH_SHORT).show();

    }
}
