package com.example.geoffsgeob;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Help extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        TextView textView = findViewById(R.id.text_help);
        textView.setText("meow2");
    }
}
