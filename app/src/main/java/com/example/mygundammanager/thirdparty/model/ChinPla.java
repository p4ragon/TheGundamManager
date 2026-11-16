package com.example.mygundammanager.thirdparty.model;

import androidx.room.PrimaryKey;

public abstract class ChinPla {
    //non inizializzo final perchè voglio che sia possibile all'utente correggere errori di battitura
    private String name;
    private Double price;
    private String description;


    //campi richiesti: se ci sono runner avanzati, anno di rilascio
    private Boolean spareRunners;
    private long releaseDate;
    //stringa che contiene URI foto. scelgo di NON implementare URL
    private String photoUri;
    //chiave primaria univoca per identificare il record nel database
    @PrimaryKey(autoGenerate=true)
    //campo id: necessario affinchè Room generi un progressivo.
    private long id;

    public ChinPla(String name, Double price, String description, Boolean spareRunners, long releaseDate, String photoUri){
        this.name=name;
        this.price=price;
        this.description=description;
        this.spareRunners=spareRunners;
        this.releaseDate=releaseDate;
        this.photoUri=photoUri;
    }

    //funzione astratta: ritorna il brand del kit
    public abstract String getBrand();

    public String getName(){return name;}
    public Double getPrice(){return price;}
    public String getDescription(){return description;}
    public long getReleaseDate(){return releaseDate;}
    public Boolean hasSpareRunners(){return spareRunners;}
    public String getPhotoUri(){return photoUri;}


    //ad uso esclusivo di Room
    public long getId() {return id;}
    public void setId(long id) { this.id = id; }

    public void setName(String name){this.name=name;}
    public void setPrice(Double price){this.price=price;}
    public void setDescription(String description){this.description=description;}
    public void setSpareRunners(Boolean spareRunners){this.spareRunners=spareRunners;}
    public void setReleaseDate(long releaseDate){this.releaseDate=releaseDate;}
    public void setPhotoUri(String photoUri){this.photoUri=photoUri;}


}

