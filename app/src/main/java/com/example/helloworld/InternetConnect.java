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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import android.media.AudioManager;

public class InternetConnect extends AppCompatActivity{
        MediaPlayer mediaPlayer;
        DownloadFile async ;
        DownloadManager downloadManager;
        private TextView mTextView;
        boolean result;
        Button download, playdownload, stop;
        String internetStatus = "Internet not available, can't download" ;
        Context mContext;

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();

//        public void myClickHandler(View view) {
//
//            ConnectivityManager connMgr = (ConnectivityManager)
//                    getSystemService(Context.CONNECTIVITY_SERVICE);
//            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
//            if (networkInfo != null && networkInfo.isConnected()) {
//// fetch data
//            } else {
//// display error
//            }
//        }
    }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_internet);
            mTextView = (TextView) findViewById(R.id.text);
            download = (Button) findViewById(R.id.button7);
            playdownload =(Button)findViewById(R.id.button8);
            stop = (Button)findViewById(R.id.button9);
//             result = isInternetAvailable();
            result = isNetworkConnected();

            if(result) {
                internetStatus = "Internet Available";
                mTextView.setText(internetStatus);
                Toast.makeText(getApplicationContext(), internetStatus, Toast.LENGTH_SHORT).show();
            }
            else
            {
                mTextView.setText(internetStatus);
                Toast.makeText(getApplicationContext(), internetStatus, Toast.LENGTH_SHORT).show();
            }
            download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(mTextView.getText() =="Internet Available") {

                        String url = "https://faculty.iiitd.ac.in/~mukulika/s1.mp3";
//                        Toast.makeText(getApplicationContext(), "Downloading ", Toast.LENGTH_SHORT).show();
                        async= new DownloadFile(getApplicationContext());
//                        DownloadFile async = new DownloadFile(getApplicationContext());
                        async.execute(url);

//
//                        downloadManager=(DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
//                        Uri uri=Uri.parse("http://faculty.iiitd.ac.in/~mukulika/s1.mp3");
//                        DownloadManager.Request request = new DownloadManager.Request(uri);
//                        try{
//                            request.setDestinationInExternalPublicDir(getFilesDir()+"/DownloadTestFolder","/");
//                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//                            Long reference = downloadManager.enqueue(request);
//
//                        }catch (Exception e){
////            Error
//                    }

                    }
                    else if(mTextView.getText() =="Internet not available, can't download"){
                        Toast.makeText(getApplicationContext(), "Internet not available, can't download", Toast.LENGTH_SHORT).show();
                    }
//                    ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
//                    File directory = contextWrapper.getDir(getFilesDir().getName(), Context.MODE_PRIVATE);
//                    File file =  new File(directory,"abc");

//                    FileOutputStream fos = new FileOutputStream("d.mp3", true); // save

//                    String FILENAME = "downloaded_file.mp3";
////                    ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
////                    File directory = contextWrapper.getDir(getFilesDir().getName(), Context.MODE_PRIVATE);
////                    File file =  new File(directory,FILENAME);

//                    File dir = new File(mContext.getFilesDir(), "MyDownloadedSongs");
//                    if(!dir.exists()){
//                        dir.mkdir();
//                    }
//                    File destination = new File(dir, FILENAME);
//                    try {
//                        destination.createNewFile();
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }

//                    new DownloadFile(getApplicationContext()).execute(url);
                }
            });

            playdownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    playDownload();
                }

            });
            stop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mediaPlayer.stop();
                }

            });
        }

        private void startDownload(){

        String url = "https://faculty.iiitd.ac.in/~mukulika/s1.mp3";
            DownloadManager.Request req = new DownloadManager.Request(Uri.parse(url));
            req.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
            req.setTitle("Download");
            req.setDescription("Downloading file.....");
            req.allowScanningByMediaScanner();
            req.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            req.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, ""+System.currentTimeMillis());
            DownloadManager manager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
            manager.enqueue(req);

        }
        private void playDownload() {

            try {

//            Uri myUri = Uri.fromFile(getFilesDir()) + "/mydownloadedfile.mp3"; // initialize Uri here
                Uri myUri = Uri.parse("/data/data/com.example.helloworld/files/mydownloadedfile.mp3");
                System.out.println(myUri);
                    mediaPlayer = new MediaPlayer();

                    mediaPlayer.setAudioAttributes(
                            new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                    .setUsage(AudioAttributes.USAGE_MEDIA)
                                    .build()
                    );
                    mediaPlayer.setDataSource(getApplicationContext(), myUri);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch(IOException e){
                System.out.println(e);
                }
            }
        }




