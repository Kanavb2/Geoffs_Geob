package com.example.geoffsgeob;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;


public class BackgroundSoundService extends Service {
    static MediaPlayer player;
    public IBinder onBind(Intent arg0) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();


        player = MediaPlayer.create(this, R.raw.videoplayback);
        player.setLooping(true); // Set looping
        player.setVolume(100,100);

    }
    public int onStartCommand(Intent intent, int flags, int startId) {


        player.start();

        return START_STICKY;
    }
    public static void changeVolume(int volume) {
        player.setVolume(volume * 2, volume * 2);
    }
    public static void startPlayer() {
        if (!player.isPlaying()) {
            player.start();
        }
    }

    @Override
    public void onDestroy() {

        player.stop();
        player.release();
    }
}
