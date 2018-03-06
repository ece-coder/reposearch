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
public abstract class Issues {

    @SerializedName("items")
    public abstract List<IssueDetails> issues();

    @SerializedName("total_count")
    public abstract String totalCount();

    public static TypeAdapter<Issues> typeAdapter(Gson gson) {
        return new AutoValue_Issues.GsonTypeAdapter(gson);
    }
}

