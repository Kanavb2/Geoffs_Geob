package com.example.geoffsgeob;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;


public class BackgroundSoundService extends Service {
    static MediaPlayer player;
    static MediaPlayer nyan;

    public IBinder onBind(Intent arg0) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(this, R.raw.bitwin);
        player.setLooping(true); // Set looping
        player.setVolume(100,100);
        nyan = MediaPlayer.create(this, R.raw.nyancat);
        nyan.setVolume(100, 100);
        nyan.setLooping(true);


    }
    public int onStartCommand(Intent intent, int flags, int startId) {

        player.start();

        return START_STICKY;
    }

    public static void changeMusic() {
        if (MainActivity.getCat()) {
            player.pause();
            nyan.start();
        } else {
            nyan.pause();
            player.start();
        }
    }

    public static void changeVolume(int volume) {
        if (player.isPlaying()) {
            player.setVolume(volume, volume);
        } else {
            nyan.setVolume(volume, volume);
        }

    }

    public void onStart(Intent intent, int startId) {

    }

    public IBinder onUnBind(Intent arg0) {

        return null;
    }

    public void onStop() {

    }
    public void onPause() {

    }
    @Override
    public void onDestroy() {

        player.stop();
        player.release();
    }

    @Override
    public void onLowMemory() {

    }
}
