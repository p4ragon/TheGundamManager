package com.example.mygundammanager.thirdparty.dao;



import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mygundammanager.thirdparty.model.CangToys;

import java.util.List;

@Dao
public interface CangToysDao {

    @Insert
    void insert(CangToys kit);

    @Update
    void update(CangToys kit);

    @Delete
    void delete(CangToys kit);

    // Recupera tutti gli Entry Grade
    @Query("SELECT * FROM cang_toys_kits ORDER BY name ASC")
    List<CangToys> getAllCANGs();

    // Calcola la spesa totale solo per la categoria Entry Grade
    @Query("SELECT SUM(price) FROM cang_toys_kits")
    Double getTotalPrice();
}