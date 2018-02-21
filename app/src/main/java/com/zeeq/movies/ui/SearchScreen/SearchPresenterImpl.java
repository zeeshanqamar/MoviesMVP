package com.zeeq.movies.ui.SearchScreen;

import com.zeeq.movies.ui.base.BaseIPresenter;

/**
 * Created by zeeshan.qamar on 2/20/18.
 */

public interface SearchPresenterImpl<V extends SearchView> extends BaseIPresenter<V> {


    void searchMovie(String keyword,int pNo);

}
