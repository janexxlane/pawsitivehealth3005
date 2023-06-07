package com.example.pawsitivehealth3005;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pawsitivehealth3005.Database.ArztpraxisDatabase;
import com.example.pawsitivehealth3005.Database.ArztpraxisEntity;
import com.example.pawsitivehealth3005.Database.MedikamenteDAO;
import com.example.pawsitivehealth3005.Database.MedikamenteDatabase;
import com.example.pawsitivehealth3005.Database.MedikamenteEntity;
import com.example.pawsitivehealth3005.Database.SpaziergängeDatabase;
import com.example.pawsitivehealth3005.Database.SpaziergängeEntity;
import com.example.pawsitivehealth3005.Database.StammdatenDatabase;
import com.example.pawsitivehealth3005.Database.StammdatenEntity;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Datum_heute extends AppCompatActivity {
    private ActivityResultLauncher<Intent> cameraLauncher;
    private byte[] imageBytesEssen;
    private byte[] imageBytesAusscheidung;

    private ActivityResultLauncher<Intent> cameraLauncherEssen;
    private ActivityResultLauncher<Intent> cameraLauncherAusscheidung;

    private ExecutorService executorService;
    private DatabaseOperations databaseOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datum_heute);

        // Get Date von Calender View und zeigts an in Datum heute "Datum heute"
        TextView DatumAnzeige = findViewById(R.id.Datum_Anzeige);

        Intent intent = getIntent();
        String DatumAnzeigeString = intent.getStringExtra("Datum");
        DatumAnzeige.setText(DatumAnzeigeString);

        // Initialize ExecutorService
        executorService = Executors.newSingleThreadExecutor();
        databaseOperations = new DatabaseOperations();

        // Camera for Essen
        ImageView imageViewEssen = findViewById(R.id.FotoEssen);
        ImageButton buttonEssen = findViewById(R.id.imageButtonEssen);

        cameraLauncherEssen = registerForActivityResult(
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
        // Man braucht einen setOnTouchListener für den ImageButton ( mit einem normalen Button und Onclicklistener gehts aber auch nicht)
        buttonEssen.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Der ImageButton wurde gedrückt
                    Intent openCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (openCamera.resolveActivity(getPackageManager()) != null) {
                        cameraLauncherEssen.launch(openCamera);
                    } else {
                        Toast.makeText(getApplicationContext(), "Button Fehler.", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
                return false;
            }
        });

        // Camera for Ausscheidung
        ImageView imageViewAusscheidung = findViewById(R.id.FotoAusscheidung);
        ImageButton buttonAusscheidung = findViewById(R.id.imageButtonAusscheidung);

        cameraLauncherAusscheidung = registerForActivityResult(
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

        buttonAusscheidung.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Der ImageButton wurde gedrückt
                    Intent openCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (openCamera.resolveActivity(getPackageManager()) != null) {
                        cameraLauncherAusscheidung.launch(openCamera);
                    } else {
                        Toast.makeText(getApplicationContext(), "Button Fehler", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
                return false;
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

        // Notizen aus datum_heute layout
        EditText notizenEditText = findViewById(R.id.notesEssen);
        String notizenEssen = notizenEditText.getText().toString();

        EditText notizenEditText2 = findViewById(R.id.notesAusscheidung);
        String notizenAusscheidung = notizenEditText2.getText().toString();

        // Speichern von eingegebenen Daten
        Button buttonSave = findViewById(R.id.speichernButton);
        buttonSave.setOnClickListener(view -> {
            // Erzeuge eine Instanz der Entity mit den gespeicherten Werten (die über die Intents kommen oder direkt aus datum_heute sind)
            StammdatenEntity stammdatenEntity = new StammdatenEntity();
            SpaziergängeEntity spaziergängeEntity = new SpaziergängeEntity();
            MedikamenteEntity medikamenteEntity= new MedikamenteEntity();
            ArztpraxisEntity arztpraxisEntity= new ArztpraxisEntity();


            //  MEDIKAMENTE
            // holt medikamente daten aus der neues Medikamente Activity
            String mediNameString = getIntent().getStringExtra("mediNameString");
            int mgAngabe = getIntent().getIntExtra("mgAngabe", 0);
            Boolean isChecked = getIntent().getBooleanExtra("isChecked", false);
            Boolean isChecked2 = getIntent().getBooleanExtra("isChecked2", false);
            Boolean isChecked3 = getIntent().getBooleanExtra("isChecked3", false);

            //ARZTBESUCHE
            String arztNameString = getIntent().getStringExtra("arztNameString");
            String arztNummerString = getIntent().getStringExtra("arztNummerString");
            String diagnoseString = getIntent().getStringExtra("diagnoseString");

            //SPAZIERGÄNGE
            String spaziergangName = getIntent().getStringExtra("spaziergangName");
            String stepCount = getIntent().getStringExtra("stepCount");

            // Aus Datum_heute
            stammdatenEntity.setNotizenAusscheidung(notizenAusscheidung);
            stammdatenEntity.setNotizenEssen(notizenEssen);
            stammdatenEntity.setDatumAnzeigeString(DatumAnzeigeString);
            stammdatenEntity.setImageBytesE(imageBytesEssen);
            stammdatenEntity.setImageBytesA(imageBytesAusscheidung);

            //Aus Medis
            medikamenteEntity.setMediNameString(mediNameString);
            medikamenteEntity.setMgAngabe(mgAngabe);
            medikamenteEntity.setIsChecked(isChecked);
            medikamenteEntity.setIsChecked2(isChecked2);
            medikamenteEntity.setIsChecked3(isChecked3);

            //Aus Arzttermin
            arztpraxisEntity.setArztNameString(arztNameString);
            arztpraxisEntity.setArztNummerString(arztNummerString);
            arztpraxisEntity.setDiagnoseString(diagnoseString);

            //Aus Sapziergang
            spaziergängeEntity.setSpaziergangName(spaziergangName);
            spaziergängeEntity.setStepCount(stepCount);

            // Speichern der Daten in der Datenbank über die DatabaseOperations-Klasse
            databaseOperations.insertMedikament(medikamenteEntity, Datum_heute.this);
            databaseOperations.insertArztpraxis(arztpraxisEntity, Datum_heute.this);
            databaseOperations.insertSpaziergänge(spaziergängeEntity,Datum_heute.this);
            databaseOperations.insertStammdaten(stammdatenEntity,Datum_heute.this);
            Toast.makeText(getApplicationContext(), "Daten gespeichert", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }

    // Separate Klasse for database operations
    private class DatabaseOperations {
        public void insertMedikament(MedikamenteEntity medikamenteEntity, Context context) {
            // Erstelle eine Instanz der Datenbank
            MedikamenteDatabase medikamenteDatabase = MedikamenteDatabase.getInstance(context);

            // Führe die Datenbankoperationen auf einem separaten Thread aus
            new Thread(() -> {
                // Rufe die insertMedikament-Methode der DAO-Schnittstelle auf, um das Medikament in der Datenbank zu speichern
                // medikamenteDatabase.medikamenteDAO().insertMedikament((MedikamenteEntity)medikamenteEntity);
                medikamenteDatabase.medikamenteDAO().insertMedikament(medikamenteEntity);
            }).start();
            System.out.println("Medikamente gespeichert");
        }


        public void insertArztpraxis(ArztpraxisEntity arztpraxisEntity, Context context) {
            // Erstelle eine Instanz der Datenbank

            ArztpraxisDatabase arztpraxisDatabase = ArztpraxisDatabase.getInstance(context);
            // Führe die Datenbankoperationen auf einem separaten Thread aus
            new Thread(() -> {
                // Rufe die insertMedikament-Methode der DAO-Schnittstelle auf, um das Medikament in der Datenbank zu speichern

                arztpraxisDatabase.arztpraxisDAO().insertArztpraxis(arztpraxisEntity);
            }).start();
            System.out.println("Arztpraxis gespeichert");
        }

        public void insertSpaziergänge(SpaziergängeEntity spaziergängeEntity, Context context) {
            // Erstelle eine Instanz der Datenbank
            SpaziergängeDatabase spaziergängeDatabase= SpaziergängeDatabase.getInstance(context);

            // Führe die Datenbankoperationen auf einem separaten Thread aus
            new Thread(() -> {
                // Rufe die insertMedikament-Methode der DAO-Schnittstelle auf, um das Medikament in der Datenbank zu speichern
                spaziergängeDatabase.spaziergängeDAO().insertSpaziergänge(spaziergängeEntity);

            }).start();
            System.out.println("Spaziergänge gespeichert");
    }

        public void insertStammdaten(StammdatenEntity stammdatenEntity, Context context) {
            // Erstelle eine Instanz der Datenbank

            StammdatenDatabase stammdatenDatabase= StammdatenDatabase.getInstance(context);
            // Führe die Datenbankoperationen auf einem separaten Thread aus
            new Thread(() -> {
                // Rufe die insertMedikament-Methode der DAO-Schnittstelle auf, um das Medikament in der Datenbank zu speichern
                stammdatenDatabase.stammdatenDAO_datumheute().insertStammdaten(stammdatenEntity);

            }).start();
            System.out.println("Stammdaten gespeichert");
        }

}}

