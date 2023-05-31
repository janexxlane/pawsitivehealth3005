package com.example.pawsitivehealth3005;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonStart = findViewById(R.id.starteApp);
        buttonStart.setOnClickListener(view -> {
            Intent myintent = new Intent(this, Homescreen.class);
            startActivity(myintent);
        });
    }

}
