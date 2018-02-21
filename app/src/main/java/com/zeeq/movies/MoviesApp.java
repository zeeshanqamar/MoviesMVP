package com.zeeq.movies;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.zeeq.movies.data.DataManager;
import com.zeeq.movies.di.component.ApplicationComponent;
import com.zeeq.movies.di.component.DaggerApplicationComponent;
import com.zeeq.movies.di.module.ApplicationModule;
import com.zeeq.movies.di.module.NetworkModule;
import com.zeeq.movies.di.module.RoomModule;
import com.zeeq.movies.di.qualifiers.ApplicationContext;
import com.zeeq.movies.util.AppLogger;

import javax.inject.Inject;

/**
 * Created by Zeeshan on 2/19/2018.
 */

public class MoviesApp extends Application {

    protected ApplicationComponent applicationComponent;

    @Inject
    DataManager dataManager;

    @Inject
    @ApplicationContext
    Context applicationContext;

    public static MoviesApp get(Context context){
        return (MoviesApp) context.getApplicationContext();
    }

    @Override
    public void onCreate(){
        super.onCreate();
MultiDex.install(this);


        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule())
               //.roomModule(new RoomModule(this))
                .build();
        applicationComponent.inject(this);

        Fresco.initialize(applicationContext);
     //   Fabric.with(this, new Crashlytics());
        AppLogger.init();

    }
    public ApplicationComponent getComponent(){
        return applicationComponent;
    }

    public Context getApplicationContext() {
        return applicationContext;
    }

}
