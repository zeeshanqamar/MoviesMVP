package com.zeeq.movies.data.network;


import com.zeeq.movies.data.model.Movie;

import com.zeeq.movies.data.network.response.MoviesResponse;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import retrofit2.Response;

/**
 * Created by zeeshan.qamar on 2/19/18.
 */

@Singleton
public class NetworkHelper {
    MovieService movieService;

    @Inject
    public NetworkHelper(MovieService movieService){
        this.movieService = movieService;

    }
    public Observable<Response<MoviesResponse>> getMoviesByKeyword(String keyword){

        Map<String, String> params = new HashMap<>();


        params.put("with_keywords", keyword);

        return movieService.getpopular(params);
    }

    public Observable<Response<MoviesResponse>> getPopularMovieList(int pageNo){

        Map<String, String> params = new HashMap<>();


        params.put("page", String.valueOf(pageNo));

        return movieService.getpopular(params);
    }

    public Observable<Response<MoviesResponse>> getTopRatedMovieList(int pageNo){

        Map<String, String> params = new HashMap<>();


        params.put("page", String.valueOf(pageNo));

        return movieService.getTopRated(params);
    }

    public Observable<Response<Movie>> getMovieDetail(int movie_id){



        return movieService.getDetail(movie_id);
    }
}
