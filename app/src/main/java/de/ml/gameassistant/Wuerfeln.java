package de.ml.gameassistant;

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
import java.util.Random;

public class Wuerfeln extends AppCompatActivity implements SensorEventListener {

    private TextView ergebnis;
    private ImageView wuerfel1, wuerfel2, wuerfel3, wuerfel4, wuerfel5;
    private Button button1, button2, button3, button4, button5;
    private SensorManager sm_acc;
    private Sensor acc;
    private long then2 = 0;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wuerfeln);

        sm_acc = (SensorManager) getSystemService(SENSOR_SERVICE);
        acc = sm_acc.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

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
                wechsel();
                then2 = now;
            }

            //Falls der X-Wert höher als 6 ist und mehr als 1 Sekunde vergangen ist, wird zurückgeblättert.
            if((x > 6 ) && (now - then2) > 1000) {
                counter--;
                wechsel();
                then2 = now;
            }
        }
    }

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

        switch (counter) {
            case 1: button1.setVisibility(View.VISIBLE);
                wuerfel1.setVisibility(View.VISIBLE);
                break;
            case 2: button2.setVisibility(View.VISIBLE);
                wuerfel2.setVisibility(View.VISIBLE);
                break;
            case 3: button3.setVisibility(View.VISIBLE);
                wuerfel3.setVisibility(View.VISIBLE);
                break;
            case 4: button4.setVisibility(View.VISIBLE);
                wuerfel4.setVisibility(View.VISIBLE);
                break;
            case 5: button5.setVisibility(View.VISIBLE);
                wuerfel5.setVisibility(View.VISIBLE);
                break;
            default: break;
        }
    }

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
        ergebnis.setText(wurfergebnis);

        Animation animation = AnimationUtils.loadAnimation(
                getApplicationContext(),R.anim.rotate
        );
        wuerfel1.startAnimation(animation);
    }

    public void wuerfeln2(View view){
        Random rand = new Random();
        String wurfergebnis = "";

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
        ergebnis.setText(wurfergebnis);

        Animation animation = AnimationUtils.loadAnimation(
                getApplicationContext(),R.anim.rotate
        );
        wuerfel2.startAnimation(animation);
    }

    public void wuerfeln3(View view) {
        Random rand = new Random();
        String wurfergebnis = "";

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
        ergebnis.setText(wurfergebnis);

        Animation animation = AnimationUtils.loadAnimation(
                getApplicationContext(),R.anim.rotate
        );
        wuerfel3.startAnimation(animation);

    }

    public void wuerfeln4(View view) {
        Random rand = new Random();
        String wurfergebnis = "";

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
        ergebnis.setText(wurfergebnis);

        Animation animation = AnimationUtils.loadAnimation(
                getApplicationContext(),R.anim.rotate
        );
        wuerfel4.startAnimation(animation);
    }

    public void wuerfeln5(View view) {
        Random rand = new Random();
        String wurfergebnis = "";

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
        ergebnis.setText(wurfergebnis);

        Animation animation = AnimationUtils.loadAnimation(
                getApplicationContext(),R.anim.rotate
        );
        wuerfel5.startAnimation(animation);

    }

    /*public void wuerfeln16(View view){
        Random rand = new Random();
        String wurfergebnis = "";
        int n = rand.nextInt(6) + 1;
        switch (n) {
            case 1: wurfergebnis = wurfergebnis + "1 ";break;
            case 2: wurfergebnis = wurfergebnis + "2 ";break;
            case 3: wurfergebnis = wurfergebnis + "3 ";break;
            case 4: wurfergebnis = wurfergebnis + "4 ";break;
            case 5: wurfergebnis = wurfergebnis + "5 ";break;
            case 6: wurfergebnis = wurfergebnis + "6 ";break;
            case 7: wurfergebnis = wurfergebnis + "7 ";break;
            case 8: wurfergebnis = wurfergebnis + "8 ";break;
            case 9: wurfergebnis = wurfergebnis + "9 ";break;
            case 10: wurfergebnis = wurfergebnis + "10 ";break;
            case 11: wurfergebnis = wurfergebnis + "11 ";break;
            case 12: wurfergebnis = wurfergebnis + "12 ";break;
            case 13: wurfergebnis = wurfergebnis + "13 ";break;
            case 14: wurfergebnis = wurfergebnis + "14 ";break;
            case 15: wurfergebnis = wurfergebnis + "15 ";break;
            case 16: wurfergebnis = wurfergebnis + "16 ";break;
            default: break;
        }
        ergebnis.setText(wurfergebnis);

        Animation animation = AnimationUtils.loadAnimation(
                getApplicationContext(),R.anim.rotate
        );
        wuerfel.startAnimation(animation);
    }*/

}
