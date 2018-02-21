package com.zeeq.movies.ui.SplashScreen;

import com.zeeq.movies.ui.base.BasePresenter;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zeeshan.qamar on 2/20/18.
 */

public class SplashPresenter<V extends SplashView> extends BasePresenter<V> implements SplashPresenterImpl<V> {
    public static final int SPLASH_SCREEN_WAIT_TIME_MILLIS = 2500;

    private Disposable waitTask;

    @Inject
    public SplashPresenter() {
      super();

    }


    @Override
    public void initScreen() {
        if (waitTask == null) {
            waitTask = Observable.interval(SPLASH_SCREEN_WAIT_TIME_MILLIS, TimeUnit.MILLISECONDS).take(1)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Exception {
                            waitTask.dispose();
                            getMvpView().openMainScreen();
                        }

                      });
        }
    }
}
