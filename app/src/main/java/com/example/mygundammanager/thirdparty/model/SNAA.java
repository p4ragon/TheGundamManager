package com.example.mygundammanager.thirdparty.model;

import androidx.room.Entity;

@Entity(tableName = "snaa_kits")
public class SNAA extends ChinPla{
    public SNAA(String name, Double price, String description, Boolean spareRunners, long releaseDate, String photoUri) {
        super(name, price, description, spareRunners, releaseDate, photoUri);
    }

    @Override
    public String getBrand() {
        return "SNAA";
    }

}
