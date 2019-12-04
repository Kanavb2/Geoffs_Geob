package com.example.geoffsgeob;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;


public class Settings extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        TextView bgNumber = findViewById(R.id.bgNumber);
        TextView sfxNumber = findViewById(R.id.sfxNumber);

        final SeekBar bgVolume = findViewById(R.id.bgVolume);
        bgVolume.setProgress(MainActivity.getBgValue());
        bgVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                MainActivity.setBgValue(progress);
                bgNumber.setText(MainActivity.getBgValue() + "%");
                BackgroundSoundService.changeVolume(progress);
                System.out.println(progress);
                BackgroundSoundService.startPlayer();
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        bgNumber.setText(MainActivity.getBgValue() + "%");
        final SeekBar sfxVolume = findViewById(R.id.sfxVolume);
        sfxVolume.setProgress(MainActivity.getSfxValue());
        sfxVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                MainActivity.setSfxValue(progress);
                sfxNumber.setText(MainActivity.getSfxValue() + "%");
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sfxNumber.setText(MainActivity.getSfxValue() + "%");
        CheckBox muteSounds = findViewById(R.id.muteSounds);
        if (MainActivity.getMuteSounds()) {
            muteSounds.setChecked(true);
        }
        muteSounds.setOnCheckedChangeListener((compoundButton, b) -> {
            if (compoundButton.isChecked()) {
                MainActivity.setStoreBG(MainActivity.getBgValue());
                MainActivity.setStoreSFX(MainActivity.getSfxValue());
                MainActivity.setMuteSounds(true);
                bgVolume.setProgress(0, true);
                sfxVolume.setProgress(0, true);
            } else {
                MainActivity.setBgValue(MainActivity.getStoreBG());
                MainActivity.setSfxValue(MainActivity.getStoreSFX());
                MainActivity.setMuteSounds(false);
                bgVolume.setProgress(MainActivity.getBgValue(), true);
                sfxVolume.setProgress(MainActivity.getSfxValue(), true);
                BackgroundSoundService.startPlayer();
                BackgroundSoundService.changeVolume(MainActivity.getBgValue());
            }
        });
        CheckBox disableProgress = findViewById(R.id.disableProgressBars);
        if (MainActivity.getDisableProgress()) {
            disableProgress.setChecked(true);
        }
        disableProgress.setOnCheckedChangeListener((compoundButton, b) -> {
            if (compoundButton.isChecked()) {
                MainActivity.setDisableProgress(true);
            } else {
                MainActivity.setDisableProgress(false);
            }
        });
    }
}
