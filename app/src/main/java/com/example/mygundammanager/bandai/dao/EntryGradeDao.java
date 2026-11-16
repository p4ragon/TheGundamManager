package com.example.mygundammanager.bandai.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mygundammanager.bandai.model.EntryGradeGundam;

import java.util.List;

@Dao
public interface EntryGradeDao {

    @Insert
    void insert(EntryGradeGundam gundam);

    @Update
    void update(EntryGradeGundam gundam);

    @Delete
    void delete(EntryGradeGundam gundam);

    // Recupera tutti gli Entry Grade
    @Query("SELECT * FROM entry_grade_gundams ORDER BY name ASC")
    List<EntryGradeGundam> getAllEntryGrades();

    // Calcola la spesa totale solo per la categoria Entry Grade
    @Query("SELECT SUM(price) FROM entry_grade_gundams")
    Double getTotalPrice();
}