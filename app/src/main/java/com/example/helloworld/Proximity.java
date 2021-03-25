package com.example.helloworld;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(tableName = "proximity")
public class Proximity {

    @PrimaryKey(autoGenerate = true)
    public int uid5;

    @ColumnInfo(name = "timestamp")
    public long timestamp;

    @ColumnInfo(name = "proximity")
    public float proximity;

    public int getUid5() {
        return uid5;
    }

    public void setUid5(int uid5) {
        this.uid5 = uid5;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public float getProximity() {
        return proximity;
    }

    public void setProximity(float proximity) {
        this.proximity = proximity;
    }

//    @ColumnInfo(name = "y")
//    public float ycoord;
//
//    @ColumnInfo(name = "z")
//    public float zcoord;

//    @ColumnInfo(name = "name")
//    public String name;

    public Proximity() {
    }
//@Ignore
    public Proximity(long timestamp, float proximity) {
        this.timestamp = timestamp;
//        this.name = name;
        this.proximity = proximity;

    }

    public String getDateString() {
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");

        return sdf.format(new Date(timestamp));
    }

    public String getDebugString() {
        return "UID: " + uid5 + " Timestamp: " + getDateString();
    }
}
