package com.zeeq.movies.data.model;

/*import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;*/

import com.google.gson.annotations.SerializedName;

import java.util.List;

//@Entity
public class Movie {

  //  @PrimaryKey
    @SerializedName("id")
    int id;

    @SerializedName("title")
    String title;
    @SerializedName("tagline")
    String tagline;
    @SerializedName("status")
    String status;
    @SerializedName("overview")
    String overView;
    @SerializedName("poster_path")
    String imageUri;
    @SerializedName("homepage")
    String homepage;
    @SerializedName("original_language")
    String language;

    @SerializedName("vote_average")
    double voteAverage;
    @SerializedName("release_date")
    String releaseDate;
    @SerializedName("runtime")
    int runtime;
    @SerializedName("backdrop_path")
    private String backdropPath;
   private List<Genres> genres;
    private List<ProdCompanies> production_companies;

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

 public List<ProdCompanies> getProduction_companies() {
        return production_companies;
    }

    public void setProduction_companies(List<ProdCompanies> production_companies) {
        this.production_companies = production_companies;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

   public List<Genres> getGenres() {
        return genres;
    }

    public void setGenres(List<Genres> genres) {
        this.genres = genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverView() {
        return overView;
    }

    public void setOverView(String overView) {
        this.overView = overView;
    }

    public String getImageUri() {
        if (imageUri != null)
            return imageUri;
        else
            return "";
    }

    public void setImageUri(String imageUri) {
        if (!imageUri.equals("")) {
            this.imageUri = imageUri;
        } else {
            this.imageUri = "";
        }

    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



}
