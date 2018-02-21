package com.zeeq.movies.di.module;

import android.app.Activity;
import android.content.Context;

import com.zeeq.movies.di.qualifiers.ActivityContext;
import com.zeeq.movies.di.scopes.ActivityScope;
import com.zeeq.movies.ui.PopularScreen.PopularPresenter;
import com.zeeq.movies.ui.PopularScreen.PopularPresenterImpl;
import com.zeeq.movies.ui.PopularScreen.PopularViewImpl;
import com.zeeq.movies.ui.SplashScreen.SplashPresenter;
import com.zeeq.movies.ui.SplashScreen.SplashPresenterImpl;
import com.zeeq.movies.ui.SplashScreen.SplashView;
import com.zeeq.movies.ui.TopScreen.TopRatedPresenter;
import com.zeeq.movies.ui.TopScreen.TopRatedPresenterImpl;
import com.zeeq.movies.ui.TopScreen.TopRatedViewImpl;
import com.zeeq.movies.ui.common.MovieListAdapter;
import com.zeeq.movies.ui.main.MainPresenter;
import com.zeeq.movies.ui.main.MainPresenterImpl;
import com.zeeq.movies.ui.main.MainView;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Zeeshan on 2/19/2018.
 */
@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @ActivityScope
    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }
    @ActivityScope
    @Provides
    Activity provideActivity() {
        return mActivity;
    }
    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    MovieListAdapter provideMovieAdapter(){
        return new MovieListAdapter();
    };
@Provides
PopularPresenterImpl<PopularViewImpl> providePopularFragment(PopularPresenter<PopularViewImpl> popularPresenter){
        return popularPresenter;
    }

    @Provides
    TopRatedPresenterImpl<TopRatedViewImpl> provideTopRatedFragment(TopRatedPresenter<TopRatedViewImpl> topRatedPresenter){
        return topRatedPresenter;
    }
    @ActivityScope
    @Provides
    MainPresenterImpl<MainView> provideMainPresenter(MainPresenter<MainView> mainPresenter) {
        return mainPresenter;
    }
    @ActivityScope
    @Provides
    SplashPresenterImpl<SplashView> provideSplashPresenter(SplashPresenter<SplashView> splashPresenter) {
        return splashPresenter;
    }

}
