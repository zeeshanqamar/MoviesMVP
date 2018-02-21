package com.zeeq.movies.di.module;

import android.app.Application;
//import android.arch.persistence.room.Room;

import com.zeeq.movies.data.database.MovieDao;
import com.zeeq.movies.data.repository.MovieDataSource;
import com.zeeq.movies.data.repository.MovieDatabase;
import com.zeeq.movies.data.repository.MovieRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class RoomModule {

    private MovieDatabase movieDatabase;


    public RoomModule(Application mApplication) {
       // movieDatabase = Room.databaseBuilder(mApplication, MovieDatabase.class, "movie-db").build();
    }

    @Singleton
    @Provides
    MovieDatabase providesRoomDatabase() {
        return movieDatabase;
    }

    @Singleton
    @Provides
    MovieDao providesMovieDao(MovieDatabase movieDatabase) {
        return movieDatabase.getMovieDao();
    }

    @Singleton
    @Provides
    MovieRepository movieRepository(MovieDao movieDao) {
        return new MovieDataSource(movieDao);
    }

}
