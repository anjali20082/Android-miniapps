package com.example.helloworld;

import android.app.DownloadManager;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;


public class InternetFragment extends Fragment {
    MediaPlayer mediaPlayer;
    DownloadFile async ;
    DownloadManager downloadManager;
    private TextView mTextView;
    boolean result;
    Button download, playdownload, stop;
    String internetStatus = "Internet not available, can't download" ;
    Context mContext;
    View rootView;
    Bundle bundle = new Bundle();

    public InternetFragment() {
//        bundle =getArguments();
//        if(bundle!=null){
//    String value=bundle.getString("key");}
        // Required empty public constructor
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();


    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//
//    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_internet, container,false);

        mTextView = (TextView)  rootView.findViewById(R.id.text);
        download = (Button)  rootView.findViewById(R.id.button7);
        playdownload =(Button) rootView.findViewById(R.id.button8);
        stop = (Button) rootView.findViewById(R.id.button9);
//             result = isInternetAvailable();
        result = isNetworkConnected();

        if(result) {
            internetStatus = "Internet Available";
            mTextView.setText(internetStatus);
            Toast.makeText(getContext(), internetStatus, Toast.LENGTH_SHORT).show();
        }
        else
        {
            mTextView.setText(internetStatus);
            Toast.makeText(getContext(), internetStatus, Toast.LENGTH_SHORT).show();
        }




        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mTextView.getText() =="Internet Available") {

                    String url = "https://faculty.iiitd.ac.in/~mukulika/s1.mp3";
//                        Toast.makeText(getApplicationContext(), "Downloading ", Toast.LENGTH_SHORT).show();
                    async= new DownloadFile(getContext());
//                        DownloadFile async = new DownloadFile(getApplicationContext());
                    async.execute(url);

                }
                else if(mTextView.getText() =="Internet not available, can't download"){
                    Toast.makeText(getContext(), "Internet not available, can't download", Toast.LENGTH_SHORT).show();
                }

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
        return rootView;
//        return inflater.inflate(R.layout.fragment_internet, container, false);
    }

    private void playDownload() {

        try {
//                File file = new File(mContext.getFilesDir(), "mydownloadedfile.mp3");
//            Uri myUri = Uri.fromFile(file); // initialize Uri here

//                Uri myUri = Uri.fromFile(new File("/data/data/com.example.helloworld/files/mydownloadedfile.mp3") );
            Uri myUri = Uri.parse("/data/data/com.example.helloworld/files/mydownloadedfile.mp3");
            System.out.println(myUri);
            mediaPlayer = new MediaPlayer();

            mediaPlayer.setAudioAttributes(
                    new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build()
            );
            mediaPlayer.setDataSource(getContext(), myUri);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch(IOException e){
            System.out.println(e);
        }
    }
}