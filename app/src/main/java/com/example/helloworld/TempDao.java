package com.example.helloworld;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface TempDao {
    @Query("SELECT * FROM temperature")
    List<Temperature> getAll();

    @Query("SELECT * FROM temperature WHERE uid6 = :id")
    List<Temperature> loadAllById(int id);
    @Insert
    void insertAll(Temperature... users);

    @Delete
    void delete(Temperature user);

    @Query("DELETE FROM temperature")
    void deleteAll();
}
