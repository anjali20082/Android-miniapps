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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    TextView mainText;
    WifiManager mainWifi;
    WifiReceiver receiverWifi;
    List<ScanResult> wifiList;
    StringBuilder sb = new StringBuilder();
    private Button buttonScan, wardrivebutton;
    // variable for our bar chart
    BarChart barChart;
    // variable for our bar data.

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // initializing variable for bar chart.

        barChart = findViewById(R.id.idBarChart);

        mainText = (TextView) findViewById(R.id.textView);
        mainWifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        if (mainWifi.isWifiEnabled() == false)
        {
            // If wifi disabled then enable it
            Toast.makeText(getApplicationContext(), "wifi is disabled..making it enabled",
                    Toast.LENGTH_LONG).show();
            mainWifi.setWifiEnabled(true);
        }

        receiverWifi = new WifiReceiver();
        buttonScan = findViewById(R.id.scanBtn);
        wardrivebutton = findViewById(R.id.nxtbutton);
        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
//                scanWifi();
                registerReceiver(receiverWifi, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
                mainWifi.startScan();
                mainText.setText("Starting Scan...");

            }

        });


        wardrivebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Wardrive.class);
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

            sb = new StringBuilder();
            wifiList = mainWifi.getScanResults();
            sb.append("\n        Number Of Wifi connections :"+wifiList.size()+"\n\n");

            for(int i = 0; i < wifiList.size(); i++){

                sb.append(new Integer(i+1).toString() + ". ");
                sb.append(("WiFI Name : "+ wifiList.get(i).SSID) + "  ");
                sb.append(("  WIfi Strength : "+ wifiList.get(i).level));
//                barEntriesArrayList.add(new BarEntry(i, (wifiList.get(i).level)));
                barEntriesArrayList.add(new BarEntry((wifiList.get(i).level), i));
                labels.add((wifiList.get(i).SSID));
                Log.i("wifilist",wifiList.get(i).SSID );
                sb.append("\n");
            }

            mainText.setText(sb);
            BarDataSet bardataset = new BarDataSet (barEntriesArrayList, "WIFI RSSI VALUES");

            BarData data = new BarData(labels, bardataset);
            barChart.setData(data); // set the data and list of labels into chart

            bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
            barChart.animateXY(2000, 2000);

        }
    }


}