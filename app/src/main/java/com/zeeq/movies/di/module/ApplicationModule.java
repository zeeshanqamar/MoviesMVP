package com.zeeq.movies.di.module;

import android.app.Application;
import android.content.Context;

import com.zeeq.movies.di.qualifiers.ApplicationContext;
import com.zeeq.movies.di.qualifiers.DatabaseInfo;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Zeeshan on 2/19/2018.
 */
@Module
public class ApplicationModule {
private final Application mApplication;

public ApplicationModule(Application app){
    mApplication = app;
}

@Provides
@ApplicationContext
Context provideContext(){
    return mApplication;
}
    @Provides
    Application provideApplication() {
        return mApplication;
    }


   /* @Provides
    SharedPreferences provideSharedPrefs() {
        return mApplication.getSharedPreferences("demo-prefs", Context.MODE_PRIVATE);
    }*/

 /* @Provides
   @ApplicationScope
   NetworkHelper provideNetworkHelper(NetworkHelper networkHelper){
       return networkHelper;
   }*/

}

