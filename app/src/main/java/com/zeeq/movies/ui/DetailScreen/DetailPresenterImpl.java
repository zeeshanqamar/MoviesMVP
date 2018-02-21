package com.zeeq.movies.ui.DetailScreen;

import com.zeeq.movies.ui.base.BaseIPresenter;

/**
 * Created by zeeshan.qamar on 2/20/18.
 */

public interface DetailPresenterImpl<V extends DetailView> extends BaseIPresenter<V> {


    void fetchMovieDetails(int movie_id);

}
