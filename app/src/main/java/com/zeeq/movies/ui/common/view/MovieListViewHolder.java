package com.zeeq.movies.ui.common.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zeeq.movies.R;


import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieListViewHolder extends RecyclerView.ViewHolder {


    public SimpleDraweeView getImgMovie() {
        return imgMovie;
    }

    public void setImgMovie(SimpleDraweeView imgMovie) {
        this.imgMovie = imgMovie;
    }


    @BindView(R.id.img_movie)
     SimpleDraweeView imgMovie;

    public MovieListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);

    }


}
