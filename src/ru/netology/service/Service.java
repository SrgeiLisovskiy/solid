package ru.netology.service;

import ru.netology.model.Cart;
import ru.netology.model.Product;

import java.util.List;
import java.util.stream.Collectors;

public class Service implements FiltrationService, OrderService {
    WarehouseService warehouseService;

    public Service(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @Override
    public List<Product> filterByName(String name) {
        List<Product> products = warehouseService.getProducts()
                .stream()
                .filter(product -> product.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
        if (products.isEmpty()) {
            System.out.println("Совпадений не найдено");
        }
        return products;
    }

    @Override
    public List<Product> filterByPrice(double minPrice, double maxPrice) {
        List<Product> products = warehouseService.getProducts()
                .stream()
                .filter(product -> product.getPrice() >= minPrice && product.getPrice() <= maxPrice)
                .collect(Collectors.toList());
        if (products.isEmpty()) {
            System.out.println("Не найдено товаров подходящие под параметры");
        }
        return products;
    }

    @Override
    public List<Product> filterByCountry(String country) {
        List<Product> products = warehouseService.getProducts()
                .stream()
                .filter(product -> product.getCountryOfOrigin().equals(country))
                .collect(Collectors.toList());
        if (products.isEmpty()) {
            System.out.println("Не найдено товаров подходящие под параметры");
        }
        return products;
    }

    @Override
    public void printAvailableProducts(List<Product> products) {                                                // Правило DRY
        System.out.println("Доступные к покупке товары:");
        for (int i = 0; i < products.size(); i++) {                                                                 // принцип избегания магических чисел
            if (warehouseService.getWarehouse().get(products.get(i).getId()) > 0) {
                System.out.println("ID товара: " + products.get(i).getId() + ", наименование товара: "
                        + products.get(i).getName() + ", страна происхождения: "
                        + products.get(i).getCountryOfOrigin() + ", цена: " + products.get(i).getPrice());
            }
        }
    }

    @Override
    public void returnOrder(Long cartId) {
        Cart cart = warehouseService.getCart(cartId);
        for (long id : cart.getProducts().keySet()) {
            warehouseService.addProduct(warehouseService.getProduct(id), cart.getProducts().get(id));
        }
        System.out.println("Возврат денежных средств в размере " + cart.getSum());
    }

    @Override
    public Cart repeatOrder(Long cartId) {
        Cart cart = warehouseService.getCart(cartId);
        for (long id : cart.getProducts().keySet()) {
            warehouseService.editWarehouse(cartId, cart.getProducts().get(id));
        }
        return cart;
    }

    public void payment(Cart cart) {
        System.out.println("********* Чек *********");
        double sum = 0;
        for (long id : cart.getProducts().keySet()) {
            double value = cart.getProducts().get(id);
            Product product = warehouseService.getProduct(id);
            System.out.println(product.getName() + "              " + product.getPrice() + "x" + value + " = " + product.getPrice() * value);
            sum = sum + (product.getPrice() * value);
        }
        cart.setSum(sum);
        System.out.println("Общая сумма: " + sum);
        System.out.println("ID заказа: " + cart.getId());
        System.out.println();
        warehouseService.saveCart(cart.getId(), cart);
    }

}
