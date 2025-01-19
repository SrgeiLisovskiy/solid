package ru.netology.model;


import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Product {
    private long id;
    private String name;

    private double price;
    private String countryOfOrigin;
    private LocalDate dateOfManufacture;

    public Product(String name, double price, String countryOfOrigin, LocalDate dateOfManufacture) {
        this.name = name;
        this.price = price;
        this.countryOfOrigin = countryOfOrigin;
        this.dateOfManufacture = dateOfManufacture;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && price == product.price && Objects.equals(name, product.name)
                && Objects.equals(countryOfOrigin, product.countryOfOrigin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, countryOfOrigin);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", countryOfOrigin='" + countryOfOrigin + '\'' +
                '}';
    }


}
