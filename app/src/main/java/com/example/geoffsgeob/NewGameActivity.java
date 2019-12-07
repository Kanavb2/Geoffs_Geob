package com.example.geoffsgeob;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.net.Inet4Address;

public class NewGameActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        Button fiveWeeks = findViewById(R.id.fiveWeeks);
        Button tenWeeks = findViewById(R.id.tenWeeks);
        Button fifteenWeeks = findViewById(R.id.fifteenWeeks);

        fiveWeeks.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            MainActivity.setTotalWeeks(5);
            finish();
        });

        tenWeeks.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            MainActivity.setTotalWeeks(10);
            finish();
        });

        fifteenWeeks.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            MainActivity.setTotalWeeks(15);
            finish();
        });

        Button about = findViewById(R.id.about);

        about.setOnClickListener(view -> {
            Intent intent = new Intent(this, About.class);
            startActivity(intent);
        });
    }
}
