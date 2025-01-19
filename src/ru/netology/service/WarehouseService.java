package ru.netology.service;

import ru.netology.model.Cart;
import ru.netology.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WarehouseService {
    private long productId = 1;
    private Map<Long, Double> warehouse = new HashMap<>();
    private Map<Long, Product> productsMap = new HashMap<>();
    private List<Product> products = new ArrayList<>();
    private Map<Long, Cart> carts = new HashMap<>();

    public void addProduct(Product product, double amount) {
        if (warehouse.containsKey(product.getId())) {
            warehouse.put(product.getId(), warehouse.get(product.getId()) + amount);
        }
        product.setId(productId);
        warehouse.put(product.getId(), amount);
        productsMap.put(productId, product);
        products.add(product);
        productId += 1;
        System.out.println(product.getName() + " в количестве " + amount + " добавлен на склад");
    }

    public boolean editWarehouse(Long productId, double amount) {
        if (!productsMap.containsKey(productId)){
            System.out.println("Проверьте ID товара, данный товар отсутствует в магазине");
            return false;
        }
        if (checkAmountProduct(productId, amount)) {
            warehouse.put(productId, warehouse.get(productId) - amount);
            System.out.println("Товар с ID = " + productId + " списан с склада в количестве " + amount);
            return true;
        }
        System.out.println("Не достаточно товара на складе. Остаток :" + warehouse.get(productId));
        return false;
    }

    public boolean checkAmountProduct(long id, double amount) {
        try {
            if (warehouse.get(id) > amount) {
                return true;
            }
        } catch (NullPointerException e) {
            System.out.println(e.getStackTrace());
        }
        return false;
    }


    public Product getProduct(Long productId) {
        return productsMap.get(productId);
    }

    public List<Product> getProducts() {
        return products;
    }

    public Map<Long, Double> getWarehouse() {
        return warehouse;
    }

    public void saveCart(long id, Cart cart) {
        carts.put(id, cart);
    }

    public void printCart() {
        for (long id : carts.keySet()) {
            Cart cart = carts.get(id);
            System.out.println("Возможно повторить заказ: ");
            System.out.println("ID :" + cart.getId() + " Общая сумма:" + cart.getSum());
        }
    }

    public Cart getCart(long id) {
        return carts.get(id);
    }
}
