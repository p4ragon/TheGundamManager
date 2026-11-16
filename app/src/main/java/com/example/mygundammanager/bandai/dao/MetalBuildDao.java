package com.example.mygundammanager.bandai.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mygundammanager.bandai.model.MetalBuildGundam;

import java.util.List;

@Dao
public interface MetalBuildDao {
    @Insert
    void insert(MetalBuildGundam gundam);

    @Update
    void update(MetalBuildGundam gundam);

    @Delete
    void delete(MetalBuildGundam gundam);

    @Query("SELECT * FROM metal_build_gundams ORDER BY name ASC")
    List<MetalBuildGundam> getAllMetalBuilds();

    @Query("SELECT SUM(price) FROM metal_build_gundams")
    Double getTotalPrice();
}