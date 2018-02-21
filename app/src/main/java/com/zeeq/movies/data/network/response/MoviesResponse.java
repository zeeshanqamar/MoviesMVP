package com.zeeq.movies.data.network.response;

import com.google.gson.annotations.SerializedName;
import com.zeeq.movies.data.model.Movie;


import java.util.List;

/**
 * Created by ivan on 8/20/2017.
 */

public class MoviesResponse {
    @SerializedName("page")
    int pageNum;
    @SerializedName("total_pages")
    int total_pages;
    @SerializedName("results")
    private List<Movie> movies;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<Movie> getMovieList() {
        return movies;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movies = movieList;
    }
}
