package com.example.helloworld;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;
import androidx.core.app.NotificationManagerCompat;
import android.app.Notification;
import android.app.PendingIntent;
import java.util.ArrayList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.app.NotificationManager;
import android.content.Context;
import android.app.TaskStackBuilder;
import android.app.NotificationChannel;
import android.graphics.Color;
import android.util.Log;

public class MusicService extends Service {
    private static final String TAG_FOREGROUND_SERVICE = "FOREGROUND_SERVICE";

    public static final String ACTION_START_FOREGROUND_SERVICE = "ACTION_START_FOREGROUND_SERVICE";

    public static final String ACTION_STOP_FOREGROUND_SERVICE = "ACTION_STOP_FOREGROUND_SERVICE";

    public static final String ACTION_PAUSE = "ACTION_PAUSE";

    public static final String ACTION_PLAY = "ACTION_PLAY";
    MediaPlayer music;
    int currentIndex=0;
    Bundle bundle;
    @Override
    public IBinder onBind(Intent intent){ return  null;}

    @Override
    public void onCreate(){
        Toast.makeText(this, "Service Created ", Toast.LENGTH_SHORT).show();
//         Uri myUri = Uri.parse("android.resource://com.example.helloworld/" + R.raw.brave);
        ArrayList<Integer> songs= new ArrayList<>();
        songs.add(0, R.raw.brave);
        songs.add(1, R.raw.comealive);
        songs.add(2, R.raw.daylight);
        songs.add(3, R.raw.pricetag);



         music = MediaPlayer.create(this, songs.get(currentIndex));
        music.setLooping(true);
    }
    @Override
    public int onStartCommand(Intent intent, int flags , int startId)
    {

        ArrayList<Integer> songs= new ArrayList<>();
        songs.add(0, R.raw.brave);
        songs.add(1, R.raw.comealive);
        songs.add(2, R.raw.daylight);
        songs.add(3, R.raw.pricetag);

      int number = intent.getIntExtra("songnumber",0);

//        music = MediaPlayer.create(this, songs.get(currentIndex));
        music.setLooping(false);

        Toast.makeText(this, "Service Started ", Toast.LENGTH_SHORT).show();
        if(number== 1){
            if(music.isPlaying()) {
                music.stop();
            }
            music = MediaPlayer.create(this, songs.get(0));
        }
        else if(number == 2)
        {
            if(music.isPlaying()) {
                music.stop();
            }
            music = MediaPlayer.create(this, songs.get(1));
        }
        else if(number == 3)
        {
            if(music.isPlaying()) {
                music.stop();
            }
            music = MediaPlayer.create(this, songs.get(2));
        }
        else if(number == 4)
        {
            if(music.isPlaying()) {
                music.stop();
            }
            music = MediaPlayer.create(this, songs.get(3));

        }

        if(music.isPlaying()) {
            music.stop();
        }
        if(music!=null) {

            music.start();
            startForegroundService();

        }
        if(intent.getAction() == "ACTION_STOP_FOREGROUND_SERVICE"){
            stopForegroundService();
        }
//        return super.onStartCommand(intent,flags,currentIndex);
        return START_STICKY;
    }

    private void startForegroundService() {
        Log.d(TAG_FOREGROUND_SERVICE, "Start foreground service.");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel("my_service", "My Background Service");
        } else {

            // Create notification default intent.
            Intent intent = new Intent();
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

            // Create notification builder.
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

            // Make notification show big text.
            NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
            bigTextStyle.setBigContentTitle("Music player implemented by foreground service.");
            bigTextStyle.bigText("Android foreground service is a android service which can run in foreground always, it can be controlled by user via notification.");
            // Set big text style.
            builder.setStyle(bigTextStyle);

            builder.setWhen(System.currentTimeMillis());
            builder.setSmallIcon(R.mipmap.ic_launcher);
            Bitmap largeIconBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background);
            builder.setLargeIcon(largeIconBitmap);
            // Make the notification max priority.
            builder.setPriority(Notification.PRIORITY_MAX);
            // Make head-up notification.
            builder.setFullScreenIntent(pendingIntent, true);

            // Add Play button intent in notification.
            Intent playIntent = new Intent(this, MusicService.class);
            playIntent.setAction(ACTION_PLAY);
            PendingIntent pendingPlayIntent = PendingIntent.getService(this, 0, playIntent, 0);
            NotificationCompat.Action playAction = new NotificationCompat.Action(android.R.drawable.ic_media_play, "Play", pendingPlayIntent);
            builder.addAction(playAction);

            // Add Pause button intent in notification.
            Intent pauseIntent = new Intent(this, MusicService.class);
            pauseIntent.setAction(ACTION_PAUSE);
            PendingIntent pendingPrevIntent = PendingIntent.getService(this, 0, pauseIntent, 0);
            NotificationCompat.Action prevAction = new NotificationCompat.Action(android.R.drawable.ic_media_pause, "Pause", pendingPrevIntent);
            builder.addAction(prevAction);

            // Build the notification.
            Notification notification = builder.build();

            // Start foreground service.
            startForeground(1, notification);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName) {
        Intent resultIntent = new Intent(this, MainActivity.class);
// Create the TaskStackBuilder and add the intent, which inflates the back stack
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntentWithParentStack(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationChannel chan = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
        chan.setLightColor(Color.BLUE);
        chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        assert manager != null;
        manager.createNotificationChannel(chan);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelId);
        Notification notification = notificationBuilder.setOngoing(true)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Musikal playing song in background")
                .setPriority(NotificationManager.IMPORTANCE_MIN)
                .setCategory(Notification.CATEGORY_SERVICE)
                .setContentIntent(resultPendingIntent) //intent
                .build();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, notificationBuilder.build());
        startForeground(1, notification);
    }


    private void stopForegroundService() {
        Log.d(TAG_FOREGROUND_SERVICE, "Stop foreground service.");

        // Stop foreground service and remove the notification.
        stopForeground(true);

        // Stop the foreground service.
        stopSelf();
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Toast.makeText(this, "Service Stopped ", Toast.LENGTH_SHORT).show();
        if(music.isPlaying()) {
            music.stop();
        }
    }

}
