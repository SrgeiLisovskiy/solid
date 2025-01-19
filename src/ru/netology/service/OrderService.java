package ru.netology.service;

import ru.netology.model.Cart;
import ru.netology.model.Product;

import java.util.List;

interface OrderService {
    void printAvailableProducts(List<Product> products);

    void returnOrder(Long cart);

    Cart repeatOrder(Long cart);

}
