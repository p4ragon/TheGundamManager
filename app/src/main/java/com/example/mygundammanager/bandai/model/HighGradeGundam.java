package com.example.mygundammanager.bandai.model;

import androidx.room.Entity;

@Entity(tableName = "high_grade_gundams")
public class HighGradeGundam extends Gundam {
    public HighGradeGundam(String name, Double price, String description, Boolean spareRunners, long releaseDate, String photoUri) {
        super(name, price, description, spareRunners, releaseDate, photoUri);
    }

    @Override
    public String getGrade() {
        return "High Grade";
    }
}