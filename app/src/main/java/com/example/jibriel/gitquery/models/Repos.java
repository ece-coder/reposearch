package com.example.jibriel.gitquery.models;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jibriel on 06/03/2018.
 */

@AutoValue
public abstract class Repos {


    @SerializedName("total_count")
    public abstract String totalCount();

    @SerializedName("items")
    public abstract List<RepoDetails> repos();

    public static TypeAdapter<Repos> typeAdapter(Gson gson) {
        return new AutoValue_Repos.GsonTypeAdapter(gson);
    }

}

