package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class MainActivity extends AppCompatActivity {
     ExampleReceiver rec = new ExampleReceiver();

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    public final String TAG="Fragment";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        addMusicFragment();

    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter f = new IntentFilter();
        f.addAction(Intent.ACTION_BATTERY_LOW);
        f.addAction(Intent.ACTION_BATTERY_OKAY);
        f.addAction(Intent.ACTION_POWER_DISCONNECTED);
        registerReceiver(rec,f);
    }

    private void addMusicFragment(){
        fragmentTransaction=fragmentManager.beginTransaction();
        MusicFragment musicListFragment=new MusicFragment();
        fragmentTransaction.add(R.id.fragmentContainer,musicListFragment);
        fragmentTransaction.commit();
    }
//    public void startMusic(View v){
//        Intent intent = new Intent(this, MusicService.class);
//        startService(intent);
//    }
//    public void stopMusic(View v){
//        Intent intent2 = new Intent(this, MusicService.class);
//        stopService(intent2);
//    }


    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(rec);
    }
}