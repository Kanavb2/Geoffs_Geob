package com.example.geoffsgeob;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
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
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        bgVolume.setMax(audioManager
                .getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        bgVolume.setProgress(audioManager
                .getStreamVolume(AudioManager.STREAM_MUSIC));
        MainActivity.setBgValue(bgVolume.getProgress() * 100 / 15);
        bgNumber.setText(MainActivity.getBgValue() + "%");

        bgVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                MainActivity.setBgValue(progress * 100 / 15);
                bgNumber.setText(MainActivity.getBgValue() + "%");
                BackgroundSoundService.changeVolume(progress);
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                        progress, 0);
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

        CheckBox disableEncounters = findViewById(R.id.disableEncounters);
        if (MainActivity.getDisableEncounters()) {
            disableEncounters.setChecked(true);
        }
        disableEncounters.setOnCheckedChangeListener(((compoundButton, b) -> {
            if (compoundButton.isChecked()) {
                MainActivity.setDisableEncounters(true);
            } else {
                MainActivity.setDisableEncounters(false);
            }
        }));

        CheckBox disableBonuses = findViewById(R.id.disableBonuses);
        if (MainActivity.getDisableBonuses()) {
            disableBonuses.setChecked(true);
        }
        disableBonuses.setOnCheckedChangeListener(((compoundButton, b) -> {
            if (compoundButton.isChecked()) {
                MainActivity.setDisableBonuses(true);
            } else {
                MainActivity.setDisableBonuses(false);
            }
        }));

    }
}
