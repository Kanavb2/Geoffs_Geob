package com.example.geoffsgeob;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geoffsgeob.ui.home.HomeFragment;

public class Bonuses extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonuses);

        Button chuchu = findViewById(R.id.chuchu);
        Button xyz = findViewById(R.id.xyz);
        Button ben = findViewById(R.id.benNordick);
        Button challen = findViewById(R.id.geoffreyChallen);

        TextView chuchuText = findViewById(R.id.chuchuText);
        TextView xyzText = findViewById(R.id.xyzText);
        TextView benText = findViewById(R.id.benNordickText);
        TextView challenText = findViewById(R.id.geoffreyText);

        if (MainActivity.getChuchu()) {
            chuchu.setVisibility(View.GONE);
            xyz.setVisibility(View.GONE);
            chuchuText.setVisibility(View.GONE);
            xyzText.setVisibility(View.GONE);
        }
        if (MainActivity.getXyz()) {
            chuchu.setVisibility(View.GONE);
            xyz.setVisibility(View.GONE);
            chuchuText.setVisibility(View.GONE);
            xyzText.setVisibility(View.GONE);
        }
        if (MainActivity.getBen()) {
            ben.setVisibility(View.GONE);
            benText.setVisibility(View.GONE);
        }
        if (MainActivity.getChallen()) {
            challen.setVisibility(View.GONE);
            challenText.setVisibility(View.GONE);
        }
        chuchu.setOnClickListener(view -> {
            HomeFragment.progressBar(0, 20);
            chuchu.setVisibility(View.GONE);
            xyz.setVisibility(View.GONE);
            chuchuText.setVisibility(View.GONE);
            xyzText.setVisibility(View.GONE);
            MainActivity.setChuchu(true);
            MainActivity.setXyz(true);
            Toast.makeText(Bonuses.this,
                    "You brought Chuchu to lecture this week!", Toast.LENGTH_SHORT).show();
        });
        xyz.setOnClickListener(view -> {
            HomeFragment.progressBar(0, 20);
            chuchu.setVisibility(View.GONE);
            xyz.setVisibility(View.GONE);
            chuchuText.setVisibility(View.GONE);
            xyzText.setVisibility(View.GONE);
            MainActivity.setChuchu(true);
            MainActivity.setXyz(true);
            Toast.makeText(Bonuses.this,
                    "You brought Xyz to lecture this week!", Toast.LENGTH_SHORT).show();
        });
        ben.setOnClickListener(view -> {
            HomeFragment.progressBar(10, 10);
            ben.setVisibility(View.GONE);
            benText.setVisibility(View.GONE);
            MainActivity.setBen(true);
            Toast.makeText(Bonuses.this,
                    "Ben did his magic!", Toast.LENGTH_SHORT).show();
        });
        challen.setOnClickListener(view -> {
            HomeFragment.progressBar(20, 0);
            challen.setVisibility(View.GONE);
            challenText.setVisibility(View.GONE);
            MainActivity.setChallen(true);
            Toast.makeText(Bonuses.this,
                    "The real Geoff fixed things up for you!", Toast.LENGTH_SHORT).show();
        });
    }
}
