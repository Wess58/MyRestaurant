package com.wess58.myrestaurant;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoryActivity extends AppCompatActivity {
    @BindView(R.id.textView3) TextView mStoryTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        ButterKnife.bind(this);

        Typeface IndieFonts = Typeface.createFromAsset(getAssets(),"fonts/Indie_Flower/IndieFlower.ttf");
        mStoryTextView.setTypeface(IndieFonts);


        Intent intent = getIntent();
        String Verb = intent.getStringExtra("Verb");
        String Noun = intent.getStringExtra("Noun");
        mStoryTextView.setText("My arctic " + Noun + " ate it and now I have to start " + Verb + " all over again");

    }
}
