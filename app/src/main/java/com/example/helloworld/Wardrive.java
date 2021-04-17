package com.example.helloworld;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class Wardrive extends AppCompatActivity {
    private AppDatabase myappdatabase;
    EditText locationtxt;
    Button savebtn, testbtn;
    WifiManager mainWifi;
//    MainActivity.WifiReceiver receiverWifi;
    WifiReceiver receiverWifi;
    List<ScanResult> wifiList;
    BarChart barChart;
    String location, SSID1, SSID2;
    int ap1, ap2 , ap3 =0;
    int flag =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.wardrive);
        locationtxt = (EditText)findViewById(R.id.textView2);
        savebtn = (Button)findViewById(R.id.button2);
        testbtn =(Button)findViewById(R.id.button3);
        barChart = findViewById(R.id.idBarChart3);

        mainWifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        receiverWifi = new WifiReceiver();
        registerReceiver(receiverWifi, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        mainWifi.startScan();

        myappdatabase = Room.databaseBuilder(this, AppDatabase.class, "WIFIDATABASE").allowMainThreadQueries().build();
//        myappdatabase.getRssiDao().deleteAll();


        testbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Wardrive.this, TestLocation.class);
                startActivity(intent);

            }
        });
    }

    protected void onPause() {
        unregisterReceiver(receiverWifi);
        super.onPause();
    }

    protected void onResume() {
        registerReceiver(receiverWifi, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        super.onResume();
    }

    class WifiReceiver extends BroadcastReceiver {

        // This method call when number of wifi connections changed
        public void onReceive(Context c, Intent intent) {

            ArrayList<BarEntry> barEntriesArrayList = new ArrayList<>();
            ArrayList< String> labels = new ArrayList<String>();


            wifiList = mainWifi.getScanResults();


            for(int i = 0; i < wifiList.size(); i++){

//                barEntriesArrayList.add(new BarEntry(i, (wifiList.get(i).level)));
                barEntriesArrayList.add(new BarEntry((wifiList.get(i).level), i));
                labels.add((wifiList.get(i).SSID));

                Log.i("wifilist",wifiList.get(i).SSID );
                Log.i("wifilist", String.valueOf(wifiList.get(i).level));
                if(wifiList.get(i).SSID.equals("RAJNI") ){

                    ap1 = wifiList.get(i).level;
                }
                if(wifiList.get(i).SSID.equals("ANJALI")){

                    ap2 = wifiList.get(i).level;
                }
                if(wifiList.get(i).SSID.equals("Arun")){

                    ap3 = wifiList.get(i).level;
                }

                Log.i("rssi values", String.valueOf(ap1 +"  " + ap2 ));
            }



            BarDataSet bardataset = new BarDataSet (barEntriesArrayList, "WIFI RSSI VALUES");

            BarData data = new BarData(labels, bardataset);
            barChart.setData(data); // set the data and list of labels into chart

            bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
            barChart.animateXY(2000, 2000);

            savebtn.setOnClickListener(new View.OnClickListener() {
//                @SuppressLint("ResourceType")
                @Override
                public void onClick(View v) {
                    location = locationtxt.getText().toString();


                    RssiData user = new RssiData(location, ap1,ap2,ap3);
                    myappdatabase.getRssiDao().insertAll(user);

                    Log.i("database", "values saved");
                }
            });

        }
    }

}
