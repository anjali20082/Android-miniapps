package com.example.helloworld;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface LinAccDao {

    @Query("SELECT * FROM linacc")
    List<LinAcc> getAll();
    @Query("SELECT * FROM linacc WHERE uid4 = :id")
    List<LinAcc> loadAllById(int id);
    @Insert
    void insertAll(LinAcc... users);

    @Delete
    void delete(LinAcc user);

    @Query("DELETE FROM linacc")
    void deleteAll();
}
