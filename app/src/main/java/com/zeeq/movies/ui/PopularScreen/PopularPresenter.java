package com.zeeq.movies.ui.PopularScreen;

import com.zeeq.movies.R;
import com.zeeq.movies.data.DataManager;
import com.zeeq.movies.data.network.response.MoviesResponse;
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

public class PopularPresenter<V extends PopularViewImpl> extends BasePresenter<V> implements PopularPresenterImpl<V> {

   @Inject
    public PopularPresenter(DataManager dataManager, CompositeDisposable compsiteDisposable){
        super(dataManager,compsiteDisposable);
    }


    @Override
    public void fetchPopularMoviesFromApi(int pNo) {
        Disposable disposable = getDataManager().getPopularMovieList(pNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<MoviesResponse>>() {
                    @Override
                    public void onNext(@NonNull Response<MoviesResponse> tvModelResultResponse) {

                            switch (tvModelResultResponse.code()){

                            case 200:
                                getMvpView().updateList(tvModelResultResponse.body());
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
