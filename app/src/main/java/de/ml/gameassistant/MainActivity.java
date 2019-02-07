package de.ml.gameassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
//hello
    //und so
    //hallllllllo
    //nochmalhallo

    private ImageButton button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.imageButton);
    }

    public void wuerfeln(){
        Intent intent = new Intent(this, Wuerfeln.class);
        startActivity(intent);
    }
}
