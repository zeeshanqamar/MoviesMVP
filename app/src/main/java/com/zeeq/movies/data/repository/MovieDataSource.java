package com.zeeq.movies.data.repository;

//import android.arch.lifecycle.LiveData;

import com.zeeq.movies.data.database.MovieDao;
import com.zeeq.movies.data.model.Movie;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MovieDataSource implements MovieRepository {

    private MovieDao movieDao;

    @Inject
    public MovieDataSource(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

  /*  @Override
    public LiveData<Movie> findById(int id) {
        return movieDao.findById(id);
    }

    @Override
    public LiveData<List<Movie>> findAll() {
        return movieDao.findAll();
    }

    @Override
    public long insert(Movie product) {
        return movieDao.insert(product);
    }

    @Override
    public int delete(Movie product) {
        return movieDao.delete(product);
    }*/
}
