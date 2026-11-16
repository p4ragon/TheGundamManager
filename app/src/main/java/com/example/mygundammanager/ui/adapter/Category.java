package com.example.mygundammanager.ui.adapter;


public class Category {
    private final String name;
    private final boolean isBandai;

    public Category(String name, boolean isBandai) {
        this.name = name;
        this.isBandai = isBandai;
    }

    public String getName() { return name; }
    public boolean isBandai() { return isBandai; }
}