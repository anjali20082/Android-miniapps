package com.example.helloworld;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database
        (entities = {RssiData.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract RssiDao getRssiDao();

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