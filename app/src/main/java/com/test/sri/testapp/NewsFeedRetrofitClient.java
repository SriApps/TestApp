package com.test.sri.testapp;

import android.provider.SyncStateContract;

import retrofit.Retrofit;
import retrofit.GsonConverterFactory;
/**
 * Created by Myworld on 22/02/2018.
 */

public class NewsFeedRetrofitClient {

    private static NewsFeedRetrofitClient instance;
    private RetrofitApiService retrofitApiService;
    private static final String FEED_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/";

    public static NewsFeedRetrofitClient getInstance() {
        if (instance == null) {
            instance = new NewsFeedRetrofitClient();
        }
        return instance;
    }


    private NewsFeedRetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FEED_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitApiService = retrofit.create(RetrofitApiService.class);
    }

    public RetrofitApiService getService() {
        return retrofitApiService;
    }
}
