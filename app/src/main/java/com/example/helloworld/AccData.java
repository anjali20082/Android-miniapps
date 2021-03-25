package com.example.helloworld;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import java.text.SimpleDateFormat;
import java.util.Date;
@Entity(tableName = "accdata")
public class AccData {

    @PrimaryKey(autoGenerate = true)
    public int uid1;

    @ColumnInfo(name = "timestamp")
    public long timestamp;

    @ColumnInfo(name = "xcoord")
    public float xcoord;

    @ColumnInfo(name = "ycoord")
    public float ycoord;

    @ColumnInfo(name = "zcoord")
    public float zcoord;

    public int getUid1() {
        return uid1;
    }

    public void setUid1(int uid1) {
        this.uid1 = uid1;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public float getXcoord() {
        return xcoord;
    }

    public void setXcoord(float xcoord) {
        this.xcoord = xcoord;
    }

    public float getYcoord() {
        return ycoord;
    }

    public void setYcoord(float ycoord) {
        this.ycoord = ycoord;
    }

    public float getZcoord() {
        return zcoord;
    }

    public void setZcoord(float zcoord) {
        this.zcoord = zcoord;
    }

    //    @ColumnInfo(name = "name")
//    public String name;
//@Ignore
    public AccData() {
    }
//@Ignore
    public AccData(long timestamp, float xcoord, float ycoord, float zcoord) {
        this.timestamp = timestamp;
//        this.name = name;
        this.xcoord = xcoord;
        this.ycoord = ycoord;
        this.zcoord = zcoord;
    }
//
//    public String getDateString() {
//        SimpleDateFormat sdf = new SimpleDateFormat();
//        sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");
//
//        return sdf.format(new Date(timestamp));
//    }
//
//    public String getDebugString() {
//        return "UID: " + uid1 + " Timestamp: " + getDateString() ;
//    }

}
