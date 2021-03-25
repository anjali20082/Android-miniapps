package com.example.helloworld;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements  SensorEventListener,LocationListener  {
    private SensorManager mSensorManager;
    private LocationManager mSensorManagerLoc;
    private Sensor mAcc;
    private Sensor mSensorProximity;
    private Sensor mSensorLight;
    private Sensor mSensorTemp;
    private Sensor mLinAcc;
    // TextViews to display current sensor values
    private TextView mTextSensorLight, mTextSensoraccx, mTextSensoraccy, mTextSensoraccz, mTextLinaccx, mTextLinaccy, mTextLinaccz, motiondetect;
    private TextView mTextSensorProximity, mtexttemp, mtextlatitude, mtextlongitude, textlightavg, textaccavg;
    private Switch accbtn, linaccbtn, lightbtn, gpsbtn, proxbtn, tempbtn;
    private Button avgaccbtn, avglightbtn;
    private AppDatabase myappdatabase;
    private float provider;
    int sensorType;
    float currentValue, currentValue2, currentValue3;
    public double latitude, longitude;
    Location l;
    public SensorEventListener sensorEventListener;
    private float[] mGravity;
    private float mAccel;
    private float mAccelCurrent;
    private float mAccelLast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String sensor_error = getResources().getString(R.string.error_no_sensor);
        mTextSensorLight = (TextView) findViewById(R.id.LightTextView);
        mTextSensorProximity = (TextView) findViewById(R.id.ProxTextView);
        mTextSensoraccx = (TextView) findViewById(R.id.AccxTextView);
        mTextSensoraccy = (TextView) findViewById(R.id.AccyTextView);
        mTextSensoraccz = (TextView) findViewById(R.id.AcczTextView);
        mTextLinaccx = (TextView) findViewById(R.id.LinAccxTextView);
        mTextLinaccy = (TextView) findViewById(R.id.LinAccyTextView);
        mTextLinaccz = (TextView) findViewById(R.id.LinAcczTextView);
        mtexttemp = (TextView) findViewById(R.id.TempTextView);
        mtextlatitude = (TextView) findViewById(R.id.LatitudeTextView);
        mtextlongitude = (TextView) findViewById(R.id.LongitudeTextView);
        textlightavg = (TextView) findViewById(R.id.tempavgtextview);
        textaccavg = (TextView) findViewById(R.id.accavgtextview);
        motiondetect =(TextView)findViewById(R.id.Walking_Stop_Txtview);
        avgaccbtn = (Button)findViewById(R.id.Acc1hravgbutton);
        avglightbtn = (Button) findViewById(R.id.Temp1hravgbutton);
        accbtn = (Switch) findViewById(R.id.AccSwitch);
        linaccbtn = (Switch) findViewById(R.id.LinAccSwitch);
        lightbtn = (Switch) findViewById(R.id.LightSwitch);
        gpsbtn = (Switch) findViewById(R.id.GPSSwitch);
        proxbtn = (Switch) findViewById(R.id.ProxSwitch);
        tempbtn = (Switch) findViewById(R.id.TempSwitch);


        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorManagerLoc = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        List<Sensor> sensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        mAcc = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        mSensorLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mSensorTemp = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        mLinAcc = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        System.out.println((sensorList));
        int x = sensorList.size();
        Log.i("noofsensors", String.valueOf(x));
        Log.i("list", sensorList.get(0).toString());

        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;



    }



    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        //sensor accuracy changes
    }

    @Override
//    sensorEventListener
    public final void onSensorChanged(SensorEvent event) {

        sensorType = event.sensor.getType();
        currentValue = event.values[0];
        Date date = new Date();

        switch (sensorType) {
            // Event came from the light sensor.
            case Sensor.TYPE_LIGHT:
                // Handle light sensor
//                String accx = String.valueOf(currentValue);
                mTextSensorLight.setText(String.valueOf(currentValue));

                Light user3 = new Light(date.getTime(), currentValue);
//                AppDatabase.getDatabase(getApplicationContext()).getLightDao().insertAll(user3);
                myappdatabase.getLightDao().insertAll(user3);
                break;

            case Sensor.TYPE_PROXIMITY:
//                String prox = String.valueOf(currentValue);
                mTextSensorProximity.setText(String.valueOf(currentValue));

                Proximity user4 = new Proximity(date.getTime(), currentValue);
//                AppDatabase.getDatabase(getApplicationContext()).getProximityDao().insertAll(user4);
                myappdatabase.getProximityDao().insertAll(user4);
                break;

            case Sensor.TYPE_ACCELEROMETER:
                currentValue2 = event.values[1];
                currentValue3 = event.values[2];
                mTextSensoraccx.setText(String.valueOf(currentValue));
                mTextSensoraccy.setText(String.valueOf(currentValue2));
                mTextSensoraccz.setText(String.valueOf(currentValue3));
                

                AccData user = new AccData(date.getTime(), currentValue,currentValue2,currentValue3);
//                AppDatabase.getDatabase(getApplicationContext()).getAccDao().insertAll(user);
                myappdatabase.getAccDao().insertAll(user);

                mGravity = event.values.clone();
                // Shake detection
                float x = mGravity[0];
                float y = mGravity[1];
                float z = mGravity[2];
                mAccelLast = mAccelCurrent;
                mAccelCurrent = (float) Math.sqrt(x*x + y*y + z*z);
                float delta = mAccelCurrent - mAccelLast;
                mAccel = mAccel * 0.9f + delta;
                // Make this higher or lower according to how much
                // motion you want to detect
                if(mAccel > 0.55){
                    // do something
                    motiondetect.setText("Walking");
                }else {
                    motiondetect.setText("Stationary");
                }
                break;

            case Sensor.TYPE_LINEAR_ACCELERATION:
                currentValue2 = event.values[1];
                currentValue3 = event.values[2];
                mTextLinaccx.setText(String.valueOf(currentValue));
                mTextLinaccy.setText(String.valueOf(currentValue2));
                mTextLinaccz.setText(String.valueOf(currentValue3));

                LinAcc user2 = new LinAcc(date.getTime(), currentValue,currentValue2,currentValue3);
//                AppDatabase.getDatabase(getApplicationContext()).getLinaccDao().insertAll(user2);
                myappdatabase.getLinaccDao().insertAll(user2);
                break;
            case Sensor.TYPE_GYROSCOPE:
                mtexttemp.setText(String.valueOf(currentValue));

                Temperature user5 = new Temperature(date.getTime(),currentValue);
                myappdatabase.getTempDao().insertAll(user5);
                break;


            default:
              // do nothing

        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        myappdatabase = Room.databaseBuilder(this, AppDatabase.class, "DATABASE").allowMainThreadQueries().build();
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
//        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 153);
        if (mAcc != null) {
            mSensorManager.registerListener(this, mAcc, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorProximity != null) {
            mSensorManager.registerListener(this, mSensorProximity, SensorManager.SENSOR_DELAY_FASTEST);
        }
        if (mSensorLight != null) {
            mSensorManager.registerListener(this, mSensorLight, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mLinAcc != null) {
            mSensorManager.registerListener(this, mLinAcc, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorTemp != null) {
            mSensorManager.registerListener(this, mSensorTemp, SensorManager.SENSOR_DELAY_NORMAL);
        }


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(this, " ", Toast.LENGTH_SHORT).show();
//            return;
        }
        boolean isgpsenabled = mSensorManagerLoc.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (isgpsenabled) {

            mSensorManagerLoc.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
            l = mSensorManagerLoc.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
        else{
            Toast.makeText(this,"not enabled", Toast.LENGTH_SHORT).show();
        }


        accbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Log.v("Switch State=", "" + isChecked);
                    registersensor(mAcc);
                }else if(!isChecked)
                {
                   unregistersensor(mAcc);
                }

            }
        });

        avgaccbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("accavg","Average accelerometer pressed");
                List<AccData> accdata = myappdatabase.getAccDao().getAll();
                if(accdata.size()==0){
                    textaccavg.setText("no data available");
                }else{
                    double sumx =0, sumy =0, sumz=0;
                    long count =0;

                    for(AccData acc : accdata){
                        sumx = sumx + acc.getXcoord();
                        sumy += acc.getYcoord();
                        sumz += acc.getZcoord();
                        count +=1;
                    }
                    double avg_x = sumx/count;
                    double avg_y = sumy/count;
                    double avg_z = sumz/count;
                    textaccavg.setText("Average X:"+avg_x+" "+"Average Y:"+avg_y+" "+ "Average Z:"+avg_z);
                }

            }
        });


        avglightbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("lightavg","Average light pressed");
//

                List<Light> lightvalue = myappdatabase.getLightDao().getAll();
                if(lightvalue.size()==0){
                    textlightavg.setText("no data available");
                }else{
                    float sumlight =0;
                    long count =0;

                    for(Light l : lightvalue){
                        sumlight = sumlight + l.getLight();
                        count +=1;
                    }
                    float avg_light= sumlight/count;
                    textlightavg.setText(String.valueOf( avg_light));
                }

            }
        });


        }


        @Override
        protected void onPause () {
            super.onPause();
            mSensorManager.unregisterListener(this);

        }
public boolean registersensor(Sensor s){
        return mSensorManager.registerListener(sensorEventListener,s,SensorManager.SENSOR_DELAY_NORMAL);

}
public void unregistersensor(Sensor s){

    mSensorManager.unregisterListener(sensorEventListener,s);
}

        @Override
        public void onLocationChanged (@NonNull Location location){

        mtextlatitude.setText(String.valueOf(l.getLatitude()));
        mtextlongitude.setText(String.valueOf(l.getLongitude()));

        }

        @Override
        public void onProviderEnabled (@NonNull String provider){

        }

        @Override
        public void onProviderDisabled (String provider){

        }

//        @Override
//        public void onCheckedChanged (CompoundButton buttonView,boolean isChecked){
//
//            switch (buttonView.getId()) {
//        case R.id.AccSwitch:
//            if(isChecked){
//                    Log.v("Switch State=", "" + isChecked);
////                    mSensorManager.registerListener(this, mAcc, SensorManager.SENSOR_DELAY_NORMAL);
//                registersensor(mAcc);
//                }else if(!isChecked)
//                {
////                    mSensorManager.unregisterListener(this);
//                    unregistersensor();
//                    mTextSensoraccx.setText(" ");
//                    mTextSensoraccy.setText(" ");
//                    mTextSensoraccz.setText(" ");
//                }
//
//            }
//        }



    }
