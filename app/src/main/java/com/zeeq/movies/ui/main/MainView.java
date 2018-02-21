package com.zeeq.movies.ui.main;

import com.zeeq.movies.ui.base.BaseView;

/**
 * Created by zeeshan.qamar on 2/20/18.
 */

public interface MainView extends BaseView{

    void closeDrawer();
    void showPopularMovies();
    void showTopRatedMovies();
    void openSearchScreen();
}
