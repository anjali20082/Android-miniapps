package com.example.helloworld;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database
        (entities = {AccData.class,GPS.class, Light.class, LinAcc.class, Proximity.class, Temperature.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract AccDao getAccDao();
    public abstract  GPSDao getGPSDao();
    public abstract  LightDao getLightDao();
    public abstract  LinAccDao getLinaccDao();
    public abstract  ProximityDao getProximityDao();
    public abstract  TempDao getTempDao();
    private static AppDatabase instance;


//    static AppDatabase getDatabase(final Context context) {
//        if (instance == null) {
//            synchronized (AppDatabase.class) {
//                instance = Room.databaseBuilder(context, AppDatabase.class, "DATABASE").allowMainThreadQueries().build();
//            }
//        }
//        return instance;
//
//
//    }


}