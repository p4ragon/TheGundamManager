package com.example.mygundammanager.bandai.model;

import androidx.room.Entity;

@Entity(tableName = "real_grade_gundams")
public class RealGradeGundam extends Gundam {
    public RealGradeGundam(String name, Double price, String description, Boolean spareRunners, long releaseDate, String photoUri) {
        super(name, price, description, spareRunners, releaseDate, photoUri);
    }

    @Override
    public String getGrade() {
        return "Real Grade";
    }
}