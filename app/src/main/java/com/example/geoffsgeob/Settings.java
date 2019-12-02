package com.example.geoffsgeob;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.geoffsgeob.ui.home.HomeFragment;

public class Settings extends Activity {

    private int bgValue = 50;
    private int sfxValue = 50;
    private int storeBG;
    private int storeSFX;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final SeekBar bgVolume = findViewById(R.id.bgVolume);
        bgVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                bgValue = progress;
                TextView bgNumber = findViewById(R.id.bgNumber);
                bgNumber.setText(bgValue + "%");
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        final SeekBar sfxVolume = findViewById(R.id.sfxVolume);
        sfxVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sfxValue = progress;
                TextView sfxNumber = findViewById(R.id.sfxNumber);
                sfxNumber.setText(sfxValue + "%");
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        CheckBox muteSounds = findViewById(R.id.muteSounds);
        muteSounds.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    storeBG = bgValue;
                    storeSFX = sfxValue;
                    bgVolume.setProgress(0, true);
                    sfxVolume.setProgress(0, true);
                } else {
                    bgVolume.setProgress(storeBG, true);
                    sfxVolume.setProgress(storeSFX, true);
                }
            }
        });
        CheckBox disableProgress = findViewById(R.id.disableProgressBars);
        disableProgress.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    HomeFragment.hideProgressBars();
                } else {
                    HomeFragment.showProgressBars();
                }
            }
        });
    }
}
