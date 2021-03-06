package com.wess58.myrestaurant.services;

import com.wess58.myrestaurant.Constants;
import com.wess58.myrestaurant.models.Restaurant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class YelpService {

    //findRestaurants method START

    public static void findRestaurants(String location, Callback callback){

        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.YELP_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter("location", location);
        String url = urlBuilder.build().toString();


        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer "+Constants.YELP_API_KEY)
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    //findRestaurants method END

    public ArrayList<Restaurant> processResults(Response response){
        ArrayList<Restaurant> restaurants = new ArrayList<>();


        try{
            String jsonData = response.body().string();
            if (response.isSuccessful()){
                JSONObject yelpJSON = new JSONObject(jsonData);
                JSONArray businessesJSON = yelpJSON.getJSONArray("businesses");

                for(int i=0; i<businessesJSON.length(); i++){

                    JSONObject restaurantJSON = businessesJSON.getJSONObject(i);
                    String name = restaurantJSON.getString("name");
                    String phone = restaurantJSON.optString("display_name", "Phone not available");
                    String website = restaurantJSON.getString("url");
                    String imageUrl = restaurantJSON.getString("image_url");
                    double rating = restaurantJSON.getDouble("rating");
                    double latitude = restaurantJSON.getJSONObject("coordinates").getDouble("latitude");
                    double longitude = restaurantJSON.getJSONObject("coordinates").getDouble("longitude");
                    ArrayList<String> categories = new ArrayList<>();
                    JSONArray categoriesJSON = restaurantJSON.getJSONArray("categories");
                    for (int y = 0;y< categoriesJSON.length();y++){
                        categories.add(categoriesJSON.getJSONObject(0).toString());
                    }
                    ArrayList<String> address = new ArrayList<>();
                    JSONArray addressJSON = restaurantJSON.getJSONObject("location")
                            .getJSONArray("display_address");
                    for (int y = 0; y < addressJSON.length(); y++) {
                        address.add(addressJSON.get(y).toString());
                    }
                    Restaurant restaurant = new Restaurant(name, phone, website, imageUrl, rating, latitude, longitude, categories, address);
                    restaurants.add(restaurant);

                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (JSONException e ){
            e.printStackTrace();
        }
        return restaurants;
    }
}
