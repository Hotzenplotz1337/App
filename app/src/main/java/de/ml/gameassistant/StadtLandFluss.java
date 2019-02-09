package de.ml.gameassistant;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class StadtLandFluss extends AppCompatActivity implements SensorEventListener {

    private TextView anzeige;
    private SensorManager sm_prox;
    private Sensor prox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stadt_land_fluss);

        anzeige = findViewById(R.id.textView);

        sm_prox = (SensorManager) getSystemService(SENSOR_SERVICE);
        prox = sm_prox.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    }

    protected void onResume() {
        super.onResume();
        //Sensor-Listener werden gestartet (aktiv, wenn App aktiv ist)
        sm_prox.registerListener(this, prox, SensorManager.SENSOR_DELAY_UI);
    }

    protected void onPause() {
        super.onPause();
        //Sensor-Listener werden gestoppt (wenn App geschlossen oder pausiert wird)
        sm_prox.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    //Wird jedes mal ausgeführt, wenn sich Sensordaten ändern
    public void onSensorChanged(SensorEvent event) {
        //Sensor s erhält die Sensodaten als Array-List
        Sensor s = event.sensor;
        //Falls Sensor s Daten vom Accelerometer enthält:

            //Falls Sensor s Daten vom Abstandssensor enthält wird zum Tutorial zurückgeblättert (Seite 0) und es wird in einer Textview angezeigt,
            //ob der Sensor bedeckt oder nicth bedeckt ist.
         if (s.getType() == Sensor.TYPE_PROXIMITY) {
            float dist = event.values[0];
            if (dist == 0.0) {
                Context context = getApplicationContext();
                CharSequence text = "Hello toast!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                drehen(null);
            }


        }

    }

    public void drehen(View view) {
        Random rand = new Random();

        int n = rand.nextInt(26) + 1;
        //50 is the maximum and the 1 is our minimum.

        String buchstabe = "";

        switch (n) {
            case 1:  buchstabe = "A"; break;
            case 2:  buchstabe = "B"; break;
            case 3:  buchstabe = "C"; break;
            case 4:  buchstabe = "D"; break;
            case 5:  buchstabe = "E"; break;
            case 6:  buchstabe = "F"; break;
            case 7:  buchstabe = "G"; break;
            case 8:  buchstabe = "H"; break;
            case 9:  buchstabe = "I"; break;
            case 10:  buchstabe = "J"; break;
            case 11:  buchstabe = "K"; break;
            case 12:  buchstabe = "L"; break;
            case 13:  buchstabe = "M"; break;
            case 14:  buchstabe = "N"; break;
            case 15:  buchstabe = "O"; break;
            case 16:  buchstabe = "P"; break;
            case 17:  buchstabe = "Q"; break;
            case 18:  buchstabe = "R"; break;
            case 19:  buchstabe = "S"; break;
            case 20:  buchstabe = "T"; break;
            case 21:  buchstabe = "U"; break;
            case 22:  buchstabe = "V"; break;
            case 23:  buchstabe = "W"; break;
            case 24:  buchstabe = "X"; break;
            case 25:  buchstabe = "Y"; break;
            case 26:  buchstabe = "Z"; break;
            default: break;
        }

        anzeige.setText(buchstabe);
    }
}
