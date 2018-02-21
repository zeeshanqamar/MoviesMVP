package com.zeeq.movies.ui.base;

import com.zeeq.movies.data.DataManager;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Zeeshan on 2/20/2018.
 */

public class BasePresenter<V extends BaseView> implements BaseIPresenter<V> {
    DataManager dataManager;
    CompositeDisposable compositeDisposable;
    private V mView;

    @Inject
    public BasePresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        this.dataManager = dataManager;
        this.compositeDisposable = compositeDisposable;
    }

    public BasePresenter(){

    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    public void setCompositeDisposable(CompositeDisposable compositeDisposable) {
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void onAttach(V view) {
        mView = view;
    }

    @Override
    public void onDetach() {
        if(compositeDisposable!=null)
        compositeDisposable.dispose();
        mView = null;

    }

    public V getMvpView() {
        return mView;
    }
    public boolean isViewAttached(){
        return mView!=null;
    }

}
