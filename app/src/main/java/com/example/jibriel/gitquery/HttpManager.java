package com.example.jibriel.gitquery;

import com.example.jibriel.gitquery.models.AutoValueGsonFactory;
import com.example.jibriel.gitquery.models.Repos;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by jibriel on 06/03/2018.
 */

public interface HttpManager {

    String BASE_URL = "https://api.github.com/";
    //Sample API:
    //https://api.github.com/search/issues?q=repo:twbs/bootstrap+is:closed

    @GET("/search/issues")
    Observable<Repos> searchIssues(@Query("q") String issues);


    @GET("/search/repositories")
    Observable<Repos> searchRepo(@Query("q") String repoSearch);


    class Factory {

        public static HttpManager create() {
            GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(new GsonBuilder()
                    .registerTypeAdapterFactory(AutoValueGsonFactory.create())
                    .create());

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            Retrofit retrofitClient = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(client)
                    .build();

            return retrofitClient.create(HttpManager.class);
        }


    }
}
