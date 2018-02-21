package com.zeeq.movies.data.network;



import com.zeeq.movies.data.model.Movie;

import com.zeeq.movies.data.network.response.MoviesResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by zeeshan.qamar on 2/19/18.
 */

public interface MovieService {

    @GET("/discover/movie")
    Observable<Response<MoviesResponse>> getMovieByTitle(@QueryMap Map<String, String> queries);

    @GET("movie/popular")
    Observable<Response<MoviesResponse>> getpopular(@QueryMap Map<String, String> queries);

   @GET("movie/top_rated")
    Observable<Response<MoviesResponse>> getTopRated(@QueryMap Map<String, String> queries);

    @GET("movie/{movie_id}")
    Observable<Response<Movie>> getDetail(@Path("movie_id") int movie_id);

}
