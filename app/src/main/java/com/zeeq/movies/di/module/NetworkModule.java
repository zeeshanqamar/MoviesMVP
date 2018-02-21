package com.zeeq.movies.di.module;

import com.zeeq.movies.data.network.HttpLoggingInterceptor;
import com.zeeq.movies.data.network.MovieService;
import com.zeeq.movies.data.network.RequestInterceptor;
import com.zeeq.movies.util.Constants;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zeeshan.qamar on 2/19/18.
 */
@Module
public class NetworkModule {


    public static final int CONNECT_TIMEOUT_IN_MS = 30000;

    @Provides
    @Singleton
    Interceptor requestInterceptor(RequestInterceptor interceptor) {
        return interceptor;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(RequestInterceptor requestInterceptor) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new okhttp3.OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT_IN_MS, TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(requestInterceptor)
                .build();
    }

    @Provides
Retrofit provideRetrofit(String baseUrl,OkHttpClient okHttpClient){


        return new Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
}

@Provides
String retrofitBaseUrl(){ return Constants.BASE_URL;}


@Provides
MovieService getMovieService(Retrofit retrofit){
    return retrofit.create(MovieService.class);
}

}
