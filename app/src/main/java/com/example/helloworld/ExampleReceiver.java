package com.example.helloworld;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.os.BatteryManager;
import android.widget.Toast;
import java.lang.String;

public class ExampleReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        String actionString = intent.getAction();
        Toast.makeText(context, actionString, Toast.LENGTH_SHORT).show();
//         if(Intent.ACTION_BATTERY_LOW.equals(actionString)) {
//             Toast.makeText(context, "BATTERY LOW", Toast.LENGTH_SHORT).show();
//         }
//         else if(Intent.ACTION_BATTERY_OKAY.equals(actionString)){
//             Toast.makeText(context, "BATTERY OKAY", Toast.LENGTH_SHORT).show();
//         }
//         else if(Intent.ACTION_POWER_DISCONNECTED.equals(actionString)){
//             Toast.makeText(context, "POWER DISCONNECTED", Toast.LENGTH_SHORT).show();
//         }

//        throw new UnsupportedOperationException("Not yet implemented");
    }
}