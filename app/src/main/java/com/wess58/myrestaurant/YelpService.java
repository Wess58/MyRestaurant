package com.wess58.myrestaurant;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class YelpService {

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
}
