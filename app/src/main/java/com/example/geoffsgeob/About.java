package com.example.geoffsgeob;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class About extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        TextView textView = findViewById(R.id.text_about);
        textView.setText("meow");
    }
}
