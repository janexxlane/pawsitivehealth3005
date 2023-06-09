package com.example.pawsitivehealth3005;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Homescreen extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;
    Homescreen homescreen;
    Settings settings;
    Log log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        homescreen = new Homescreen();
        settings = new Settings();
        log = new Log();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);

        CalendarView calendarView = findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar selectedDate = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    selectedDate = Calendar.getInstance();
                    selectedDate.set(year, month, dayOfMonth);
                }

                Button button1 = findViewById(R.id.hinzu);
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent myintent = new Intent(Homescreen.this, com.example.pawsitivehealth3005.Datum_heute.class);
                        startActivity(myintent);
                    }
                });
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    // Do nothing since we are already in the Home Activity
                    return true;

                case R.id.settings:
                    Intent settingsIntent = new Intent(Homescreen.this, Settings.class);
                    startActivity(settingsIntent);
                    return true;

                case R.id.log:
                    Intent logIntent = new Intent(Homescreen.this, Log.class);
                    startActivity(logIntent);
                    return true;
        }
        return false;
    }
}
