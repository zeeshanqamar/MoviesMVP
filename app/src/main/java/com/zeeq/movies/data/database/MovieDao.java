package com.zeeq.movies.data.database;

/*
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
*/

import com.zeeq.movies.data.model.Movie;

import java.util.List;

import retrofit2.http.Query;

//@Dao
public interface MovieDao {

  /*  @Query("SELECT * FROM Movie WHERE id=:id")
    LiveData<Movie> findById(int id);

    @Query("SELECT * FROM Movie")
    LiveData<List<Movie>> findAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Movie product);

    @Delete
    int delete(Movie product);*/

}
