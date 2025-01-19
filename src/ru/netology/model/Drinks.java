package ru.netology.model;

import java.time.LocalDate;

public class Drinks extends Product {
    private double size;
    private СlassificationDrinks classification;


    public Drinks(String name, double price, String countryOfOrigin, double size, LocalDate dateOfManufacture, СlassificationDrinks classification) {
        super(name, price, countryOfOrigin, dateOfManufacture);
        this.size = size;
        this.classification = classification;
    }

    public СlassificationDrinks getClassification() {
        return classification;
    }

}