package de.ml.gameassistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class Wuerfeln extends AppCompatActivity {

    private TextView ergebnis;
    private ImageView wuerfel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wuerfeln);

        ergebnis = findViewById(R.id.ergebnisview);
        wuerfel = findViewById(R.id.wuerfel);
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
        wuerfel.startAnimation(animation);
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
        wuerfel.startAnimation(animation);
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
        wuerfel.startAnimation(animation);

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
        wuerfel.startAnimation(animation);
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
        wuerfel.startAnimation(animation);

    }

    public void wuerfeln16(View view){
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
    }

}
