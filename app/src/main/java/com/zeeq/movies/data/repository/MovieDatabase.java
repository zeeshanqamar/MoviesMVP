package com.zeeq.movies.data.repository;

/*import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;*/

import com.zeeq.movies.data.database.MovieDao;
import com.zeeq.movies.data.model.Movie;



//@Database(entities = {Movie.class}, version = MovieDatabase.VERSION)
public abstract class MovieDatabase {//extends RoomDatabase {

    static final int VERSION = 1;

    public abstract MovieDao getMovieDao();

}
