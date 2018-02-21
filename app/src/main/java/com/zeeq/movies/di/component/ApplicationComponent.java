package com.zeeq.movies.di.component;

import android.app.Application;
import android.content.Context;

import com.zeeq.movies.MoviesApp;
import com.zeeq.movies.data.DataManager;
import com.zeeq.movies.data.database.MovieDao;
import com.zeeq.movies.data.repository.MovieDatabase;
import com.zeeq.movies.data.repository.MovieRepository;
import com.zeeq.movies.di.module.ApplicationModule;
import com.zeeq.movies.di.module.NetworkModule;
import com.zeeq.movies.di.module.RoomModule;
import com.zeeq.movies.di.qualifiers.ApplicationContext;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Zeeshan on 2/19/2018.
 */

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
//@Component(modules = {ApplicationModule.class, NetworkModule.class, RoomModule.class})
public interface ApplicationComponent {
    void inject(MoviesApp moviesApp);

    @ApplicationContext
    Context getContext();

    Application getApplication();

    DataManager getDataManager();

 /* MovieDao movieDao();

    MovieDatabase movieDatabase();

    MovieRepository movieRepository();*/


}
