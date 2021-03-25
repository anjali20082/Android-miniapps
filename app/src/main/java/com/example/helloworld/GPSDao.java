package com.example.helloworld;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface GPSDao {
    @Query("SELECT * FROM gps")
    List<GPS> getAll();
    @Query("SELECT * FROM gps WHERE uid2 = :id")
    List<GPS> loadAllById(int id);
    @Insert
    void insertAll(GPS... users);

    @Delete
    void delete(GPS user);

    @Query("DELETE FROM gps")
    void deleteAll();
}
