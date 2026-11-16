package com.example.mygundammanager.thirdparty.model;

import androidx.room.Entity;

@Entity(tableName = "blokees_kits")
public class Blokees extends ChinPla{
    public Blokees(String name, Double price, String description, Boolean spareRunners, long releaseDate, String photoUri) {
        super(name, price, description, spareRunners, releaseDate, photoUri);
    }

    @Override
    public String getBrand() {
        return "Blokees";
    }

}
