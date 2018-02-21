package com.zeeq.movies.data;

//import android.arch.lifecycle.LiveData;
import android.content.Context;


import com.zeeq.movies.data.model.Movie;
import com.zeeq.movies.data.network.NetworkHelper;
import com.zeeq.movies.data.network.response.MoviesResponse;
import com.zeeq.movies.data.preferences.SharedPrefsHelper;
import com.zeeq.movies.data.repository.MovieDataSource;
import com.zeeq.movies.di.qualifiers.ApplicationContext;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import retrofit2.Response;

/**
 * Created by Zeeshan on 2/19/2018.
 */
@Singleton
public class DataManager {
    private Context mContext;

    private MovieDataSource mMovieDB;
    private SharedPrefsHelper mSharedPrefsHelper;
    private NetworkHelper mNetworkHelper;

    @Inject
    public DataManager(@ApplicationContext Context context,

                       NetworkHelper networkHelper){
        mContext = context;

        mNetworkHelper = networkHelper;
      //  mMovieDB = movieDB;
    }

    public Observable<Response<MoviesResponse>> getMoviesByKeyword(String keyword) {
        return mNetworkHelper.getMoviesByKeyword(keyword);
    }
    public Observable<Response<MoviesResponse>> getPopularMovieList(int pageNo) {
        return mNetworkHelper.getPopularMovieList(pageNo);
    }

    public Observable<Response<MoviesResponse>> getTopRatedMovieList(int pageNo) {
        return mNetworkHelper.getTopRatedMovieList(pageNo);
    }
    public Observable<Response<Movie>> getMovieDetail(int id) {
        return mNetworkHelper.getMovieDetail(id);
    }

  /*  public LiveData<List<Movie>> printMovieCount() throws Exception {
        return mMovieDB.findAll();
    }*/

}
