package com.example.helloworld;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import java.util.ArrayList;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MusicFragment extends Fragment {
    View rootView;

    Button play,play2,play3, play4,stop,internet_connect;

    public MusicFragment() {
        // Required empty public constructor
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         rootView = inflater.inflate(R.layout.fragment_music, container,false);
       play = (Button) rootView.findViewById(R.id.button);
       play2 =(Button) rootView.findViewById(R.id.button2);
        play3 =(Button) rootView.findViewById(R.id.button3);
        play4 =(Button) rootView.findViewById(R.id.button4);
       stop = (Button) rootView.findViewById(R.id.button5);
       internet_connect =(Button) rootView.findViewById(R.id.button6);
//        Bundle bundle = new Bundle();
//  ArrayList<Integer> songs= new ArrayList<>();
//        songs.add(0, R.raw.brave);
//        songs.add(1, R.raw.comealive);
//        songs.add(2, R.raw.daylight);
//        songs.add(3, R.raw.pricetag);
//         music = MediaPlayer.create(this, songs.get(currentIndex++));
//        music.setLooping(false);
       play.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v) {
//                   if(music!=null ){
//                       music.start();}
//                   music.setLooping(false);
//           }
//               music = MediaPlayer.create(getContext(), songs.get(0));
                   Intent intent = new Intent(getContext(), MusicService.class);
               intent.putExtra("songnumber",1);
                   getContext().startService(intent);
           }
       });
        play2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                   if(music!=null ){
//                       music.start();}
//                   music.setLooping(false);
//           }
//           music = MediaPlayer.create(getContext(), songs.get(1));
                Intent intent = new Intent(getContext(), MusicService.class);
                intent.putExtra("songnumber", 2);
                getContext().startService(intent);
            }
        });
        play3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                   if(music!=null ){
//                       music.start();}
//                   music.setLooping(false);
//           }
//                music = MediaPlayer.create(getContext(), songs.get(2));
                Intent intent = new Intent(getContext(), MusicService.class);
                intent.putExtra("songnumber", 3);
                getContext().startService(intent);
            }
        });
        play4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                   if(music!=null ){
//                       music.start();}
//                   music.setLooping(false);
//           }
//                music = MediaPlayer.create(getContext(), songs.get(0));
                Intent intent = new Intent(getContext(), MusicService.class);
                intent.putExtra("songnumber", 4);
                getContext().startService(intent);
            }
        });

        stop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
//                music.stop();
                    Intent intent2 = new Intent(getContext(), MusicService.class);
                    getContext().stopService(intent2);

            }
        });

        internet_connect.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), InternetConnect.class);
                startActivity(intent);
            }
        }));
        return rootView;
//        return inflater.inflate(R.layout.fragment_music, container, false);
    }


}