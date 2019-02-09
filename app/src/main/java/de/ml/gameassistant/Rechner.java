package de.ml.gameassistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Rechner extends AppCompatActivity {

    private Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, bPlus, bMinus, bMal, bGeteilt, bKomma, bGleich;
    private TextView anzeige;
    private EditText eingabe;
    private int counter;

    private double zahl1 = Double.NaN, zahl2;
    private static final char ADDITION = '+';
    private static final char SUBTRAKTION = '-';
    private static final char MULTIPLIKATION = '*';
    private static final char DIVISION = '/';
    private static final char GLEICH = '=';

    private char AKTUELLE_OPERATION;

    DecimalFormat decimalFormat = new DecimalFormat("#.##########");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rechner);

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
    }

    public void press1(View view){
        eingabe.setText(eingabe.getText() + "1");
    }

    public void press2(View view){
        eingabe.setText(eingabe.getText() + "2");
    }

    public void press3(View view){
        eingabe.setText(eingabe.getText() + "3");
    }

    public void press4(View view){
        eingabe.setText(eingabe.getText() + "4");
    }

    public void press5(View view){
        eingabe.setText(eingabe.getText() + "5");
    }

    public void press6(View view){
        eingabe.setText(eingabe.getText() + "6");
    }

    public void press7(View view){
        eingabe.setText(eingabe.getText() + "7");
    }

    public void press8(View view){
        eingabe.setText(eingabe.getText() + "8");
    }

    public void press9(View view){
        eingabe.setText(eingabe.getText() + "9");
    }

    public void press0(View view){
        eingabe.setText(eingabe.getText() + "0");
    }

    public void pressKomma(View view){
        eingabe.setText(eingabe.getText() + ".");
    }

    private void Rechne(){
        if(!Double.isNaN(zahl1) && counter == 1) {
            if(AKTUELLE_OPERATION == ADDITION) {
                zahl2 = Double.parseDouble(eingabe.getText().toString());
                zahl1 += zahl2;
            }
            else if(AKTUELLE_OPERATION == SUBTRAKTION) {
                zahl2 = Double.parseDouble(eingabe.getText().toString());
                zahl1 -= zahl2;
            }
            else if(AKTUELLE_OPERATION == MULTIPLIKATION){
                zahl2 = Double.parseDouble(eingabe.getText().toString());
                zahl1 *= zahl2;
            }
            else if(AKTUELLE_OPERATION == DIVISION) {
                zahl2 = Double.parseDouble(eingabe.getText().toString());
                zahl1 /= zahl2;
            }
        }
        else {
            try {
                zahl1 = Double.parseDouble(eingabe.getText().toString());
            }
            catch (Exception e){}
        }
        eingabe.setText(null);

    }

    public void pressPlus(View view) {
        AKTUELLE_OPERATION = ADDITION;
        Rechne();
        anzeige.setText(decimalFormat.format(zahl1));
    }


    public void pressMinus(View view) {
        AKTUELLE_OPERATION = SUBTRAKTION;
        Rechne();
        anzeige.setText(decimalFormat.format(zahl1));
    }

    public void pressMal(View view) {
        AKTUELLE_OPERATION = MULTIPLIKATION;
        Rechne();
        anzeige.setText(decimalFormat.format(zahl1));
    }

    public void pressGeteilt(View view) {
        AKTUELLE_OPERATION = DIVISION;
        Rechne();
        anzeige.setText(decimalFormat.format(zahl1));
    }

    public void pressGleich(View view) {
        Rechne();
        anzeige.setText(decimalFormat.format(zahl1));
        eingabe.setText(decimalFormat.format(zahl1));
        zahl1 = Double.NaN;
        AKTUELLE_OPERATION = '0';
    }

}
