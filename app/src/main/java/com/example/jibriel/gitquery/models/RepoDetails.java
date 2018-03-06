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
public abstract class RepoDetails {

    @SerializedName("full_name")
    public abstract String fullName();

    @Nullable
    @SerializedName("description")
    public abstract String description();

    @SerializedName("url")
    public abstract String url();

    @SerializedName("stargazers_count")
    public abstract int numStars();

    @SerializedName("forks_count")
    public abstract int numForks();

    @SerializedName("open_issues_count")
    public abstract int numOpenIssues();

    public static TypeAdapter<RepoDetails> typeAdapter(Gson gson) {
        return new AutoValue_RepoDetails.GsonTypeAdapter(gson);
    }
}
