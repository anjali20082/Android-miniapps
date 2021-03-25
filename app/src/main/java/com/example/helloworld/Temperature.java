package com.example.helloworld;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(tableName = "temperature")
public class Temperature {

    @PrimaryKey(autoGenerate = true)
    public int uid6;

    @ColumnInfo(name = "timestamp")
    public long timestamp;

    @ColumnInfo(name = "temperature")
    public float temperature;

    public void setUid6(int uid6) {
        this.uid6 = uid6;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public int getUid6() {
        return uid6;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public float getTemperature() {
        return temperature;
    }

    //    @Ignore
    public Temperature() {
    }
@Ignore
    public Temperature(long timestamp, float temperature) {
        this.timestamp = timestamp;
//        this.name = name;
        this.temperature = temperature;
    }

//    public String getDateString() {
//        SimpleDateFormat sdf = new SimpleDateFormat();
//        sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");
//
//        return sdf.format(new Date(timestamp));
//    }
//
//    public String getDebugString() {
//        return "UID: " + uid6+ " Timestamp: " + getDateString() ;
//    }

}
