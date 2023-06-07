package com.example.pawsitivehealth3005;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class neuesMedikament extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neues_medikament);

        //Speichern von Name
        EditText medikamentenName= findViewById(R.id.name);
        String mediNameString = medikamentenName.getText().toString();
        //Speichern von mg
        EditText editTextNumberSigned = findViewById(R.id.editTextNumberSigned);
        int mgAngabe = Integer.parseInt(editTextNumberSigned.getText().toString());

        //Speichern von Häufigkeit
        CheckBox morgenCheck = findViewById(R.id.morgensCheck);
        boolean isChecked = morgenCheck.isChecked();
        CheckBox mittagCheck = findViewById(R.id.mittagsCheck);
        boolean isChecked2 = mittagCheck.isChecked();
        CheckBox abendCheck = findViewById(R.id.abendsCheck);
        boolean isChecked3 = abendCheck.isChecked();


        Button saveBtn = findViewById(R.id.saveButton);
        saveBtn.setOnClickListener(view -> {
            //Schickt in datum_heute
            Intent myintent = new Intent(neuesMedikament.this, Datum_heute.class);
            myintent.putExtra("mediNameString", mediNameString);
            myintent.putExtra("MediDosisInt", mgAngabe);
            myintent.putExtra("checkbox_status_morgens", isChecked);
            myintent.putExtra("checkbox_status_morgens", isChecked2);
            myintent.putExtra("checkbox_status_morgens", isChecked3);
             startActivity(myintent);

        });

        Button backBtn = findViewById(R.id.backButton);
        backBtn.setOnClickListener(view -> {

            Intent myintent = new Intent(this, com.example.pawsitivehealth3005.Datum_heute.class);
            startActivity(myintent);
        });

    }}

