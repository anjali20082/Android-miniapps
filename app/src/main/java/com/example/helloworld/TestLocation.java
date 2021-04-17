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
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class TestLocation extends AppCompatActivity {
    private AppDatabase myappdatabase;
    TextView showlocationtxt, ap1txtview,ap2txtview, ap3txtview, knntxt;
    Button testlocbtn, knntestbtn;
    WifiManager mainWifi;
    WifiReceiver receiverWifi;
    List<ScanResult> wifiList;
    BarChart barChart;
    int ap1, ap2 , ap3 =0;
    int newap1, newap2, newap3=0;
    int diff1 =0, diff2 =0, diff3 =0;
    double distance =0;
    int k = 5;
    String testlocation, testlocationknn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_location);

        showlocationtxt = (TextView) findViewById(R.id.showloctxt);
        knntxt= (TextView)findViewById(R.id.knntxtview);
        ap1txtview = (TextView) findViewById(R.id.ap1txt);
        ap2txtview = (TextView) findViewById(R.id.ap2txt);
        ap3txtview = (TextView) findViewById(R.id.ap3txt);
        testlocbtn = (Button)findViewById(R.id.button4);
        knntestbtn = (Button)findViewById(R.id.knnbtn);

        barChart = findViewById(R.id.idBarChart3);

        mainWifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        receiverWifi = new WifiReceiver();
        registerReceiver(receiverWifi, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        mainWifi.startScan();

        myappdatabase = Room.databaseBuilder(this, AppDatabase.class, "WIFIDATABASE").allowMainThreadQueries().build();
//        myappdatabase.getRssiDao().deleteAll();

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
//                    newap1 = ap1;
                }
                if(wifiList.get(i).SSID.equals("ANJALI")){

                    ap2 = wifiList.get(i).level;
//                    newap2 =ap2;
                }
                if(wifiList.get(i).SSID.equals("Arun")){

                    ap3 = wifiList.get(i).level;
//                    newap3 =ap3;
                }

                Log.i("rssi_values_test", String.valueOf(ap1 +"  " + ap2 + " "+ ap3));
            }

            Log.i("outsideforloop ", String.valueOf(ap1+ " "+ap2+ " " +ap3));
//
                    ap1txtview.setText(String.valueOf(ap1));
                    ap2txtview.setText(String.valueOf(ap2));
                    ap3txtview.setText(String.valueOf(ap3));

            BarDataSet bardataset = new BarDataSet (barEntriesArrayList, "WIFI RSSI VALUES");

            BarData data = new BarData(labels, bardataset);
            barChart.setData(data); // set the data and list of labels into chart

            bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
            barChart.animateXY(2000, 2000);

            testlocbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    newap1 = ap1;
                    newap2 =ap2;
                    newap3 =ap3;
                    Log.i( "rssi values ", ap1 + " "+ ap2 + " "+ ap3);

                    List<Integer> ap1list =  myappdatabase.getRssiDao().getAllap1();
                    List<Integer> ap2list =  myappdatabase.getRssiDao().getAllap2();
                    List<Integer> ap3list =  myappdatabase.getRssiDao().getAllap3();
                    List<String> loclist = myappdatabase.getRssiDao().getlocation();
                    List<Double> distlist=new ArrayList<Double>();


                    for( int i = 0; i < ap1list.size(); i++){

                        diff1 = Math.abs(newap1 - ap1list.get(i));
                        diff2 =  Math.abs(newap2 - ap2list.get(i));
                        diff3 =  Math.abs(newap3 - ap3list.get(i));
                        distance = Math.sqrt(diff1*diff1+diff2*diff2+diff3*diff3);
                        distlist.add(distance);


                    }
                    int minIndex = distlist.indexOf(Collections.min(distlist));
                    testlocation = loclist.get(minIndex);
                    showlocationtxt.setText(testlocation);

                }
            });

            knntestbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    newap1 = ap1;
                    newap2 =ap2;
                    newap3 =ap3;
                    Log.i( "rssi values ", ap1 + " "+ ap2 + " "+ ap3);

                    List<Integer> ap1list =  myappdatabase.getRssiDao().getAllap1();
                    List<Integer> ap2list =  myappdatabase.getRssiDao().getAllap2();
                    List<Integer> ap3list =  myappdatabase.getRssiDao().getAllap3();
                    List<String> loclist = myappdatabase.getRssiDao().getlocation();
                    List<Double> distlist=new ArrayList<Double>();
                    List< Integer>neighbours = new ArrayList<Integer>();
                    List<String> neighloclist = new ArrayList<String>();


                    for( int i = 0; i < ap1list.size(); i++){

                        diff1 = Math.abs(newap1 - ap1list.get(i));
                        diff2 =  Math.abs(newap2 - ap2list.get(i));
                        diff3 =  Math.abs(newap3 - ap3list.get(i));
                        distance = Math.sqrt(diff1*diff1+diff2*diff2+diff3*diff3);
                        distlist.add(distance);
                    }

//                    int minIndex = distlist.indexOf(Collections.min(distlist));
                    List<Double> copy_dist = new ArrayList<Double>(distlist);
                    System.out.println(distlist);

                    for (int i = 0; i < k; i++) {
                        int IndexMin = 0;

                        for (int j = 1; j < copy_dist.size(); j++) {
                            if (copy_dist.get(j) < copy_dist.get(IndexMin)) {
                                IndexMin = j;
                            }
                        }
//                        neighbours.add(distlist.remove(IndexMin));
                        neighbours.add((IndexMin));
                        copy_dist.remove(IndexMin);
                    }
                    System.out.println(neighbours);

                    for(int p =0 ; p<neighbours.size(); p++){
                        neighloclist.add(loclist.get(neighbours.get(p)));
                    }
                    String[] arr = neighloclist.toArray(new String[0]);


                    testlocationknn = findWord(arr);
                    knntxt.setText(testlocationknn);
                }
            });

        }

        public String findWord(String[] neighbours)
        {
            HashMap<String, Integer> most_common = new HashMap<String, Integer>();

            for (int i = 0; i < neighbours.length; i++) {

                if (most_common.containsKey(neighbours[i])) {
                    most_common.put(neighbours[i], most_common.get(neighbours[i]) + 1);
                }
                else {
                    most_common.put(neighbours[i], 1);
                }
            }
            Set<Map.Entry<String, Integer> > set = most_common.entrySet();
            String key = "";
            int value = 0;

            for (Map.Entry<String, Integer> mode : set) {
                if (mode.getValue() > value) {
                    value = mode.getValue();
                    key = mode.getKey();
                }
            }
            return key;
        }
    }


}