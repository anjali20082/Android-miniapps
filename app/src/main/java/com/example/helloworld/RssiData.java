package com.example.helloworld;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "rssidata")
public class RssiData {

    @PrimaryKey(autoGenerate = true)
    public int uid1;

    @ColumnInfo(name = "location")
    public String location;

    @ColumnInfo(name = "ap1")
    public int ap1;

    @ColumnInfo(name = "ap2")
    public int ap2;

    @ColumnInfo(name = "ap3")
    public int ap3;

    public RssiData(){

    }
    public RssiData(String location, int ap1, int ap2, int ap3) {
        this.location = location;
        this.ap1 = ap1;
        this.ap2 = ap2;
        this.ap3 = ap3;
    }

    public int getUid1() {
        return uid1;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getAp1() {
        return ap1;
    }

    public void setAp1(int ap1) {
        this.ap1 = ap1;
    }

    public int getAp2() {
        return ap2;
    }

    public void setAp2(int ap2) {
        this.ap2 = ap2;
    }

    public int getAp3() {
        return ap3;
    }

    public void setAp3(int ap3) {
        this.ap3 = ap3;
    }
}
