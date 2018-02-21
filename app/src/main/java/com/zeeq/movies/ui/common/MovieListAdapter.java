package com.zeeq.movies.ui.common;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.zeeq.movies.R;
import com.zeeq.movies.data.model.Movie;

import com.zeeq.movies.ui.common.view.MovieListViewHolder;
import com.zeeq.movies.util.Constants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by zeeshan on 8/15/2016.
 */
public class MovieListAdapter extends RecyclerView.Adapter<MovieListViewHolder> {


    private List<Movie> visibleObjects;

    @Inject
    public MovieListAdapter() {
        visibleObjects = new ArrayList<Movie>();


    }

    public List<Movie> getmItems() {
        return visibleObjects;
    }

    public  void addAddItems(List<Movie> items){
        int count=0;

        for (int i=items.size();i>0;i--){
            visibleObjects.add(items.get(count));
            count++;
        }
notifyDataSetChanged();
    }

    public void addItem(Movie item) {
        this.visibleObjects.add(item);
    }

    public void clearItems() {
        this.visibleObjects.clear();
        notifyDataSetChanged();
    }

    @Override
    public MovieListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieListViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(MovieListViewHolder viewHolder, int position) {
        final Movie item = visibleObjects.get(position);



        if (item.getImageUri() != null && !item.getImageUri().equals("")) {
            Uri uri4 = Uri.parse(Constants.ImgThumbBaseUrl+item.getImageUri());
            ImageRequest imageRequest =
                    ImageRequestBuilder.newBuilderWithSource(uri4)
                            .build();
            DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(imageRequest)
                    .setOldController(viewHolder.getImgMovie().getController())
                    .setAutoPlayAnimations(true)
                    .build();
            viewHolder.getImgMovie().setController(draweeController);
        }



    }


    @Override
    public int getItemCount() {
        return visibleObjects.size();

    }


}
