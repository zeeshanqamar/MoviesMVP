package com.zeeq.movies.ui.SearchScreen;

import android.os.Handler;

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

public class SearchPresenter<V extends SearchView> extends BasePresenter<V> implements SearchPresenterImpl<V> {

     Handler handler = new android.os.Handler();
    Runnable runnable;
    String mQueryString;

    @Inject
    public SearchPresenter(DataManager dataManager, CompositeDisposable compsiteDisposable){
        super(dataManager,compsiteDisposable);
    }



    @Override
    public void searchMovie(String keyword,int page) {
        mQueryString=keyword;
if(mQueryString!=null) {

    handler.removeCallbacks(runnable);
    runnable = new Runnable() {
        @Override
        public void run() {
            Disposable disposable = getDataManager().getMoviesByKeyword(mQueryString)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableObserver<Response<MoviesResponse>>() {
                        @Override
                        public void onNext(@NonNull Response<MoviesResponse> tvModelResultResponse) {

                            switch (tvModelResultResponse.code()) {

                                case 200:
                                    getMvpView().updateSearchList(tvModelResultResponse.body());
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
    };
    handler.postDelayed(runnable, 500);
}
    }
}
