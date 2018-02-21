package com.zeeq.movies.ui.TopScreen;

import com.zeeq.movies.data.network.response.MoviesResponse;
import com.zeeq.movies.ui.base.BaseView;

/**
 * Created by zeeshan.qamar on 2/20/18.
 */

public interface TopRatedViewImpl extends BaseView{
    void fetchList(MoviesResponse result);
}
