package de.ml.gameassistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.Random;

public class StadtLandFluss extends AppCompatActivity {

    private TextView anzeige;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stadt_land_fluss);

        anzeige = findViewById(R.id.textView);
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
        //anzeige.setText(Integer.toString(n));

    }
}
