package com.example.helloworld;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(tableName = "gps")
public class GPS {

    @PrimaryKey(autoGenerate = true)
    public int uid2;

    @ColumnInfo(name = "timestamp")
    public long timestamp;

    @ColumnInfo(name = "latitude")
    public long latitude;

    @ColumnInfo(name = "longitude")
    public long longitude;
//
//    @ColumnInfo(name = "z")
//    public float zcoord;

    public int getUid2() {
        return uid2;
    }

    public void setUid2(int uid2) {
        this.uid2 = uid2;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

//    @ColumnInfo(name = "name")
//    public String name;

    public GPS() {
    }
//    @Ignore
    public GPS(long timestamp, long latitude, long longitude) {
        this.timestamp = timestamp;
//        this.name = name;
        this.latitude = latitude;
        this.longitude=longitude;
    }

    public String getDateString() {
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");

        return sdf.format(new Date(timestamp));
    }

    public String getDebugString() {
        return "UID: " + uid2 + " Timestamp: " + getDateString() ;
    }
}
