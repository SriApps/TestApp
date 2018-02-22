package com.test.sri.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class MainActivity extends AppCompatActivity {

    private ListView listView;
    List <NewsFeedListModel> sampleList;
    private String title = null;
    NewsFeedAdapter newsFeedAdapter;

    private ArrayAdapter<NewsFeedListModel> adapter;
    private static final String TAG = "NewsFeedActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData();


    }

        private void getData()
        {
            sampleList=new ArrayList<>()  ;

            RetrofitApiService service = NewsFeedRetrofitClient.getInstance().getService();
            if (service != null) {
                Call<NewsFeedModel> call = service.loadFeeds();
                if (call != null) {
                    //Async call of retrofit library which will download and parse the JSON
                    call.enqueue(new Callback<NewsFeedModel>() {
                        @Override
                        public void onResponse(Response<NewsFeedModel> response, Retrofit retrofit) {
                            //Check whether response recieved is valid
                            if (response != null && response.body() != null) {
                                try {
                                    title = response.body().title;
                                    setTitle(title);

                                    if (response.body().rows != null && response.body().rows.size() > 0) {

                                        sampleList=response.body().rows;
                                        listView = (ListView) findViewById(R.id.main_list_view);
                                        adapter=new NewsFeedAdapter(MainActivity.this,R.id.main_list_view,sampleList);
                                        listView.setAdapter(adapter);


                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();

                                }
                            } else {

                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            Log.e(TAG, "Callback failure");

                        }
                    });
                } else {

                }
            } else {

            }
        }
    }

