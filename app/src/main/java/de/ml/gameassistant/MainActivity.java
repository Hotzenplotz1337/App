package de.ml.gameassistant;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.view.View;
import android.hardware.SensorEventListener;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    //4 Bild-Button: "Würfeln", "Namen Losen", "Rechner", "Stadt Land Fluss"
    private ImageButton button1, button2, button3, button4;
    //Sensor und SensorManager für den Beschleunigungs- bzw. Neiungsmesser
    private SensorManager sm_acc;
    private Sensor acc;

    //wichtige Variable, damit immer nur einmal umgeblättert wird (unten mehr dazu)
    private long then2 = 0;
    //Speichert den aktuell aktivierten ImageButton
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sm_acc = (SensorManager) getSystemService(SENSOR_SERVICE);
        acc = sm_acc.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //IDs werden zugeordnet
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        //Alle Buttons versteckt außer der erste
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.GONE);
        button3.setVisibility(View.GONE);
        button4.setVisibility(View.GONE);

        //Toast zur Bedienungshilfe
        Context context = getApplicationContext();
        CharSequence text = "Zum Navigieren Smartphone nach rechts oder links neigen";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    protected void onResume() {
        super.onResume();
        //Sensor-Listener werden gestartet (aktiv, wenn App aktiv ist)
        sm_acc.registerListener(this, acc, SensorManager.SENSOR_DELAY_NORMAL); //Standart-Delay
    }

    protected void onPause() {
        super.onPause();
        //Sensor-Listener werden gestoppt (wenn App geschlossen oder pausiert wird)
        sm_acc.unregisterListener(this);

    }

    //Der Vollständigkeit halber
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {
        //Sensor s erhält die Sensodaten als Array-List
        Sensor s = event.sensor;
        //Falls Sensor s Daten vom Accelerometer enthält:
        if (s.getType() == Sensor.TYPE_ACCELEROMETER) {
            //Wert für X-Achse werden ausgelesen und in Ints gespeichert. "Z" und "Y" werden nicht benötigt.
            int x = Math.round(event.values[0]);
            //die aktuelle Zeit wird erfasst
            long now = System.currentTimeMillis();

            //Falls der X-Wert niedriger ist als -6 ist und mehr als 1 Sekunde vergangen ist, wird voreblättert.
            if((x < -6 ) && (now - then2) > 1000) {
                //Counter wird erhöht, damit aktualisieren() den richtigen Button auf visible setzt
                counter++;
                //Funktion zum "Umblättern" wird ausgelöst
                aktualisieren();
                //then2 bekommt neuen Wert
                then2 = now;

            }
            //Falls der X-Wert höher als 6 ist und mehr als 1 Sekunde vergangen ist, wird zurückgeblättert.
            if((x > 6 ) && (now - then2) > 1000) {
                //Counter wird erhöht, damit aktualisieren() den richtigen Button auf visible setzt
                counter--;
                //Funktion zum "Umblättern" wird ausgelöst
                aktualisieren();
                //then2 bekommt neuen Wert
                then2 = now;

            }
        }

    }


    public void aktualisieren() {
        //Logik, damit von der 1. zur 4. und von der 4. zur 1. Seite gewechselt werden kann
        if (counter > 4) {counter = 1;} //"5. Seite" --> 1. Seite
        if (counter < 1) {counter = 4;} //"0. Seite" --> 4. Seite

        //alle Buttons werden versteckt
        button1.setVisibility(View.GONE);
        button2.setVisibility(View.GONE);
        button3.setVisibility(View.GONE);
        button4.setVisibility(View.GONE);

        //Hier wird, abhängig vom Counter, jeweils der richtige Button auf visible gesetzt
        switch (counter) {
            case 1: button1.setVisibility(View.VISIBLE);break;
            case 2: button2.setVisibility(View.VISIBLE);break;
            case 3: button3.setVisibility(View.VISIBLE);break;
            case 4: button4.setVisibility(View.VISIBLE);break;
            default: break;
        }
    }

    //Activities lassen sich über die ImageButtons starten
    public void wuerfeln        (View view) {startActivity(new Intent(MainActivity.this, Wuerfeln.class));}
    public void losen           (View view) {startActivity(new Intent(MainActivity.this, NamenLosen.class));}
    public void rechner         (View view) {startActivity(new Intent(MainActivity.this, Rechner.class));}
    public void stadtlandfluss  (View view) {startActivity(new Intent(MainActivity.this, StadtLandFluss.class));}
}


