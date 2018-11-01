package com.wess58.myrestaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantsActivity extends AppCompatActivity {

    @BindView(R.id.textView2) TextView mLocationTextView;
    @BindView(R.id.listView) ListView  mListView;
    private String[] restaurants = new String[]{"Java","Subway","KFC","Galito's"
            ,"Fritaz","Kwa Mary","CJ's","Kilimanjaro", "Chipotle","Manhattan"
    };
    private String[] cuisines = new String[] {"Vegan Food", "Breakfast", "Fishs Dishs",
            "Scandinavian", "Coffee", "English Food", "Burgers", "Fast Food", "Noodle Soups", "Mexican"
    };


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


        //we'll create an ArrayAdapter and set our ListView adapter to the new adapter
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, restaurants );
        mListView.setAdapter(adapter);

        //Creating a Toast to display restaurant name when  a list item is clicked
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterViewer, View view, int i, long l) {
                String restaurant = ((TextView)view).getText().toString();
                Toast.makeText(RestaurantsActivity.this, restaurant, Toast.LENGTH_SHORT).show();
             }
        });

    }
}
