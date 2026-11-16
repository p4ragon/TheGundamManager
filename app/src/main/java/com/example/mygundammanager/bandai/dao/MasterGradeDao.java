package com.example.mygundammanager.bandai.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mygundammanager.bandai.model.MasterGradeGundam;

import java.util.List;

@Dao
public interface MasterGradeDao {
    @Insert
    void insert(MasterGradeGundam gundam);

    @Update
    void update(MasterGradeGundam gundam);

    @Delete
    void delete(MasterGradeGundam gundam);

    @Query("SELECT * FROM master_grade_gundams ORDER BY name ASC")
    List<MasterGradeGundam> getAllMasterGrades();

    @Query("SELECT SUM(price) FROM master_grade_gundams")
    Double getTotalPrice();
}