package com.example.mygundammanager.bandai.model;

import androidx.room.Entity;

@Entity(tableName = "metal_build_gundams")
public class MetalBuildGundam extends Gundam {
    public MetalBuildGundam(String name, Double price, String description, Boolean spareRunners, long releaseDate, String photoUri) {
        super(name, price, description, spareRunners, releaseDate, photoUri);
    }

    @Override
    public String getGrade() {
        return "Metal Build";
    }
}