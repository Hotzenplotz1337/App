package de.ml.gameassistant;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Wuerfeln extends AppCompatActivity implements SensorEventListener {

    // In der TextView wird das zufallsgenrierte Würfelergebnis angezeigt

    private TextView ergebnis;

    // Die ImageView zeigt je nach Anzahl der Würfel ein anderes Würfelbild an,
    // das beim Würfelvorgang animiert wird

    private ImageView wuerfel1, wuerfel2, wuerfel3, wuerfel4, wuerfel5;

    // Der Button zeigt an wieviel Würfel gewürfelt werden und kann den 'Wurf' starten

    private Button button1, button2, button3, button4, button5;

    // Werden für die Umsetzung unsrer Gestensteuerung benötigt

    private SensorManager sm_acc, sm_prox;
    private Sensor acc, prox;
    private long then2 = 0;
    private int counter = 0;
    private int dicecounter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wuerfeln);

        // hierdurch wird darauf hingewiesen das man per Geste zwischen der Anzahl
        // der Würfeln wechseln kann
        Context context = getApplicationContext();
        CharSequence text = "Zum Navigieren Smartphone nach rechts oder links neigen";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        // Erzeugen der SensorManager Instanzen und der Sensor Objekte
        sm_acc = (SensorManager) getSystemService(SENSOR_SERVICE);
        acc = sm_acc.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm_prox = (SensorManager) getSystemService(SENSOR_SERVICE);
        prox = sm_prox.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        //Zuweisen der Ressourcen IDs
        ergebnis = findViewById(R.id.ergebnisview);

        wuerfel1 = findViewById(R.id.wuerfel1);
        wuerfel2 = findViewById(R.id.wuerfel2);
        wuerfel3 = findViewById(R.id.wuerfel3);
        wuerfel4 = findViewById(R.id.wuerfel4);
        wuerfel5 = findViewById(R.id.wuerfel5);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);

        // Startzustand ist, das 1 Würfel simuliert werden soll, somit wird die
        // entsprechende ImageView und der zugehörige Button sichtbar gemacht
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.GONE);
        button3.setVisibility(View.GONE);
        button4.setVisibility(View.GONE);
        button5.setVisibility(View.GONE);

        wuerfel1.setVisibility(View.VISIBLE);
        wuerfel2.setVisibility(View.GONE);
        wuerfel3.setVisibility(View.GONE);
        wuerfel4.setVisibility(View.GONE);
        wuerfel5.setVisibility(View.GONE);
    }

    protected void onResume() {
        super.onResume();
        //Sensor-Listener werden gestartet (aktiv, wenn App aktiv ist)
        sm_acc.registerListener(this, acc, SensorManager.SENSOR_DELAY_NORMAL);
        sm_prox.registerListener(this, prox, SensorManager.SENSOR_DELAY_UI);
    }

    protected void onPause() {
        super.onPause();
        //Sensor-Listener werden gestoppt (wenn App geschlossen oder pausiert wird)
        sm_acc.unregisterListener(this);
        sm_prox.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {

        //Sensor s erhält die Sensordaten als Array-List
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
                wechsel();
                then2 = now;
            }

            //Falls der X-Wert höher als 6 ist und mehr als 1 Sekunde vergangen ist, wird zurückgeblättert.
            else if((x > 6 ) && (now - then2) > 1000) {
                counter--;
                wechsel();
                then2 = now;
            }
        }

        // Steuerung per Entfernungssensor, die je nach Counterwert und Switchcases
        // die entsprechende Würfelmethode ausführt
        if (s.getType() == Sensor.TYPE_PROXIMITY) {
            float dist = event.values[0];
            if (dist == 0.0) {
                switch (dicecounter) {
                    case 1: wuerfeln1(null); break;
                    case 2: wuerfeln2(null); break;
                    case 3: wuerfeln3(null); break;
                    case 4: wuerfeln4(null); break;
                    case 5: wuerfeln5(null); break;
                    default: break;
                }
            }
        }
    }

    // In der Methode wechsel() wird je nach Counterwert die Richtige ImageView und der
    // zugehörige Button sichtbar gemacht
    public void wechsel() {
        if (counter > 5) {counter = 1;} //"6. Seite" --> 1. Seite
        if (counter < 1) {counter = 5;} //"0. Seite" --> 5. Seite

        button2.setVisibility(View.GONE);
        button2.setVisibility(View.GONE);
        button3.setVisibility(View.GONE);
        button4.setVisibility(View.GONE);
        button5.setVisibility(View.GONE);

        wuerfel1.setVisibility(View.GONE);
        wuerfel2.setVisibility(View.GONE);
        wuerfel3.setVisibility(View.GONE);
        wuerfel4.setVisibility(View.GONE);
        wuerfel5.setVisibility(View.GONE);

        // bestimmen, welche ImageView und welcher Button aktuell angezeigt werden
        switch (counter) {
            case 1: button1.setVisibility(View.VISIBLE);
                wuerfel1.setVisibility(View.VISIBLE);
                dicecounter = 1;
                break;
            case 2: button2.setVisibility(View.VISIBLE);
                wuerfel2.setVisibility(View.VISIBLE);
                dicecounter = 2;
                break;
            case 3: button3.setVisibility(View.VISIBLE);
                wuerfel3.setVisibility(View.VISIBLE);
                dicecounter = 3;
                break;
            case 4: button4.setVisibility(View.VISIBLE);
                wuerfel4.setVisibility(View.VISIBLE);
                dicecounter = 4;
                break;
            case 5: button5.setVisibility(View.VISIBLE);
                wuerfel5.setVisibility(View.VISIBLE);
                dicecounter = 5;
                break;
            default: break;
        }
    }

    // Methode, die entweder per Sensor oder onClick() des button1 ausgeführt wird,
    // simuliert den Wurf eines einzelnen sechsseitigen Würfels
    public void wuerfeln1(View view){
        Random rand = new Random();
        int n = rand.nextInt(6) + 1;
        String wurfergebnis = "";
        switch(n){
            case 1: wurfergebnis = "1"; break;
            case 2: wurfergebnis = "2"; break;
            case 3: wurfergebnis = "3"; break;
            case 4: wurfergebnis = "4"; break;
            case 5: wurfergebnis = "5"; break;
            case 6: wurfergebnis = "6"; break;
            default:break;
        }

        // Das zufallsgenerierte Wurfergebnis wird in TextView sichtbar gemacht
        ergebnis.setText(wurfergebnis);

        // Animation wird initialisiert und bekommt per ID Ressource zugewiesen,
        // danach wird die Animation an ImageView angewandt
        Animation animation = AnimationUtils.loadAnimation(
                getApplicationContext(),R.anim.rotate
        );
        wuerfel1.startAnimation(animation);
    }

    // Methode, die entweder per Sensor oder onClick() des button2 ausgeführt wird,
    // simuliert den Wurf von 2 sechsseitigen Würfeln
    public void wuerfeln2(View view){
        Random rand = new Random();
        String wurfergebnis = "";

        // For-Schleife, da der Switchcase je nach Würfelanzahl mehrmals hintereinander
        // ausgeführt werden muss
        for(int i = 0; i<2; i++){
            int n = rand.nextInt(6) + 1;
            switch(n){
                case 1: wurfergebnis = wurfergebnis + "1 "; break;
                case 2: wurfergebnis = wurfergebnis + "2 "; break;
                case 3: wurfergebnis = wurfergebnis + "3 "; break;
                case 4: wurfergebnis = wurfergebnis + "4 "; break;
                case 5: wurfergebnis = wurfergebnis + "5 "; break;
                case 6: wurfergebnis = wurfergebnis + "6 "; break;
                default:break;
            }
        }

        // Das zufallsgenerierte Wurfergebnis wird in TextView sichtbar gemacht
        ergebnis.setText(wurfergebnis);

        // Animation wird initialisiert und bekommt per ID Ressource zugewiesen,
        // danach wird die Animation an ImageView angewandt
        Animation animation = AnimationUtils.loadAnimation(
                getApplicationContext(),R.anim.rotate
        );
        wuerfel2.startAnimation(animation);
    }

    // Methode, die entweder per Sensor oder onClick() des button3 ausgeführt wird,
    // simuliert den Wurf von 3 sechsseitigen Würfeln
    public void wuerfeln3(View view) {
        Random rand = new Random();
        String wurfergebnis = "";

        // For-Schleife, da der Switchcase je nach Würfelanzahl mehrmals hintereinander
        // ausgeführt werden muss
        for(int i = 0; i<3; i++){
            int n = rand.nextInt(6) + 1;
            switch(n){
                case 1: wurfergebnis = wurfergebnis + "1 "; break;
                case 2: wurfergebnis = wurfergebnis + "2 "; break;
                case 3: wurfergebnis = wurfergebnis + "3 "; break;
                case 4: wurfergebnis = wurfergebnis + "4 "; break;
                case 5: wurfergebnis = wurfergebnis + "5 "; break;
                case 6: wurfergebnis = wurfergebnis + "6 "; break;
                default:break;
            }
        }

        // Das zufallsgenerierte Wurfergebnis wird in TextView sichtbar gemacht
        ergebnis.setText(wurfergebnis);

        // Animation wird initialisiert und bekommt per ID Ressource zugewiesen,
        // danach wird die Animation an ImageView angewandt
        Animation animation = AnimationUtils.loadAnimation(
                getApplicationContext(),R.anim.rotate
        );
        wuerfel3.startAnimation(animation);

    }

    // Methode, die entweder per Sensor oder onClick() des button4 ausgeführt wird,
    // simuliert den Wurf von 4 sechsseitigen Würfeln
    public void wuerfeln4(View view) {
        Random rand = new Random();
        String wurfergebnis = "";

        // For-Schleife, da der Switchcase je nach Würfelanzahl mehrmals hintereinander
        // ausgeführt werden muss
        for(int i = 0; i<4; i++){
            int n = rand.nextInt(6) + 1;
            switch(n){
                case 1: wurfergebnis = wurfergebnis + "1 "; break;
                case 2: wurfergebnis = wurfergebnis + "2 "; break;
                case 3: wurfergebnis = wurfergebnis + "3 "; break;
                case 4: wurfergebnis = wurfergebnis + "4 "; break;
                case 5: wurfergebnis = wurfergebnis + "5 "; break;
                case 6: wurfergebnis = wurfergebnis + "6 "; break;
                default:break;
            }
        }

        // Das zufallsgenerierte Wurfergebnis wird in TextView sichtbar gemacht
        ergebnis.setText(wurfergebnis);

        // Animation wird initialisiert und bekommt per ID Ressource zugewiesen,
        // danach wird die Animation an ImageView angewandt
        Animation animation = AnimationUtils.loadAnimation(
                getApplicationContext(),R.anim.rotate
        );
        wuerfel4.startAnimation(animation);
    }

    // Methode, die entweder per Sensor oder onClick() des button5 ausgeführt wird,
    // simuliert den Wurf von 5 sechsseitigen Würfeln
    public void wuerfeln5(View view) {
        Random rand = new Random();
        String wurfergebnis = "";

        // For-Schleife, da der Switchcase je nach Würfelanzahl mehrmals hintereinander
        // ausgeführt werden muss
        for(int i = 0; i<5; i++){
            int n = rand.nextInt(6) + 1;
            switch(n){
                case 1: wurfergebnis = wurfergebnis + "1 "; break;
                case 2: wurfergebnis = wurfergebnis + "2 "; break;
                case 3: wurfergebnis = wurfergebnis + "3 "; break;
                case 4: wurfergebnis = wurfergebnis + "4 "; break;
                case 5: wurfergebnis = wurfergebnis + "5 "; break;
                case 6: wurfergebnis = wurfergebnis + "6 "; break;
                default:break;
            }
        }

        // Das zufallsgenerierte Wurfergebnis wird in TextView sichtbar gemacht
        ergebnis.setText(wurfergebnis);

        // Animation wird initialisiert und bekommt per ID Ressource zugewiesen,
        // danach wird die Animation an ImageView angewandt
        Animation animation = AnimationUtils.loadAnimation(
                getApplicationContext(),R.anim.rotate
        );
        wuerfel5.startAnimation(animation);
    }
}
