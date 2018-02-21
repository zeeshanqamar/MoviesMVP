package com.zeeq.movies.ui.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zeeq.movies.R;
import com.zeeq.movies.di.component.ActivityComponent;
import com.zeeq.movies.listeners.RecyclerItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Zeeshan on 2/20/2018.
 */

public class BaseFragment extends Fragment implements BaseView, RecyclerItemClickListener.OnItemClickListener{

    private FragmentInteractionListener mListener;

    public Unbinder getUnbinder() {
        return unbinder;
    }

    public void setUnbinder(Unbinder unbinder) {
        this.unbinder = unbinder;
    }

    private Unbinder unbinder;

    public GridLayoutManager getmLayoutManager() {
        return mLayoutManager;
    }

    private GridLayoutManager mLayoutManager;

    @BindView(R.id.movie_grid)
    public RecyclerView mRecyclerView;

    public BaseFragment(){

    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof FragmentInteractionListener){
            mListener = (FragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener =null;
    }

    @Override
    public void onDestroy() {
        if(unbinder!=null){
            unbinder.unbind();
        }
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_movie_list,container,false);
       setUnbinder(ButterKnife.bind(this,view));
        mLayoutManager = new GridLayoutManager(getActivity(),2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);

        return view;
    }
    public ActivityComponent getActivityComponent() {
        return mListener.getActivityComponentCallback();
    }
    @Override
    public void showErrorMsg(@StringRes int error_msg) {
        mListener.showErrorCallback(error_msg);
    }

    @Override
    public void onItemClick(View view, int position) {
    }

    @Override
    public void onItemLongClick(View view, int position) {
    }
}
