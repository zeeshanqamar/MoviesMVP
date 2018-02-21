package com.zeeq.movies.ui.SearchScreen;

import com.zeeq.movies.data.network.response.MoviesResponse;
import com.zeeq.movies.ui.base.BaseView;

/**
 * Created by zeeshan.qamar on 2/20/18.
 */

public interface SearchView extends BaseView{

    void updateSearchList(MoviesResponse result);
    void clearSearch();
}
