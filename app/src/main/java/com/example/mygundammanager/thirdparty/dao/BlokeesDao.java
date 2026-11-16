package com.example.mygundammanager.thirdparty.dao;



import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mygundammanager.thirdparty.model.Blokees;

import java.util.List;

@Dao
public interface BlokeesDao {

    @Insert
    void insert(Blokees kit);

    @Update
    void update(Blokees kit);

    @Delete
    void delete(Blokees kit);

    // Recupera tutti gli Entry Grade
    @Query("SELECT * FROM blokees_kits ORDER BY name ASC")
    List<Blokees> getAllBlokees();

    // Calcola la spesa totale solo per la categoria Entry Grade
    @Query("SELECT SUM(price) FROM blokees_kits")
    Double getTotalPrice();
}