package com.example.mygundammanager.bandai.model;

import androidx.room.Entity;

@Entity(tableName = "master_grade_gundams")
public class MasterGradeGundam extends Gundam {
    public MasterGradeGundam(String name, Double price, String description, Boolean spareRunners, long releaseDate, String photoUri) {
        super(name, price, description, spareRunners, releaseDate, photoUri);
    }

    @Override
    public String getGrade() {
        return "Master Grade";
    }
}