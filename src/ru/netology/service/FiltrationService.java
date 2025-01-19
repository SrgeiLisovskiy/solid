package ru.netology.service;

import ru.netology.model.Product;

import java.util.List;

interface FiltrationService {
    List<Product> filterByName(String name);

    List<Product> filterByPrice(double minPrice, double maxPrice);

    List<Product> filterByCountry(String country);
}
