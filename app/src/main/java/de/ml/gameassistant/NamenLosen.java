package de.ml.gameassistant;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.Toast;

import java.util.Random;

public class NamenLosen extends AppCompatActivity implements SensorEventListener{

    EditText et1, et2, et3, et4, et5, et6, et7;
    TextView tv;
    private SensorManager sm_prox;
    private Sensor prox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_namen_losen);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        et4 = findViewById(R.id.et4);
        et5 = findViewById(R.id.et5);
        et6 = findViewById(R.id.et6);
        et7 = findViewById(R.id.et7);

        tv = findViewById(R.id.tv);
        tv.setText("---");

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
                loeschen();
            }


        }

    }

    public void losen(View view) {

        String name1 = et1.getText().toString();
        String name2 = et2.getText().toString();
        String name3 = et3.getText().toString();
        String name4 = et4.getText().toString();
        String name5 = et5.getText().toString();
        String name6 = et6.getText().toString();
        String name7 = et7.getText().toString();

        String e = "";
        int counter = 8;

        if (name1.equals(e)) {counter--;}
        if (name2.equals(e)) {counter--;}
        if (name3.equals(e)) {counter--;}
        if (name4.equals(e)) {counter--;}
        if (name4.equals(e)) {counter--;}
        if (name5.equals(e)) {counter--;}
        if (name6.equals(e)) {counter--;}
        if (name7.equals(e)) {counter--;}

        if (counter > 1) {
            Random rand = new Random();
            int n = rand.nextInt(counter) + 1;

            switch (n) {
                case 1:  tv.setText(name1); break;
                case 2:  tv.setText(name2); break;
                case 3:  tv.setText(name3); break;
                case 4:  tv.setText(name4); break;
                case 5:  tv.setText(name5); break;
                case 6:  tv.setText(name6); break;
                case 7:  tv.setText(name7); break;
                default: break;
            }
        } else {
            Context context = getApplicationContext();
            CharSequence text = "Bitte mindestens zwei Namen eingeben";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            //tv.setText("Min. 2 Namen");
        }





    }

    public void loeschen() {
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        et5.setText("");
        et6.setText("");
        et7.setText("");

        tv.setText("---");

        Context context = getApplicationContext();
        CharSequence text = "Namen wurden gelöscht";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
