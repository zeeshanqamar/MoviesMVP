package com.zeeq.movies.ui.SearchScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.zeeq.movies.R;
import com.zeeq.movies.data.model.Movie;
import com.zeeq.movies.data.network.response.MoviesResponse;
import com.zeeq.movies.listeners.RecyclerItemClickListener;

import com.zeeq.movies.ui.DetailScreen.DetailActivity;
import com.zeeq.movies.ui.base.BaseActivity;
import com.zeeq.movies.ui.common.MovieListAdapter;
import com.zeeq.movies.util.Constants;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Zeeshan on 2/19/2018.
 */

public class SearchActivity extends BaseActivity implements SearchView {

    @BindView(R.id.movie_search)
    public RecyclerView mRecyclerView;
    @BindView(R.id.search_view)
    public MaterialSearchView materialSearchView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Inject
    MovieListAdapter adapter;
    @Inject
    SearchPresenter<SearchView> searchPresenter;
    String query;
    private GridLayoutManager mLayoutManager;
    private int pastVisiblesItems, visibleItemCount;
    private int totalPages = 0;
    private int threshold = 15;
    private boolean loading = false;
    private int page = 1;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public GridLayoutManager getmLayoutManager() {
        return mLayoutManager;
    }

    public void setmLayoutManager(GridLayoutManager mLayoutManager) {
        this.mLayoutManager = mLayoutManager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);
        getActivityComponent().injectSearchActivity(this);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        searchPresenter.onAttach(this);
        materialSearchView.setVoiceSearch(false);

        materialSearchView.setEllipsize(true);
   ;
        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                setQuery(query);
                searchPresenter.searchMovie(query, getPage());

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                setQuery(newText);
                searchPresenter.searchMovie(newText, getPage());
                return false;
            }
        });
        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                Movie mItem = adapter.getmItems().get(position);
                Intent movieDetail = new Intent(SearchActivity.this, DetailActivity.class);
                movieDetail.putExtra(Constants.SHOW_ID, mItem.getId());
                startActivity(movieDetail);
            }

            @Override
            public void onItemLongClick(View view, int position) {
            }
        }));
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) //check for scroll down
                {
                    visibleItemCount = getmLayoutManager().getChildCount();
                    totalItemCount = getmLayoutManager().getItemCount();
                    pastVisiblesItems = getmLayoutManager().findFirstVisibleItemPosition();
                    lastVisibleItem = getmLayoutManager().findLastVisibleItemPosition();
                    //if (!loading) {
                    if ((visibleItemCount + pastVisiblesItems + threshold) >= totalItemCount) {
                        if (totalPages > page) {
                            loading = true;
                            page++;
                            searchPresenter.searchMovie(getQuery(), page);
                        }
                    }
                    // }
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        materialSearchView.setMenuItem(item);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        if (materialSearchView.isSearchOpen()) {
            materialSearchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        searchPresenter.onDetach();
    }


    @Override
    public void updateSearchList(MoviesResponse result) {
        setTotalPages(result.getTotal_pages());
        adapter.addAddItems(result.getMovieList());
    }

    @Override
    public void clearSearch() {
        setPage(1);
        setTotalPages(0);
        adapter.clearItems();
    }
}