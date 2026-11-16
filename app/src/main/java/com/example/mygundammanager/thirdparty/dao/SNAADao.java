package com.example.mygundammanager.thirdparty.dao;



import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mygundammanager.thirdparty.model.SNAA;

import java.util.List;

@Dao
public interface SNAADao {

    @Insert
    void insert(SNAA kit);

    @Update
    void update(SNAA kit);

    @Delete
    void delete(SNAA kit);

    // Recupera tutti gli Entry Grade
    @Query("SELECT * FROM snaa_kits ORDER BY name ASC")
    List<SNAA> getAllSNAAs();

    // Calcola la spesa totale solo per la categoria Entry Grade
    @Query("SELECT SUM(price) FROM snaa_kits")
    Double getTotalPrice();
}