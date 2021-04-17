package com.example.helloworld;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RssiDao {
    @Query("SELECT * FROM rssidata")
    List<RssiData> getAll();
    @Query("SELECT location FROM rssidata ")
    List<String> getlocation();
    @Query("SELECT ap1  FROM rssidata")
    List<Integer> getAllap1();
    @Query("SELECT ap2  FROM rssidata")
    List<Integer> getAllap2();
    @Query("SELECT ap3  FROM rssidata")
    List<Integer> getAllap3();
    @Query("SELECT * FROM rssidata WHERE uid1 = :id")
    List<RssiData> loadAllById(int id);
    @Insert
    void insertAll(RssiData... users);

    @Delete
    void delete(RssiData user);

    @Query("DELETE FROM rssidata")
    void deleteAll();
}
