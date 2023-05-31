package com.example.pawsitivehealth3005;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class neuerArzttermin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neuer_arzttermin);


        //Speichern von Name
        EditText arztName = findViewById(R.id.nameArzt);
        String arztNameString = arztName.getText().toString();
        //Speichern von telNummer
        EditText arztNummer = findViewById(R.id.phoneNr);
        String arztNummerString = arztNummer.getText().toString();
        //Speichern von Diagnose
        EditText diagnose = findViewById(R.id.diagnose);
        String diagnoseString = diagnose.getText().toString();


        Button saveBtn = findViewById(R.id.saveTerminButton);
        saveBtn.setOnClickListener(view -> {

            //Schickt in datum_heute
            Intent myintent = new Intent(this, Datum_heute.class);
            myintent.putExtra("arztNameString", arztNameString);
            myintent.putExtra("arztNummerString", arztNummerString);
            myintent.putExtra("diagnoseString", diagnoseString);
            startActivity(myintent);

        });

        Button backBtn = findViewById(R.id.backButtonTermin);
        backBtn.setOnClickListener(view -> {

            Intent myintent = new Intent(this, Datum_heute.class);
        });

    } }


