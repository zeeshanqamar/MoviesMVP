package com.zeeq.movies.ui.PopularScreen;

import com.zeeq.movies.ui.base.BaseIPresenter;

/**
 * Created by zeeshan.qamar on 2/20/18.
 */

public interface PopularPresenterImpl<V extends PopularViewImpl> extends BaseIPresenter<V> {
void fetchPopularMoviesFromApi(int pNo);
}
