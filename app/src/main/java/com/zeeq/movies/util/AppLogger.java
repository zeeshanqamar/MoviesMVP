package com.zeeq.movies.util;

import android.util.Log;

import timber.log.Timber;

/**
 * Created by Zeeshan on 2/21/2018.
 */

public class AppLogger {

    public static void init() {
      //  if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
       // }
    }

    public static void d(String s, Object... objects) {
        Timber.d(s, objects);
        Log.d("HttpLog",s);
    }

    public static void d(Throwable throwable, String s, Object... objects) {
        Timber.d(throwable, s, objects);
    }

    public static void i(String s, Object... objects) {
        Timber.i(s, objects);
    }

    public static void i(Throwable throwable, String s, Object... objects) {
        Timber.i(throwable, s, objects);
    }

    public static void w(String s, Object... objects) {
        Timber.w(s, objects);
    }

    public static void w(Throwable throwable, String s, Object... objects) {
        Timber.w(throwable, s, objects);
    }

    public static void e(String s, Object... objects) {
        Timber.e(s, objects);
    }

    public static void e(Throwable throwable, String s, Object... objects) {
        Timber.e(throwable, s, objects);
    }

}