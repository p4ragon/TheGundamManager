package com.example.mygundammanager.bandai.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mygundammanager.bandai.model.PerfectGradeGundam;

import java.util.List;

@Dao
public interface PerfectGradeDao {
    @Insert
    void insert(PerfectGradeGundam gundam);

    @Update
    void update(PerfectGradeGundam gundam);

    @Delete
    void delete(PerfectGradeGundam gundam);

    @Query("SELECT * FROM perfect_grade_gundams ORDER BY name ASC")
    List<PerfectGradeGundam> getAllPerfectGrades();

    @Query("SELECT SUM(price) FROM perfect_grade_gundams")
    Double getTotalPrice();
}