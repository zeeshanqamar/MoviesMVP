package com.zeeq.movies.ui.DetailScreen;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.zeeq.movies.R;

import com.zeeq.movies.data.model.Genres;
import com.zeeq.movies.data.model.Movie;
import com.zeeq.movies.data.model.ProdCompanies;
import com.zeeq.movies.ui.base.BaseActivity;
import com.zeeq.movies.util.Constants;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Zeeshan on 2/19/2018.
 */

public class DetailActivity extends BaseActivity implements DetailView {

    @BindView(R.id.img_movie)
    SimpleDraweeView imgMovie;

    @BindView(R.id.status)
    TextView status;
    @BindView(R.id.release_date)
    TextView release_date;
    @BindView(R.id.tagline)
    TextView tagline;
    @BindView(R.id.genres)
    TextView genres;
    @BindView(R.id.prod_companies)
    TextView prod_companies;
    @BindView(R.id.overview)
    TextView overview;
    @BindView(R.id.homepage)
    TextView homepage;
    @BindView(R.id.prod_companies_lbl)
    TextView prod_companies_lbl;
    @BindView(R.id.genres_lbl)
    TextView genres_lbl;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    DetailPresenter<DetailView> detailPresenter;

    int mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        getActivityComponent().injectDetailActivity(this);
        ButterKnife.bind(this);
        detailPresenter.onAttach(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mId = getIntent().getIntExtra(Constants.SHOW_ID, -1);
        detailPresenter.fetchMovieDetails(mId);


    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
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
    protected void onDestroy() {
        super.onDestroy();
        detailPresenter.onDetach();
    }


    @Override
    public void showMovieDetails(Movie mItem) {
        collapsingToolbarLayout.setTitle(mItem.getTitle());
        status.setText( mItem.getStatus());
        release_date.setText( mItem.getReleaseDate());
        tagline.setText(mItem.getTagline());
    if (mItem.getGenres() != null && mItem.getGenres().size() > 0) {
            genres_lbl.setVisibility(View.VISIBLE);
            genres.setVisibility(View.VISIBLE);
            int size = mItem.getGenres().size();
            int count = 0;
            StringBuffer sb = new StringBuffer();
            for (Genres genre : mItem.getGenres()) {
                sb.append(genre.getName());
                if (count < (size - 1)) {
                    sb.append(", ");
                }
                count++;
            }
            genres.setText(sb.toString());
        }else{
            genres_lbl.setVisibility(View.GONE);
            genres.setVisibility(View.GONE);
        }
        if (mItem.getProduction_companies() != null && mItem.getProduction_companies().size() > 0) {
            prod_companies_lbl.setVisibility(View.VISIBLE);
            prod_companies.setVisibility(View.VISIBLE);
            int size = mItem.getProduction_companies().size();
            int count = 0;
            StringBuffer sb = new StringBuffer();
            for (ProdCompanies pCompany : mItem.getProduction_companies()) {
                sb.append(pCompany.getName());
                if (count < (size - 1)) {
                    sb.append(", ");
                }
                count++;
            }
            prod_companies.setText(sb.toString());
        }else{
            prod_companies_lbl.setVisibility(View.GONE);
            prod_companies.setVisibility(View.GONE);
        }
        overview.setText(mItem.getOverView());
        homepage.setText(mItem.getHomepage());
        if (mItem.getImageUri() != null && !mItem.getImageUri().equals("")) {
            Uri uri4 = Uri.parse(Constants.ImgBaseUrl+mItem.getImageUri());
            ImageRequest imageRequest =
                    ImageRequestBuilder.newBuilderWithSource(uri4)
                            .build();
            DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(imageRequest)
                    .setAutoPlayAnimations(true)
                    .build();
            imgMovie.setController(draweeController);
        }
    }


}