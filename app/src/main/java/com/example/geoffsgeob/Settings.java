package com.example.geoffsgeob;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Settings extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        TextView textView = findViewById(R.id.text_settings);
        textView.setText("meow3");
    }
}
