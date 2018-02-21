package com.zeeq.movies.ui.main;

import com.zeeq.movies.ui.base.BaseIPresenter;
import com.zeeq.movies.ui.base.BasePresenter;

/**
 * Created by zeeshan.qamar on 2/20/18.
 */

public interface MainPresenterImpl<V extends MainView> extends BaseIPresenter<V> {

    void showPopularMovies();
    void showTopRatedMovies();
    void openSearchScreen();

}
