package de.ml.gameassistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class NamenLosen extends AppCompatActivity {

    EditText et1, et2, et3, et4, et5, et6, et7;
    TextView tv;

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
            tv.setText("Min. 2 Namen");
        }





    }

    public void loeschen(View view) {
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        et5.setText("");
        et6.setText("");
        et7.setText("");
    }
}
