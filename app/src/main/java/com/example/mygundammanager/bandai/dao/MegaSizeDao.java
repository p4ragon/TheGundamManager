package com.example.mygundammanager.bandai.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mygundammanager.bandai.model.MegaSizeGundam;

import java.util.List;

@Dao
public interface MegaSizeDao {
    @Insert
    void insert(MegaSizeGundam gundam);

    @Update
    void update(MegaSizeGundam gundam);

    @Delete
    void delete(MegaSizeGundam gundam);

    @Query("SELECT * FROM mega_size_gundams ORDER BY name ASC")
    List<MegaSizeGundam> getAllMegaSizes();

    @Query("SELECT SUM(price) FROM mega_size_gundams")
    Double getTotalPrice();
}