package com.zeeq.movies.ui.base;

/**
 * Created by Zeeshan on 2/20/2018.
 */

public interface BaseIPresenter<V> {
    void onAttach(V view);

    void onDetach();
}
