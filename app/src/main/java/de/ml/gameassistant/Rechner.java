package de.ml.gameassistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DecimalFormat;

public class Rechner extends AppCompatActivity {

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

    // AKTUELLE_OPERATION bekommt je nach Tastendruck die entsprechenede Rechenoperation zugewiesen
    // und wird bei '=' zurückgesetzt
    private char AKTUELLE_OPERATION;

    //zur Festlegung der Formatierung der Anzeigezahl
    DecimalFormat decimalFormat = new DecimalFormat("#.##########");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rechner);

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
    }

    // Einzelne Methoden, die bei Klick der Zahlen '0-9' & '.' ausgeführt werden
    // und diese im Eingabefeld anzeigen
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

    // in der Rechne() Methode wird entweder zahl1 zugewiesen und keine Rechenoperation ausgeführt
    // oder mittels Abgleich von AKTUELLE_OPERATION die gewünschte Rechenperation ausgeführt, zudem
    // wird das Eingabefeld gelöscht für den nächsten Techenschritt
    private void Rechne(){
        if(!Double.isNaN(zahl1)) {
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

    // Methoden der einzelnen Operatoren, AKTUELLE_OPERATION bekommt je nach Operator Konstante
    // zugewiesen, die bei dem '=' zurückgesetzt wird
    // es wird jeweils die Methode Rechne() ausgeführt, bei der entweder zahl1 zugewiesen wird
    // und keine Rechenoperation ausgeführt wird oder mittels Abgleich AKTUELLE_OPERATION
    // die gewünschte Rechenperation ausgeführt
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

        // Ergebnis wird in Eingabe Zwischengespeichert
        eingabe.setText(decimalFormat.format(zahl1));

        // zahl1 muss zurückgesetzt werden, damit das Zwischenergebnis ereneut zahl1
        // im nächsten Rechenschritt zugeordnet werden kann
        zahl1 = Double.NaN;
        AKTUELLE_OPERATION = '0';
    }

}
