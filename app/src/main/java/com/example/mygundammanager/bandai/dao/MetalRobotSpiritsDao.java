package com.example.mygundammanager.bandai.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mygundammanager.bandai.model.MetalRobotSpiritsGundam;

import java.util.List;

@Dao
public interface MetalRobotSpiritsDao {
    @Insert
    void insert(MetalRobotSpiritsGundam gundam);

    @Update
    void update(MetalRobotSpiritsGundam gundam);

    @Delete
    void delete(MetalRobotSpiritsGundam gundam);

    @Query("SELECT * FROM metal_robot_spirits_gundams ORDER BY name ASC")
    List<MetalRobotSpiritsGundam> getAllMetalRobotSpirits();

    @Query("SELECT SUM(price) FROM metal_robot_spirits_gundams")
    Double getTotalPrice();
}