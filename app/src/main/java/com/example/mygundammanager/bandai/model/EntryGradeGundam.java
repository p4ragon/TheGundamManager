package com.example.mygundammanager.bandai.model;
import androidx.room.Entity;

@Entity(tableName = "entry_grade_gundams")
public class EntryGradeGundam extends Gundam {
    public EntryGradeGundam(String name, Double price, String description, Boolean spareRunners, long releaseDate, String photoUri) {
        super(name, price, description, spareRunners, releaseDate, photoUri);
    }

    @Override
    public String getGrade() {
        return "Entry Grade";
    }

}