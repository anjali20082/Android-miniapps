package com.example.helloworld;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(tableName = "light")
public class Light {

    @PrimaryKey(autoGenerate = true)
    public int uid3;

    @ColumnInfo(name = "timestamp")
    public long timestamp;

    @ColumnInfo(name = "lightvalue")
    public float lightvalue;

//    @ColumnInfo(name = "y")
//    public float ycoord;
//
//    @ColumnInfo(name = "z")
//    public float zcoord;

    public int getUid3() {
        return uid3;
    }

    public void setUid3(int uid3) {
        this.uid3 = uid3;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public float getLight() {
        return lightvalue;
    }

    public void setLight(float lightvalue) {
        this.lightvalue = lightvalue;
    }
//
//    @ColumnInfo(name = "name")
//    public String name;

    public Light() {
    }
//    @Ignore
    public Light(long timestamp, float lightvalue) {
        this.timestamp = timestamp;
//        this.name = name;
        this.lightvalue = lightvalue;

    }

    public String getDateString() {
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");

        return sdf.format(new Date(timestamp));
    }

    public String getDebugString() {
        return "UID: " + uid3 + " Timestamp: " + getDateString() ;
    }
}
