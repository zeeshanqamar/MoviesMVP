package com.zeeq.movies.ui.DetailScreen;


import com.zeeq.movies.data.model.Movie;
import com.zeeq.movies.ui.base.BaseView;

/**
 * Created by zeeshan.qamar on 2/20/18.
 */

public interface DetailView extends BaseView{

    void showMovieDetails(Movie movie);
}
