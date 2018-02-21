package com.zeeq.movies.di.component;

import com.zeeq.movies.di.module.ActivityModule;
import com.zeeq.movies.di.scopes.ActivityScope;
import com.zeeq.movies.ui.DetailScreen.DetailActivity;
import com.zeeq.movies.ui.PopularScreen.PopularFragment;
import com.zeeq.movies.ui.SearchScreen.SearchActivity;
import com.zeeq.movies.ui.SplashScreen.SplashActivity;
import com.zeeq.movies.ui.TopScreen.TopRatedFragment;
import com.zeeq.movies.ui.main.MainActivity;

import dagger.Component;

/**
 * Created by Zeeshan on 2/19/2018.
 */


@ActivityScope

@Component(modules = {ActivityModule.class}, dependencies = {ApplicationComponent.class})

public interface ActivityComponent {

    void injectSplashActivity(SplashActivity splashActivity);
    void injectMainActivity(MainActivity mainActivity);
    void injectDetailActivity(DetailActivity detailActivity);
    void injectPopularShows(PopularFragment popularFragment);
    void injectTopRatedShows(TopRatedFragment topRatedFragment);
    void injectSearchActivity(SearchActivity searchActivity);

}