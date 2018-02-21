package com.zeeq.movies.ui.SplashScreen;

import android.content.Intent;
import android.os.Bundle;

import com.zeeq.movies.R;
import com.zeeq.movies.ui.base.BaseActivity;
import com.zeeq.movies.ui.main.MainActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by Zeeshan on 2/19/2018.
 */

public class SplashActivity extends BaseActivity implements SplashView{


@Inject
SplashPresenter<SplashView> mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getActivityComponent().injectSplashActivity(this);
        ButterKnife.bind(this);

        mainPresenter.onAttach(this);


        mainPresenter.initScreen();



    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.onDetach();
    }


    @Override
    public void openMainScreen() {
        startActivity(new Intent(this, MainActivity.class));
finish();
    }
}