package com.wess58.myrestaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MadLibsActivity extends AppCompatActivity {
    @BindView(R.id.button4) Button mSubmitWords;
    @BindView(R.id.editText2) EditText mEnterVerbInput;
    @BindView(R.id.editText3) EditText mEnterNounInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mad_libs);
        ButterKnife.bind(this);

        mSubmitWords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Verb = mEnterVerbInput.getText().toString();
                String Noun = mEnterNounInput.getText().toString();

                Intent intent = new Intent(MadLibsActivity.this, StoryActivity.class);
                intent.putExtra("Verb", Verb);
                intent.putExtra("Noun", Noun);
                startActivity(intent);
            }
        });


    }
}
