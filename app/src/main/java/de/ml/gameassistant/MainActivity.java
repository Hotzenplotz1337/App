package de.ml.gameassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.view.View;
import android.hardware.SensorEventListener;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private ImageButton button1, button2, button3, button4;
    private SensorManager sm_acc;
    private Sensor acc;

    private long then2 = 0;

    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sm_acc = (SensorManager) getSystemService(SENSOR_SERVICE);
        acc = sm_acc.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.GONE);
        button3.setVisibility(View.GONE);
        button4.setVisibility(View.GONE);
    }

    protected void onResume() {
        super.onResume();
        //Sensor-Listener werden gestartet (aktiv, wenn App aktiv ist)
        sm_acc.registerListener(this, acc, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        //Sensor-Listener werden gestoppt (wenn App geschlossen oder pausiert wird)
        sm_acc.unregisterListener(this);

    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {
        //Sensor s erhält die Sensodaten als Array-List
        Sensor s = event.sensor;
        //Falls Sensor s Daten vom Accelerometer enthält:
        if (s.getType() == Sensor.TYPE_ACCELEROMETER) {
            //Werte für X- und Y-Achse werden ausgelesen und in Ints gespeichert. "Z" wird nicht benötigt.
            int x = Math.round(event.values[0]);
            int y = Math.round(event.values[1]);
            //int z = Math.round(event.values[2]);
            //die aktuelle Zeit wird erfasst
            long now = System.currentTimeMillis();

            //Falls der X-Wert niedriger ist als -6 ist und mehr als 1 Sekunde vergangen ist, wird voreblättert.
            if((x < -6 ) && (now - then2) > 1000) {
                counter++;
                aktualisieren();
                then2 = now;

            }
            //Falls der X-Wert höher als 6 ist und mehr als 1 Sekunde vergangen ist, wird zurückgeblättert.
            if((x > 6 ) && (now - then2) > 1000) {
                counter--;
                aktualisieren();
                then2 = now;

            }
        }

    }


    public void aktualisieren() {
        if (counter > 4) {counter = 1;} //"5. Seite" --> 1. Seite
        if (counter < 1) {counter = 4;} //"0. Seite" --> 4. Seite

        button1.setVisibility(View.GONE);
        button2.setVisibility(View.GONE);
        button3.setVisibility(View.GONE);
        button4.setVisibility(View.GONE);
        switch (counter) {
            case 1:
                button1.setVisibility(View.VISIBLE);
                break;
            case 2:
                button2.setVisibility(View.VISIBLE);
                break;
            case 3:
                button3.setVisibility(View.VISIBLE);
                break;
            case 4:
                button4.setVisibility(View.VISIBLE);
                break;

            default:
                break;
        }

    }

    public void wuerfeln(View view) {
        startActivity(new Intent(MainActivity.this, Wuerfeln.class));
    }

    public void losen(View view) {
        startActivity(new Intent(MainActivity.this, NamenLosen.class));
    }

    public void rechner(View view) {
    }

    public void stadtlandfluss(View view) {
        startActivity(new Intent(MainActivity.this, StadtLandFluss.class));
    }
}


