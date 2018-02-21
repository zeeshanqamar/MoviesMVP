package com.zeeq.movies.ui.TopScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zeeq.movies.data.model.Movie;
import com.zeeq.movies.data.network.response.MoviesResponse;
import com.zeeq.movies.listeners.RecyclerItemClickListener;

import com.zeeq.movies.ui.DetailScreen.DetailActivity;
import com.zeeq.movies.ui.base.BaseFragment;
import com.zeeq.movies.ui.common.MovieListAdapter;
import com.zeeq.movies.util.Constants;

import javax.inject.Inject;

/**
 * Created by zeeshan.qamar on 2/20/18.
 */

public class TopRatedFragment extends BaseFragment implements TopRatedViewImpl {

    public TopRatedFragment() {
        // Required empty public constructor
    }

    public static TopRatedFragment newInstance() {
        TopRatedFragment fragment = new TopRatedFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Inject
    MovieListAdapter adapter;

    @Inject
    TopRatedPresenterImpl<TopRatedViewImpl> topRatedPresenter;
    private int pastVisiblesItems, visibleItemCount;
    private int totalPages = 0;
    private int threshold = 15;
    private boolean loading = false;
    private int page = 1;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater,container,savedInstanceState);

        getActivityComponent().injectTopRatedShows(this);
        topRatedPresenter.onAttach(this);
        topRatedPresenter.fetchTopRatedMoviesFromApi(1);

        mRecyclerView.setAdapter(adapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                Movie mItem = adapter.getmItems().get(position);
                Intent movieDetail = new Intent(getActivity(), DetailActivity.class);
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
                            topRatedPresenter.fetchTopRatedMoviesFromApi(page);;
                        }
                    }
                    // }
                }
            }
        });

        return view;
    }

    @Override
    public void fetchList(MoviesResponse result) {
        setTotalPages(result.getTotal_pages());
        adapter.addAddItems(result.getMovieList());
    }
    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
   /* @Inject
    PopularShowsPresenter<PopularShowsMvpView> popularTvShowsMvpPresenter;
*/

}
