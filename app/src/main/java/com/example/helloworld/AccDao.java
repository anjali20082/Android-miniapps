package com.example.helloworld;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


@Dao
public interface AccDao {
    @Query("SELECT * FROM accdata")
    List<AccData> getAll();
    @Query("SELECT * FROM accdata WHERE datetime(timestamp) >= datetime('now','-1 Hour')")
    List<AccData> getAllonehr();
    @Query("SELECT xcoord  FROM accdata")
    List<Float> getAllx();
    @Query("SELECT ycoord  FROM accdata")
    List<Float> getAlly();
    @Query("SELECT zcoord  FROM accdata")
    List<Float> getAllz();
    @Query("SELECT * FROM accdata WHERE uid1 = :id")
    List<AccData> loadAllById(int id);
    @Insert
    void insertAll(AccData... users);

    @Delete
    void delete(AccData user);

    @Query("DELETE FROM accdata")
    void deleteAll();
}
