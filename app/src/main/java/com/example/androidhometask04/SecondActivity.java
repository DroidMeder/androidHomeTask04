package com.example.androidhometask04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        String text = getIntent().getStringExtra("Iaha");
        ((TextView)findViewById(R.id.text2activity)).setText(text);

    }

    public void fatahh(View view) {
        //super.finish();
        finish();
        //onDestroy();
    }
}