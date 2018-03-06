package com.example.jibriel.gitquery.models;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jibriel on 06/03/2018.
 */

@AutoValue
public abstract class IssueDetails {

    @SerializedName("title")
    public abstract String title();

    @SerializedName("url")
    public abstract String url();

    @SerializedName("created_at")
    public abstract String created();

    @SerializedName("updated_at")
    public abstract String updated();

    @Nullable
    @SerializedName("body")
    public abstract String body();

    public static TypeAdapter<IssueDetails> typeAdapter(Gson gson) {
        return new AutoValue_IssueDetails.GsonTypeAdapter(gson);
    }
}
