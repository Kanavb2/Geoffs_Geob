package com.example.geoffsgeob;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geoffsgeob.ui.home.HomeFragment;

import java.util.Random;

public class Bonuses extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonuses);

        Button chuchu = findViewById(R.id.chuchu);
        Button ben = findViewById(R.id.benNordick);
        Button challen = findViewById(R.id.geoffreyChallen);

        TextView chuchuText = findViewById(R.id.chuchuText);
        TextView benText = findViewById(R.id.benNordickText);
        TextView challenText = findViewById(R.id.geoffreyText);

        if (MainActivity.getChuchu()) {
            chuchu.setVisibility(View.GONE);
            chuchuText.setVisibility(View.GONE);
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
            Random random = new Random();
            int studRand = random.nextInt(20) + 1;
            if (studRand < 10) {
                studRand += 10;
            }
            HomeFragment.progressBar(0, studRand);
            chuchu.setVisibility(View.GONE);
            chuchuText.setVisibility(View.GONE);
            MainActivity.setChuchu(true);
            Toast.makeText(Bonuses.this,
                    "You brought Chuchu to lecture this week!" + " \n+ " + studRand + " Student Satisfaction", Toast.LENGTH_LONG).show();
        });
        ben.setOnClickListener(view -> {
            Random random = new Random();
            int uniRand = random.nextInt(10) + 1;
            int studRand = random.nextInt(10) + 1;
            if (uniRand < 5) {
                uniRand += 5;
            }
            if (studRand < 5) {
                studRand += 5;
            }
            HomeFragment.progressBar(uniRand, studRand);
            ben.setVisibility(View.GONE);
            benText.setVisibility(View.GONE);
            MainActivity.setBen(true);
            Toast.makeText(Bonuses.this,
                    "Ben did his magic!" + " \n+ " + uniRand + " University Satisfaction" + " \n+ " + studRand + " Student Satisfaction" , Toast.LENGTH_LONG).show();
        });
        challen.setOnClickListener(view -> {
            Random random = new Random();
            int uniRand = random.nextInt(20) + 1;
            if (uniRand < 10) {
                uniRand += 10;
            }
            HomeFragment.progressBar(uniRand, 0);
            challen.setVisibility(View.GONE);
            challenText.setVisibility(View.GONE);
            MainActivity.setChallen(true);
            Toast.makeText(Bonuses.this,
                    "The real Geoff fixed things up for you!" + " \n+ " + uniRand + " University Satisfaction", Toast.LENGTH_LONG).show();
        });
    }
}
