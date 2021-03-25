package com.example.helloworld;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface LightDao {
    @Query("SELECT * FROM light")
    List<Light> getAll();
    @Query("SELECT lightvalue FROM light")
    List<Float> getAllvalues();
    @Query("SELECT * FROM light WHERE uid3 = :id")
    List<Light> loadAllById(int id);
    @Insert
    void insertAll(Light... users);

    @Delete
    void delete(Light user);

    @Query("DELETE FROM light")
    void deleteAll();
}
