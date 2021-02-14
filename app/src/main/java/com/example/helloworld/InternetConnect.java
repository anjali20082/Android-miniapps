package com.example.helloworld;
import android.app.DownloadManager;
import android.content.ContextWrapper;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import android.media.AudioManager;

public class InternetConnect extends AppCompatActivity{

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    ExampleReceiver rec = new ExampleReceiver();
//        MediaPlayer mediaPlayer;
//        DownloadFile async ;
//        DownloadManager downloadManager;
//        private TextView mTextView;
//        boolean result;
//        Button download, playdownload, stop;
//        String internetStatus = "Internet not available, can't download" ;
//        Context mContext;
//
//        private boolean isNetworkConnected() {
//            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//
//            return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
//
//
//    }

        private void addInternetFragment(){
            fragmentTransaction=fragmentManager.beginTransaction();
            InternetFragment inetFragment= new InternetFragment();
            fragmentTransaction.add(R.id.fragmentContainer2,inetFragment);
//            fragmentTransaction.replace(R.id.fragmentContainer2,inetFragment);
            fragmentTransaction.commit();


        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_internet);

            fragmentManager = getSupportFragmentManager();
            addInternetFragment();
//
//            String value=getIntent().getStringExtra("key");
//            Bundle bundle=new Bundle();
//            bundle.putString("key",value);
//            InternetFragment ob=new InternetFragment();
//            ob.setArguments(bundle);
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer2,ob).addToBackStack(null).commit();


//            mTextView = (TextView) findViewById(R.id.text);
//            download = (Button) findViewById(R.id.button7);
//            playdownload =(Button)findViewById(R.id.button8);
//            stop = (Button)findViewById(R.id.button9);
////             result = isInternetAvailable();
//            result = isNetworkConnected();
//
//            if(result) {
//                internetStatus = "Internet Available";
//                mTextView.setText(internetStatus);
//                Toast.makeText(getApplicationContext(), internetStatus, Toast.LENGTH_SHORT).show();
//            }
//            else
//            {
//                mTextView.setText(internetStatus);
//                Toast.makeText(getApplicationContext(), internetStatus, Toast.LENGTH_SHORT).show();
//            }
//            download.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    if(mTextView.getText() =="Internet Available") {
//
//                        String url = "https://faculty.iiitd.ac.in/~mukulika/s1.mp3";
////                        Toast.makeText(getApplicationContext(), "Downloading ", Toast.LENGTH_SHORT).show();
//                        async= new DownloadFile(getApplicationContext());
////                        DownloadFile async = new DownloadFile(getApplicationContext());
//                        async.execute(url);
//
//                    }
//                    else if(mTextView.getText() =="Internet not available, can't download"){
//                        Toast.makeText(getApplicationContext(), "Internet not available, can't download", Toast.LENGTH_SHORT).show();
//                    }
//
//                }
//            });
//
//            playdownload.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    playDownload();
//                }
//
//            });
//            stop.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    mediaPlayer.stop();
//                }
//
//            });
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
    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(rec);
    }

//        private void playDownload() {
//
//            try {
////                File file = new File(mContext.getFilesDir(), "mydownloadedfile.mp3");
////            Uri myUri = Uri.fromFile(file); // initialize Uri here
//
////                Uri myUri = Uri.fromFile(new File("/data/data/com.example.helloworld/files/mydownloadedfile.mp3") );
//                Uri myUri = Uri.parse("/data/data/com.example.helloworld/files/mydownloadedfile.mp3");
//                System.out.println(myUri);
//                    mediaPlayer = new MediaPlayer();
//
//                    mediaPlayer.setAudioAttributes(
//                            new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
//                                    .setUsage(AudioAttributes.USAGE_MEDIA)
//                                    .build()
//                    );
//                    mediaPlayer.setDataSource(getApplicationContext(), myUri);
//                    mediaPlayer.prepare();
//                    mediaPlayer.start();
//                } catch(IOException e){
//                System.out.println(e);
//                }
//            }





        }




