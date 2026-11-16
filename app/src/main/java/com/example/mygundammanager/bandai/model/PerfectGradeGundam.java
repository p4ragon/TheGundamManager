package com.example.mygundammanager.bandai.model;

import androidx.room.Entity;

@Entity(tableName = "perfect_grade_gundams")
public class PerfectGradeGundam extends Gundam {
    public PerfectGradeGundam(String name, Double price, String description, Boolean spareRunners, long releaseDate, String photoUri) {
        super(name, price, description, spareRunners, releaseDate, photoUri);
    }

    @Override
    public String getGrade() {
        return "Perfect Grade";
    }
}