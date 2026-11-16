package com.example.mygundammanager.bandai.model;

import androidx.room.Entity;

@Entity(tableName = "mega_size_gundams")
public class MegaSizeGundam extends Gundam {
    public MegaSizeGundam(String name, Double price, String description, Boolean spareRunners, long releaseDate, String photoUri) {
        super(name, price, description, spareRunners, releaseDate, photoUri);
    }

    @Override
    public String getGrade() {
        return "Mega Size";
    }
}