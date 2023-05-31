package com.example.pawsitivehealth3005;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class neuerSpaziergang extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor stepCounterSensor;
    private SensorEventListener stepCounterListener;
    private float stepCount;
    private TextView stepCountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neuer_spaziergang);

        // Initialize SensorManager
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Retrieve step counter sensor
        stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        if (stepCounterSensor == null) {
            // Stepcounter sensor is not supported on this device
            Toast.makeText(getApplicationContext(), "Schrittzähler nicht unterstützt", Toast.LENGTH_SHORT).show();
        }

        stepCountTextView = findViewById(R.id.trackerAnzeige);

        Button button1 = findViewById(R.id.backButton);
        EditText name = findViewById(R.id.name);
        String nameString = name.getText().toString();

        Intent myintent = new Intent(this, Datum_heute.class);
        myintent.putExtra("spaziergangName", nameString);
        myintent.putExtra("stepCount", stepCount);
        startActivity(myintent);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(neuerSpaziergang.this, Datum_heute.class);
                startActivity(myintent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (stepCounterSensor != null) {
            sensorManager.registerListener(stepCounterListener, stepCounterSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (stepCounterSensor != null) {
            sensorManager.unregisterListener(stepCounterListener);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        stepCounterListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                stepCount = event.values[0];
                // Update your UI or perform any necessary actions with the step count value
                updateStepCountUI(stepCount);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                // Optional: Implement if needed
            }
        };

        if (stepCounterSensor != null) {
            sensorManager.registerListener(stepCounterListener, stepCounterSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (stepCounterSensor != null) {
            sensorManager.unregisterListener(stepCounterListener);
        }
    }

    private void updateStepCountUI(float stepCount) {
        stepCountTextView.setText(String.valueOf(stepCount));
    }
}
