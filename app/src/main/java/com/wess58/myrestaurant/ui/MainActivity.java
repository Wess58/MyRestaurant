package com.wess58.myrestaurant.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wess58.myrestaurant.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    @BindView(R.id.textView) TextView mAppNameTextView;
    @BindView(R.id.editText) EditText mLocationEditText;
    @BindView(R.id.button2) Button mToastActionButton;
    @BindView(R.id.button) Button mFindRestaurantsButton;
    @BindView(R.id.button3) Button mMadLibsButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //using fonts

        Typeface keniaFonts = Typeface.createFromAsset(getAssets(),"fonts/Kenia/Kenia-Regular.ttf");
        mAppNameTextView.setTypeface(keniaFonts);


        mFindRestaurantsButton.setOnClickListener(new View.OnClickListener() {
            @Override
              public void onClick(View v) {
                String location = mLocationEditText.getText().toString();

                //intent to maneuver to another page/activity class
                Intent intent = new Intent(MainActivity.this, RestaurantsActivity.class);
                intent.putExtra("location", location);
                startActivity(intent);
            }


        });


          mToastActionButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Toast.makeText(MainActivity.this, "Welcome!!", Toast.LENGTH_SHORT).show();
              }
          });


          mMadLibsButton.setOnClickListener(new View.OnClickListener(){
              @Override
                public void onClick(View v){
                 Intent intent  = new Intent(MainActivity.this, MadLibsActivity.class);
                 startActivity(intent);
              }
        });


    }
}
