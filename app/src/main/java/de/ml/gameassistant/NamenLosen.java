package de.ml.gameassistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class NamenLosen extends AppCompatActivity {

    EditText et1, et2, et3, et4;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_namen_losen);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        et4 = findViewById(R.id.et4);

        tv = findViewById(R.id.tv);


    }

    public void losen(View view) {
        String name1 = et1.getText().toString();
        String name2 = et2.getText().toString();
        String name3 = et3.getText().toString();
        String name4 = et4.getText().toString();

        Random rand = new Random();

        int n = rand.nextInt(4) + 1;


        switch (n) {
            case 1:  tv.setText(name1); break;
            case 2:  tv.setText(name2); break;
            case 3:  tv.setText(name3); break;
            case 4:  tv.setText(name4); break;
            default: break;
        }

    }
}
