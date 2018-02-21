package com.zeeq.movies.ui.TopScreen;

import com.zeeq.movies.ui.base.BaseIPresenter;

/**
 * Created by zeeshan.qamar on 2/20/18.
 */

public interface TopRatedPresenterImpl<V extends TopRatedViewImpl> extends BaseIPresenter<V> {
void fetchTopRatedMoviesFromApi(int pNo);
}
