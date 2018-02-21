package com.zeeq.movies.ui.DetailScreen;

import com.zeeq.movies.R;
import com.zeeq.movies.data.DataManager;

import com.zeeq.movies.data.model.Movie;
import com.zeeq.movies.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by zeeshan.qamar on 2/20/18.
 */

public class DetailPresenter<V extends DetailView> extends BasePresenter<V> implements DetailPresenterImpl<V> {



    @Inject
    public DetailPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager,compositeDisposable);
    }


    @Override
    public void fetchMovieDetails(int id) {
        Disposable disposable = getDataManager().getMovieDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<Movie>>() {
                    @Override
                    public void onNext(@NonNull Response<Movie> movieResultResponse) {

                        switch (movieResultResponse.code()){

                            case 200:
                                getMvpView().showMovieDetails(movieResultResponse.body());
                                //getMvpView().hideLoading(bottomProgress);
                                break;

                            case 401:
                                getMvpView().showErrorMsg(R.string.missing_key);
                                // getMvpView().hideLoading(bottomProgress);
                                break;
                        }


                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        getMvpView().showErrorMsg(R.string.something_wrong);
                        //getMvpView().hideLoading(bottomProgress);

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        getCompositeDisposable().add(disposable);
    }
}
