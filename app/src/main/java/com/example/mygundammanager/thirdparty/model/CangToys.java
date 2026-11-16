package com.example.mygundammanager.thirdparty.model;

import androidx.room.Entity;

@Entity(tableName = "cang_toys_kits")
public class CangToys extends ChinPla{
    public CangToys(String name, Double price, String description, Boolean spareRunners, long releaseDate, String photoUri) {
        super(name, price, description, spareRunners, releaseDate, photoUri);
    }

    @Override
    public String getBrand() {
        return "CANG TOYS";
    }

}
