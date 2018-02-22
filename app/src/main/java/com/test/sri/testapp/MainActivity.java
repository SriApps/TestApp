package com.test.sri.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class MainActivity extends AppCompatActivity {

    //initialising variables

    private ListView listView;
    List <NewsFeedListModel> sampleList;
    private String title = null;
    NewsFeedAdapter newsFeedAdapter;
    private ArrayAdapter<NewsFeedListModel> adapter;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Method to retrive Json Data
        getData();
    }

        private void getData()
        {
            sampleList=new ArrayList<>()  ;

            RetrofitApiService service = NewsFeedRetrofitClient.getInstance().getService();

            if (service != null)
            {
                Call<NewsFeedModel> call = service.loadFeeds();

                if (call != null)
                {
                    //parsing the JSON data
                    call.enqueue(new Callback<NewsFeedModel>()
                    {
                        @Override
                        public void onResponse(Response<NewsFeedModel> response, Retrofit retrofit)
                        {
                            //Check if the response is null
                            if (response != null && response.body() != null)
                            {
                                try {
                                    title = response.body().title;
                                    setTitle(title);

                                    if (response.body().rows != null && response.body().rows.size() > 0)
                                    {
                                        sampleList=response.body().rows;
                                        listView =  findViewById(R.id.main_list_view);
                                        adapter=new NewsFeedAdapter(MainActivity.this,R.id.main_list_view,sampleList);
                                        listView.setAdapter(adapter);
                                    }
                                }
                                catch (Exception e)
                                {
                                    e.printStackTrace();

                                }
                            }
                            else {

                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            Log.e(TAG, "Failed");

                        }
                    });
                } else {

                }
            } else {

            }
        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.menu_refresh) {

            Toast toast= Toast.makeText(this,"Please wait loading....",Toast.LENGTH_LONG);
            toast.show();
            getData();

        }

        return true;
        }




    }


