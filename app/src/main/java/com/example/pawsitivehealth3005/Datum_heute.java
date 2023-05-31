package com.example.pawsitivehealth3005;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pawsitivehealth3005.DailyEintrag;
import com.example.pawsitivehealth3005.MeineDatabase;
import com.example.pawsitivehealth3005.R;
import com.example.pawsitivehealth3005.neuerArzttermin;
import com.example.pawsitivehealth3005.neuerSpaziergang;
import com.example.pawsitivehealth3005.neuesMedikament;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.ExecutorService;

public class Datum_heute extends AppCompatActivity {
    private ActivityResultLauncher<Intent> cameraLauncher;
    private byte[] imageBytesEssen;
    private byte[] imageBytesAusscheidung;

    private ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datum_heute);

        // Get selected date from CalendarView and display it in "Datum heute"
        TextView DatumAnzeige = findViewById(R.id.Datum_Anzeige);
        String DatumAnzeigeString = DatumAnzeige.getText().toString();

        // Camera for Essen
        ImageView imageViewEssen = findViewById(R.id.FotoEssen);
        ImageButton buttonEssen = findViewById(R.id.imageButtonEssen);

        cameraLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null && data.getData() != null) {
                            executorService.execute(() -> {
                                try {
                                    Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                    imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                                    imageBytesEssen = baos.toByteArray();
                                    runOnUiThread(() -> imageViewEssen.setImageBitmap(imageBitmap));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });
                        } else {
                            Toast.makeText(getApplicationContext(), "Keine Kamera-App gefunden.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Keine Kamera-App gefunden.", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        buttonEssen.setOnClickListener(view -> {
            Intent openCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (openCamera.resolveActivity(getPackageManager()) != null) {
                cameraLauncher.launch(openCamera);
            } else {
                Toast.makeText(getApplicationContext(), "Keine Kamera-App gefunden.", Toast.LENGTH_SHORT).show();
            }
        });

        // Camera for Ausscheidung
        ImageView imageViewAusscheidung = findViewById(R.id.FotoAusscheidung);
        ImageButton buttonAusscheidung = findViewById(R.id.imageButtonAusscheidung);

        cameraLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null && data.getData() != null) {
                            executorService.execute(() -> {
                                try {
                                    Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                    imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                                    imageBytesAusscheidung = baos.toByteArray();
                                    runOnUiThread(() -> imageViewAusscheidung.setImageBitmap(imageBitmap));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });
                        } else {
                            Toast.makeText(getApplicationContext(), "Fehler", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Fehler", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        buttonAusscheidung.setOnClickListener(view -> {
            Intent openCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (openCamera.resolveActivity(getPackageManager()) != null) {
                cameraLauncher.launch(openCamera);
            } else {
                Toast.makeText(getApplicationContext(), "Fehler", Toast.LENGTH_SHORT).show();
            }
        });

        // NEUE MEDIS/ TERMINE/ SPAZIERGÄNGE BUTTONS
        Button buttonMedis = findViewById(R.id.MedikamenteButton);
        buttonMedis.setOnClickListener(view -> {
            Intent myintent = new Intent(Datum_heute.this, neuesMedikament.class);
            startActivity(myintent);
        });

        Button buttonArzt = findViewById(R.id.ArztterminButton);
        buttonArzt.setOnClickListener(view -> {
            Intent myintent = new Intent(Datum_heute.this, neuerArzttermin.class);
            startActivity(myintent);
        });

        Button buttonSpaz = findViewById(R.id.SpaziergangButton);
        buttonSpaz.setOnClickListener(view -> {
            Intent myintent = new Intent(Datum_heute.this, neuerSpaziergang.class);
            startActivity(myintent);
        });

        // Notizen aus datum_heute
        EditText notizenEditText = findViewById(R.id.notesEssen);
        String notizenEssen = notizenEditText.getText().toString();

        EditText notizenEditText2 = findViewById(R.id.notesAusscheidung);
        String notizenAusscheidung = notizenEditText2.getText().toString();

        // Speichern von eingegebenen Daten
        Button buttonSave = findViewById(R.id.speichernButton);
        buttonSave.setOnClickListener(view -> {
            // Erstelle eine Instanz der Datenbank
            MeineDatabase database = MeineDatabase.getInstance(this);

            // Erzeuge eine Instanz der Entity mit den gespeicherten Werten (die über die Intents kommen oder direkt aus datum_heute sind)
            DailyEintrag eintrag = new DailyEintrag();

            // Aus Datum_heute
            eintrag.setNotizenAusscheidung(notizenAusscheidung);
            eintrag.setNotizenEssen(notizenEssen);
            eintrag.setDatumAnzeigeString(DatumAnzeigeString);
            eintrag.setImageBytesE(imageBytesEssen);
            eintrag.setImageBytesA(imageBytesAusscheidung);

            // Rufe die insertMedikament-Methode der DAO-Schnittstelle auf, um das Medikament in der Datenbank zu speichern
            database.medikamentDao().insertMedikament(eintrag);

            Toast.makeText(getApplicationContext(), "Daten gespeichert", Toast.LENGTH_SHORT).show();
        });
    }
}
