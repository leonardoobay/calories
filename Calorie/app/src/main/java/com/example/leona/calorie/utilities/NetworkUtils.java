package com.example.leona.calorie.utilities;

import android.net.Uri;
import android.util.Log;

import com.example.leona.calorie.model.FoodItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import static android.content.ContentValues.TAG;

/**
 * Created by leona on 8/3/2017.
 */

public class NetworkUtils {



    /*

    application ID  f85a3dc7

    api key  ec9e2990978081871f45fc9fe8d97f3b
    https://api.nutritionix.com/v1_1/search/
    cheddar%20cheese?fields=item_name%2Citem_id%2Cbrand_name%2
    Cnf_calories%2Cnf_total_fat&appId=f85a3dc7&appKey=ec9e2990978081871f45fc9fe8d97f3b
     */
    //Define the appropriate base_url and query_parameter constants
    // (make sure they are Java constants) here as static class members.
    //5. 5pts: Create a static method in NetworkUtils that uses Uri.Builder to build the appropriate url,
    // the url you used in (2), to return a completed Java URL.
//    private static final String NEWS_BASE_URL = "https://api.nutritionix.com/v1_1/search/cheddar%20cheese?fields=item_name%2Citem_id%2Cbrand_name%2Cnf_calories%2Cnf_total_fat&appId=f85a3dc7&";
//    private static final String queryApiKey= "apiKey";


//    private static final String FOOD_BASE_URL ="https://api.nutritionix.com/v1_1/search/mcdonalds?results=0:10&fields=item_name,brand_name,item_id,nf_calories&appId=f85a3dc7&";
//    private static final String appKey ="appKey";
//    //appKey=ec9e2990978081871f45fc9fe8d97f3b

    //******************


//    public static URL makeUrl() {
//        //build the url to were well retrive info
//        Uri builtUri = Uri.parse(FOOD_BASE_URL).buildUpon()
//                .appendQueryParameter(appKey,"ec9e2990978081871f45fc9fe8d97f3b")
//                .build();
//
//        URL url = null;
//
//        try {
//            url = new URL(builtUri.toString());
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//
//        Log.v(TAG, "Built URI " + url);
//
//        return url;
//    }

    //6. 2pts: Put this method in your NetworkUtils class:
    public static final String STATIC_NEWS_URL =
            "https://api.nutritionix.com/v1_1/search/";
    public static final String NEWS_BASE_URL = STATIC_NEWS_URL;


    public static final String appKey = "ec9e2990978081871f45fc9fe8d97f3b";
    public static final String appId= "f85a3dc7";
    public static final String results = "0:15";
    public static final String cal_max = "5000";
    public static final String cal_min = "0";
    public static final String fields = "item_name,brand_name,nf_calories";

    public static String PARAM_RESULTS = "results";
    public static String PARAM_CAL_MIN= "cal_min";
    public static String PARAM_CAL_MAX = "cal_max";
    public static String PARAM_FIELDS = "fields";
    public static String PARAM_APPID = "appId";
    public static String PARAM_APIKEY = "appKey";




    public static URL makeUrl(String search) {
        String NEW_URL = NEWS_BASE_URL.concat("phrase=" + search);
        Uri buildUri = Uri.parse(NEW_URL).buildUpon()
                .appendQueryParameter(PARAM_RESULTS, results)
                .appendQueryParameter(PARAM_CAL_MIN, cal_min)
                .appendQueryParameter(PARAM_CAL_MAX, cal_max)
                .appendQueryParameter(PARAM_FIELDS, fields)
                .appendQueryParameter(PARAM_APPID, appId)
                .appendQueryParameter(PARAM_APIKEY, appKey)
                .build();

        URL url = null;
        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.v(TAG, "Built URI " + url);
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException
    {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try{
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();
            if(hasInput)
            {
                return scanner.next();
            }
            else {
                return null;
            }

        }
        finally {
            urlConnection.disconnect();
        }
    }




}
