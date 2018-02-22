package com.test.sri.testapp;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by Myworld on 22/02/2018.
 */

public interface RetrofitApiService {
    @GET("facts.json")
    Call<NewsFeedModel> loadFeeds();

}
