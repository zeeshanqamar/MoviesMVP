package com.zeeq.movies.ui.main;

import com.zeeq.movies.data.DataManager;
import com.zeeq.movies.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by zeeshan.qamar on 2/20/18.
 */

public class MainPresenter<V extends  MainView> extends BasePresenter<V> implements MainPresenterImpl<V> {

    @Inject
    public MainPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
      super(dataManager,compositeDisposable);
    }

    @Override
    public void showPopularMovies() {
        getMvpView().closeDrawer();
        getMvpView().showPopularMovies();
    }

    @Override
    public void showTopRatedMovies() {
        getMvpView().closeDrawer();
        getMvpView().showTopRatedMovies();
    }

    @Override
    public void openSearchScreen() {
        getMvpView().closeDrawer();
        getMvpView().openSearchScreen();
    }
}
