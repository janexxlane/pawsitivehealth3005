package com.example.pawsitivehealth3005;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

public class Homescreen extends AppCompatActivity {

    /*ÜBERSICHT
      ROOMDB
      DailyEintrag = Entitätsklasse
      EintragDAO = DAO Interface
      MeineDatabase = Datenbankklasse



      //Baustellen:
      TOOLBAR/ Menü
      Calendar View ???
      Performancee issues
      LOG/ Bearbeitungsfunktion

*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        CalendarView calendarView = findViewById(R.id.calendarView);

        // Unsicher ob wir den Teil brauchen; CalenderView ist ein Problem für ein anderes Mal
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // Datum ausgewählt, hier kannst du die Daten speichern oder andere Aktionen durchführen

                // Erstelle ein Datumobjekt mit den ausgewählten Werten
                Calendar selectedDate = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    selectedDate = Calendar.getInstance();
                    selectedDate.set(year, month, dayOfMonth);
                    // i glaub des brauch ma ned aber i bin ma ned sicher deswegen is es do
                    // int jahr = selectedDate.get(Calendar.YEAR);
                    // int monat = selectedDate.get(Calendar.MONTH);
                    // int tag = selectedDate.get(Calendar.DAY_OF_MONTH);


                }

                //String speicherDate = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth);
                Button button1 = findViewById(R.id.hinzu);
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent myintent = new Intent(Homescreen.this, com.example.pawsitivehealth3005.Datum_heute.class);
                        //   myintent.putExtra("Datum", speicherDate);
                        startActivity(myintent);
                    }
                });
            }
        });
    }
}
