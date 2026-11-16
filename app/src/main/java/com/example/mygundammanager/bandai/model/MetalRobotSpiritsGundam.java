package com.example.mygundammanager.bandai.model;

import androidx.room.Entity;

@Entity(tableName = "metal_robot_spirits_gundams")
public class MetalRobotSpiritsGundam extends Gundam {
    public MetalRobotSpiritsGundam(String name, Double price, String description, Boolean spareRunners, long releaseDate, String photoUri) {
        super(name, price, description, spareRunners, releaseDate, photoUri);
    }

    @Override
    public String getGrade() {
        return "Metal Robot Spirits";
    }
}