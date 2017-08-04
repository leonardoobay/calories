package com.example.leona.calorie;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.leona.calorie.model.FoodItem;
import com.example.leona.calorie.utilities.NetworkUtils;
import com.example.leona.calorie.utilities.OpenFoodJsonUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rView;
    private ProgressBar progress;
    private  FoodsAdapter mAdapter;

    //oncreateoptons and menu inflater allow the searchicon xml to become an object and be displayed
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchicon, menu);
        return true;

    }


    //*****************************************8

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return true;
    }

    //private  FoodsAdapter mAdapter;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress = (ProgressBar) findViewById(R.id.progress);
        rView = (RecyclerView) findViewById(R.id.rv_foods);

        rView.setLayoutManager(new LinearLayoutManager(this));
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        rView.setLayoutManager(layoutManager);
        rView.setAdapter(mAdapter);
        rView.setHasFixedSize(true);
        FoodsRequests task = new FoodsRequests();

       task.execute();

        //******************




        //****************

    }
    public class FoodsRequests extends AsyncTask<Object, Object, ArrayList<FoodItem>> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // textView.setText("");
            //        progress.setVisibility(View.VISIBLE);

        }

        @Override
        protected ArrayList<FoodItem> doInBackground(Object... params) {
            ArrayList<FoodItem> output;
            String chicken = "chicken";
            URL foodRequrl = NetworkUtils.makeUrl(chicken);

            try {
                String json = NetworkUtils.getResponseFromHttpUrl(foodRequrl);

                // output = NetworkUtils.parseJSON(json);
                output = OpenFoodJsonUtils.getSimpleFoodStringsFromJson(json);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }

            return output;
        }


        @Override
        protected void onPostExecute(final ArrayList<FoodItem> foodsData) {
            super.onPostExecute(foodsData);
            progress.setVisibility(GONE);
            //  rView.setAdapter(adapter);
            if (foodsData != null) {
                FoodsAdapter adapter = new FoodsAdapter(foodsData, new FoodsAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(int itemIndex) {
//                           Uri uri = Uri.parse(foodsData.get(itemIndex).getUrl());
//                        Intent intent = new Intent(Intent.ACTION_VIEW);
//                        startActivity(intent);
                    }
                });
                rView.setAdapter(adapter);
               // mAdapter.setAdapter(adapter);
//            }

            }
           // rView.setAdapter(mAdapter);
        }


    }




}
