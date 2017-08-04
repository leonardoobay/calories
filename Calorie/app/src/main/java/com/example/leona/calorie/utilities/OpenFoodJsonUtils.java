package com.example.leona.calorie.utilities;

import com.example.leona.calorie.model.FoodItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by leona on 07/29/2017.
 */

public class OpenFoodJsonUtils {

    //******************************************


//     public static ArrayList<FoodItem> getSimpleFoodStringsFromJson(String newsJsonStr) throws JSONException {
//        JSONObject newsJson = new JSONObject(newsJsonStr);
//
//        JSONArray foodArray = newsJson.getJSONArray("hits");
//        ArrayList<FoodItem> newsArrayList = new ArrayList<>();
//
//
//
//        for(int i = 0; i<foodArray.length(); i++){
//
//            JSONObject hits = foodArray.getJSONObject(i);
//            String item_id = hits.getString("item_id");
//         String item_name = hits.getString("item_name");
//            String brand_name =  hits.getString("brand_name");
//          String nf_calories = hits.getString("nf_calories");
//
//            FoodItem item = new FoodItem(item_id,item_name,brand_name,nf_calories);
//
//            newsArrayList.add(item);
//        }
//
//        return newsArrayList;
//    }




    public static ArrayList<FoodItem> getSimpleFoodStringsFromJson(String JsonStr) throws JSONException {
        ArrayList<FoodItem> result = new ArrayList<>();
        JSONObject main = new JSONObject(JsonStr);
        JSONArray hits = main.getJSONArray("hits");


        for (int i = 0; i < hits.length(); i++) {
            JSONObject hit = hits.getJSONObject(i);
            JSONObject hitz = hit.getJSONObject("fields");

            String item_id = hitz.getString("item_id");
         String item_name = hitz.getString("item_name");
            String brand_name =  hitz.getString("brand_name");
          String nf_calories = hitz.getString("nf_calories");

            FoodItem item = new FoodItem(item_id,item_name,brand_name,nf_calories);

            result.add(item);
        }
        return result;

    }

}
