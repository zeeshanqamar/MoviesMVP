package com.zeeq.movies.ui.base;

import com.zeeq.movies.di.component.ActivityComponent;

/**
 * Created by Zeeshan on 2/20/2018.
 */

public interface FragmentInteractionListener {

    void showErrorCallback(int error_msg);
    ActivityComponent getActivityComponentCallback();
}
