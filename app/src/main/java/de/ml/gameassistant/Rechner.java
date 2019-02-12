package de.ml.gameassistant;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class Rechner extends AppCompatActivity implements SensorEventListener {

    // Tasten des Rechners
    private Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, bPlus, bMinus, bMal, bGeteilt, bKomma, bGleich;

    // Anzeige und Eingabefeld des Rechners, auf der Anzeige wird die letzte
    // Eingabe bzw Zwischenergebnis und Endergebnis angezeigt,
    // auf dem Eingabefeld wird nach tippen einer Taste diese dort angezeigt,
    // außerdem wird sie bei einem '=' als Zwischensepicher verwendet
    private TextView anzeige;
    private EditText eingabe;

    // Zahlen mit denen gerechnet wird, zahl1 fungiert auch gleichzeitig als Zwischenergebnis
    // zahl1 = Double.NaN wird verwendet, um am Anfang die erste Eingabe direkt zahl1 zuzuweisen
    // bzw. nach einem '=' wieder das letzte Ergebnis
    private double zahl1 = Double.NaN, zahl2;

    // Durch die char-Konstaten wird später abgeglichen, welche Rechenoperation durchgeführt werden soll
    private static final char ADDITION = '+';
    private static final char SUBTRAKTION = '-';
    private static final char MULTIPLIKATION = '*';
    private static final char DIVISION = '/';

    // Durch den Bool eingabeZ wird verhindert, das Eingaben getätigt werden, die zu dem aktuellen Zeitpunkt
    // nicht sinnvoll sind (z.B. Rechner erwartet nächsten Operator, jedoch wird eine Zahl eingetippt)
    private Boolean eingabeZ = true;

    // AKTUELLE_OPERATION bekommt je nach Tastendruck die entsprechenede Rechenoperation zugewiesen
    // und wird bei '=' zurückgesetzt
    private char AKTUELLE_OPERATION;

    // Für den reset des Rechners benötigt
    private SensorManager sm_prox;
    private Sensor prox;

    //zur Festlegung der Formatierung der Anzeigezahl
    DecimalFormat decimalFormat = new DecimalFormat("#.##########");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rechner);

        // hierdurch wird darauf hingewiesen, dass man per Entfernunssensor den Rechner
        // zurücksetzen kann
        Context context = getApplicationContext();
        CharSequence text = "Für das Zurücksetzen die Hand über den Entfernungssensor halten.";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        // Zuweisen der Ressourcen IDs
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);
        b5 = findViewById(R.id.b5);
        b6 = findViewById(R.id.b6);
        b7 = findViewById(R.id.b7);
        b8 = findViewById(R.id.b8);
        b9 = findViewById(R.id.b9);
        b0 = findViewById(R.id.b0);
        bPlus = findViewById(R.id.bPlus);
        bMinus = findViewById(R.id.bMinus);
        bMal = findViewById(R.id.bMal);
        bGeteilt = findViewById(R.id.bGeteilt);
        bKomma = findViewById(R.id.bKomma);
        bGleich = findViewById(R.id.bGleich);
        anzeige = findViewById(R.id.anzeige);
        eingabe = findViewById(R.id.eingabe);
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

    public void onSensorChanged(SensorEvent event) {
        //Sensor s erhält die Sensordaten als Array-List
        Sensor s = event.sensor;


        float dist = event.values[0];
        if (dist == 0.0) {
                reset();
        }
    }

    // Einzelne Methoden, die bei Klick der Zahlen '0-9' & '.' ausgeführt werden
    // und diese im Eingabefeld anzeigen

    public void press1(View view){
        if(eingabeZ) {
            eingabe.setText(eingabe.getText() + "1");
        }

    }
    public void press2(View view){
        if(eingabeZ){
            eingabe.setText(eingabe.getText() + "2");
        }

    }
    public void press3(View view){
        if(eingabeZ){
            eingabe.setText(eingabe.getText() + "3");
        }
    }
    public void press4(View view){
        if(eingabeZ){
            eingabe.setText(eingabe.getText() + "4");
        }
    }
    public void press5(View view){
        if(eingabeZ){
            eingabe.setText(eingabe.getText() + "5");
        }
    }
    public void press6(View view){
        if(eingabeZ){
            eingabe.setText(eingabe.getText() + "6");
        }
    }
    public void press7(View view){
        if(eingabeZ){
            eingabe.setText(eingabe.getText() + "7");
        }
    }
    public void press8(View view){
        if(eingabeZ){
            eingabe.setText(eingabe.getText() + "8");
        }
    }
    public void press9(View view){
        if(eingabeZ)
        eingabe.setText(eingabe.getText() + "9");
    }
    public void press0(View view){
        if(eingabeZ){
            eingabe.setText(eingabe.getText() + "0");
        }

    }
    public void pressKomma(View view){
        if(eingabeZ){
            eingabe.setText(eingabe.getText() + ".");
        }
    }

    // in der Rechne() Methode wird entweder zahl1 zugewiesen und keine Rechenoperation ausgeführt
    // oder mittels Abgleich von AKTUELLE_OPERATION die gewünschte Rechenperation ausgeführt, zudem
    // wird das Eingabefeld gelöscht für den nächsten Techenschritt
    // Zudem muss durch setEnable() dafür gesorgt werden, das die Operatoren Buttons nicht mehrmals gedrückt
    // werden und somit zu Problemen führen, bei einmaligem Tippen des Operators wird dieser solange gesperrt,
    // bis die nächste Zahl eingetippt und ereneut ein Operator getätigt wird
    private void Rechne(){
        if(!Double.isNaN(zahl1)) {
            if(AKTUELLE_OPERATION == ADDITION) {
                zahl2 = Double.parseDouble(eingabe.getText().toString());
                zahl1 += zahl2;
                bPlus.setEnabled(true);
                bMinus.setEnabled(true);
                bMal.setEnabled(true);
                bGeteilt.setEnabled(true);
            }
            else if(AKTUELLE_OPERATION == SUBTRAKTION) {
                zahl2 = Double.parseDouble(eingabe.getText().toString());
                zahl1 -= zahl2;
                bPlus.setEnabled(true);
                bMinus.setEnabled(true);
                bMal.setEnabled(true);
                bGeteilt.setEnabled(true);
            }
            else if(AKTUELLE_OPERATION == MULTIPLIKATION){
                zahl2 = Double.parseDouble(eingabe.getText().toString());
                zahl1 *= zahl2;
                bPlus.setEnabled(true);
                bMinus.setEnabled(true);
                bMal.setEnabled(true);
                bGeteilt.setEnabled(true);
            }
            else if(AKTUELLE_OPERATION == DIVISION) {
                zahl2 = Double.parseDouble(eingabe.getText().toString());
                zahl1 /= zahl2;
                bPlus.setEnabled(true);
                bMinus.setEnabled(true);
                bMal.setEnabled(true);
                bGeteilt.setEnabled(true);
            }
        }
        else {
            try {
                zahl1 = Double.parseDouble(eingabe.getText().toString());
                eingabeZ = true;
            }
            catch (Exception e){}
        }
        eingabe.setText(null);
    }

    // Methoden der einzelnen Operatoren, AKTUELLE_OPERATION bekommt je nach Operator Konstante
    // zugewiesen, die bei dem '=' zurückgesetzt wird
    // es wird jeweils die Methode Rechne() ausgeführt, bei der entweder zahl1 zugewiesen wird
    // und keine Rechenoperation ausgeführt wird oder mittels Abgleich AKTUELLE_OPERATION
    // die gewünschte Rechenperation ausgeführt
    public void pressPlus(View view) {
        AKTUELLE_OPERATION = ADDITION;
        Rechne();
        anzeige.setText(decimalFormat.format(zahl1));
        bPlus.setEnabled(false);
        bMinus.setEnabled(false);
        bMal.setEnabled(false);
        bGeteilt.setEnabled(false);
    }

    public void pressMinus(View view) {
        AKTUELLE_OPERATION = SUBTRAKTION;
        Rechne();
        anzeige.setText(decimalFormat.format(zahl1));
        bPlus.setEnabled(false);
        bMinus.setEnabled(false);
        bMal.setEnabled(false);
        bGeteilt.setEnabled(false);
    }

    public void pressMal(View view) {
        AKTUELLE_OPERATION = MULTIPLIKATION;
        Rechne();
        anzeige.setText(decimalFormat.format(zahl1));
        bPlus.setEnabled(false);
        bMinus.setEnabled(false);
        bMal.setEnabled(false);
        bGeteilt.setEnabled(false);
    }

    public void pressGeteilt(View view) {
        AKTUELLE_OPERATION = DIVISION;
        Rechne();
        anzeige.setText(decimalFormat.format(zahl1));
        bPlus.setEnabled(false);
        bMinus.setEnabled(false);
        bMal.setEnabled(false);
        bGeteilt.setEnabled(false);
    }

    public void pressGleich(View view) {
        Rechne();
        anzeige.setText(decimalFormat.format(zahl1));

        // Ergebnis wird in Eingabe Zwischengespeichert
        eingabe.setText(decimalFormat.format(zahl1));

        // zahl1 muss zurückgesetzt werden, damit das Zwischenergebnis ereneut zahl1
        // im nächsten Rechenschritt zugeordnet werden kann
        zahl1 = Double.NaN;
        AKTUELLE_OPERATION = '0';
        eingabeZ = false;
    }

    public void reset(){
        zahl1 = Double.NaN;
        anzeige.setText(null);
        eingabe.setText(null);
    }

}
