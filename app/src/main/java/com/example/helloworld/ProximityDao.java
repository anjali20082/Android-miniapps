package com.example.helloworld;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface ProximityDao {

    @Query("SELECT * FROM proximity")
    List<Proximity> getAll();
    @Query("SELECT * FROM proximity WHERE uid5= :id")
    List<Proximity> loadAllById(int id);
    @Insert
    void insertAll(Proximity... users);

    @Delete
    void delete(Proximity user);

    @Query("DELETE FROM proximity")
    void deleteAll();
}
