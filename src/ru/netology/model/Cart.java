package ru.netology.model;

import ru.netology.service.WarehouseService;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    WarehouseService warehouseService;
    private Map<Long, Double> products = new HashMap<>();
    private long id;
    private double sum;


    public Cart(long id, WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
        this.id = id;
    }

    public void addProduct(long productID, double amount) {
        if (warehouseService.editWarehouse(productID, amount)) {
            products.put(productID, amount);
            System.out.println("Товар успешно добавлен в корзину");
        }

    }

    public Map<Long, Double> getProducts() {
        return products;
    }


    public long getId() {
        return id;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
