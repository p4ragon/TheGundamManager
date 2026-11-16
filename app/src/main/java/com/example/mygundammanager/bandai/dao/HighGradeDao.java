package com.example.mygundammanager.bandai.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mygundammanager.bandai.model.HighGradeGundam;

import java.util.List;

@Dao
public interface HighGradeDao {
    @Insert
    void insert(HighGradeGundam gundam);

    @Update
    void update(HighGradeGundam gundam);

    @Delete
    void delete(HighGradeGundam gundam);

    @Query("SELECT * FROM high_grade_gundams ORDER BY name ASC")
    List<HighGradeGundam> getAllHighGrades();

    @Query("SELECT SUM(price) FROM high_grade_gundams")
    Double getTotalPrice();
}