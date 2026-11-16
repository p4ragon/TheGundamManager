package com.example.mygundammanager.bandai.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mygundammanager.bandai.model.RealGradeGundam;

import java.util.List;

@Dao
public interface RealGradeDao {
    @Insert
    void insert(RealGradeGundam gundam);

    @Update
    void update(RealGradeGundam gundam);

    @Delete
    void delete(RealGradeGundam gundam);

    @Query("SELECT * FROM real_grade_gundams ORDER BY name ASC")
    List<RealGradeGundam> getAllRealGrades();

    @Query("SELECT SUM(price) FROM real_grade_gundams")
    Double getTotalPrice();
}