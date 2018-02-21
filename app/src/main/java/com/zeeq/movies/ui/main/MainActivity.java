package com.zeeq.movies.ui.main;

//import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.zeeq.movies.R;
import com.zeeq.movies.data.DataManager;
import com.zeeq.movies.data.model.Movie;
import com.zeeq.movies.di.component.ActivityComponent;
import com.zeeq.movies.ui.PopularScreen.PopularFragment;
import com.zeeq.movies.ui.SearchScreen.SearchActivity;
import com.zeeq.movies.ui.TopScreen.TopRatedFragment;
import com.zeeq.movies.ui.base.BaseActivity;
import com.zeeq.movies.ui.base.FragmentInteractionListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * Created by Zeeshan on 2/19/2018.
 */

public class MainActivity extends BaseActivity implements MainView,FragmentInteractionListener,NavigationView.OnNavigationItemSelectedListener,View.OnClickListener{

    @Inject
    DataManager mDataManager;
    @Inject
    MainPresenter<MainView> mainPresenter;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.toolbar)
    Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().injectMainActivity(this);
        ButterKnife.bind(this);

        mainPresenter.onAttach(this);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

        };

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        mainPresenter.showPopularMovies();


    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.popular) {
            mainPresenter.showPopularMovies();

        } else if (id == R.id.top_rated) {
            mainPresenter.showTopRatedMovies();

        }else if (id == R.id.Search) {
            mainPresenter.openSearchScreen();

        }

return  true;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void showErrorMsg(@StringRes int error_msg) {

    }

    @Override
    public void closeDrawer() {
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void showPopularMovies() {
        PopularFragment popularShows = (PopularFragment) getSupportFragmentManager().findFragmentByTag(PopularFragment.class.getSimpleName());


        if (popularShows == null || !popularShows.isVisible()) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_main, popularShows.newInstance(), PopularFragment.class.getSimpleName()).commit();
        }
    }

    @Override
    public void showTopRatedMovies() {
        TopRatedFragment topRated = (TopRatedFragment) getSupportFragmentManager().findFragmentByTag(TopRatedFragment.class.getSimpleName());


        if (topRated == null || !topRated.isVisible()) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_main, TopRatedFragment.newInstance(), TopRatedFragment.class.getSimpleName()).commit();
        }
    }

    @Override
    public void openSearchScreen() {
        Intent movieSearch = new Intent(MainActivity.this, SearchActivity.class);

        startActivity(movieSearch);
    }

    @Override
    public void showErrorCallback(int error_msg) {

    }

    @Override
    public ActivityComponent getActivityComponentCallback() {
        return getActivityComponent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.onDetach();
    }


}