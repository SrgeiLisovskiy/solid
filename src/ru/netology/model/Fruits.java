package ru.netology.model;

import java.time.LocalDate;

public class Fruits extends Product {
    private double weight;
    private String bestBeforeDate;

    public Fruits(String name, double price, String countryOfOrigin, double weight, LocalDate dateOfManufacture, String bestBeforeDate) {
        super(name, price, countryOfOrigin, dateOfManufacture);
        this.weight = weight;
        this.bestBeforeDate = bestBeforeDate;
    }
}
