package com.zeeq.movies.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zeeshan on 2/26/2017.
 */

public class Genres {
    @SerializedName("id")
    String id;
    @SerializedName("name")
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
