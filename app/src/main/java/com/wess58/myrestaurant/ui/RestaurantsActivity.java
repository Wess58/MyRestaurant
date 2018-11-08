package com.wess58.myrestaurant.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.wess58.myrestaurant.R;
import com.wess58.myrestaurant.models.Restaurant;
import com.wess58.myrestaurant.services.YelpService;

import okhttp3.Call;
import okhttp3.Callback;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Response;

public class RestaurantsActivity extends AppCompatActivity {
    public static final String TAG = RestaurantsActivity.class.getSimpleName();

    @BindView(R.id.textView2) TextView mLocationTextView;
    @BindView(R.id.listView) ListView  mListView;

    public ArrayList<Restaurant> mRestaurants = new ArrayList<>();

//    WAS THERE AS RESTAURANTS PLACEHOLDER
//    private String[] restaurants = new String[]{"Java","Subway","KFC","Galito's"
//            ,"Fritaz","Kwa Mary","CJ's","Kilimanjaro", "Chipotle","Manhattan"
//    };
//    private String[] cuisines = new String[] {"Vegan Food", "Breakfast", "Fishs Dishs",
//            "Scandinavian", "Coffee", "English Food", "Burgers", "Fast Food", "Noodle Soups", "Mexican"
//    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        ButterKnife.bind(this);




        // let’s pull the data out of the intent extra in our RestaurantsActivity.
        //the getStringExtra() method to pull out the location value based using the string key we provided.
        // we call the setText() method to update its text to contain the sentence we include, and the location string we previously defined.
        Intent intent = getIntent();
        String location  = intent.getStringExtra("location");

        mLocationTextView.setText("Here are all the restaurants near:" + location);
        getRestaurants(location);

        //we'll create an ArrayAdapter and set our ListView adapter to the new adapter
//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1 );
//        mListView.setAdapter(adapter);

        //Creating a Toast to display restaurant name when  a list item is clicked
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterViewer, View view, int i, long l) {
//                String restaurant = ((TextView)view).getText().toString();
//                Toast.makeText(RestaurantsActivity.this, restaurant, Toast.LENGTH_SHORT).show();
//
//            }
//        });

      }


        private void getRestaurants(String location) {
        final YelpService yelpService = new YelpService();

        yelpService.findRestaurants(location, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {

                mRestaurants = yelpService.processResults(response);
                RestaurantsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String[] restaurantNames = new String[mRestaurants.size()];
                        for (int i= 0; i < restaurantNames.length; i++){

                            restaurantNames[i] = mRestaurants.get(i).getName();
                        }

                        ArrayAdapter adapter = new ArrayAdapter(RestaurantsActivity.this, android.R.layout.simple_list_item_1, restaurantNames);
                        mListView.setAdapter(adapter);

                        for (Restaurant restaurant : mRestaurants){
                            Log.d(TAG, "Name: " + restaurant.getName());
                            Log.d(TAG, "Phone: " + restaurant.getPhone() );
                            Log.d(TAG, "Website: " + restaurant.getWebsite());
                            Log.d(TAG, "Image Url: " + restaurant.getImageUrl());
                            Log.d(TAG, "Rating: " + Double.toString(restaurant.getRating()));
                            Log.d(TAG, "Address: " + android.text.TextUtils.join(",", restaurant.getAddress()) );
                            Log.d(TAG, "categories: " + restaurant.getCategories().toString());

                        }

                    }

                });

             }

        });

    }

}
