package ru.netology;

import ru.netology.model.Cart;
import ru.netology.model.Drinks;
import ru.netology.model.Fruits;
import ru.netology.model.СlassificationDrinks;
import ru.netology.service.Service;
import ru.netology.service.WarehouseService;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        long id = 1;
        Scanner scanner = new Scanner(System.in);
        WarehouseService warehouseService = new WarehouseService();
        warehouseFilling(warehouseService);
        Service service = new Service(warehouseService);
        Cart cart = new Cart(id, warehouseService);
        System.out.println();
        System.out.println();
//        service.printAvailableProducts();
        System.out.println();
        System.out.println();

        System.out.println("Добро пожаловать в магазин");
        while (true) {
            System.out.println();
            System.out.println("0. Показать имеющиеся товары");
            System.out.println("1. Добавить товар в корзину");
            System.out.println("2. Купить товары в отложенные в корзину");
            System.out.println("3. Повторить заказ");
            System.out.println("4. Вернуть заказ");
            System.out.println("5. Выйти из приложения");
            System.out.println();
            System.out.println("Для выбора операции введите число: ");
            int number = scanner.nextInt();

            switch (number) {
                case 0:
                    System.out.println("0. Вывести весь список товаров");
                    System.out.println("1. Отфильтровать список по цене ");
                    System.out.println("2. Отфильтровать список по названию ");
                    System.out.println("3. Отфильтровать список по стране происхождения ");
                    int numberFilter = scanner.nextInt();
                    switch (numberFilter) {
                        case 0:
                            service.printAvailableProducts(warehouseService.getProducts());
                            break;
                        case 1:
                            System.out.println("Введите нижний диапазон");
                            double minPrice = scanner.nextDouble();
                            System.out.println("Введите верхний диапазон");
                            double maxPrice = scanner.nextDouble();
                            service.printAvailableProducts(service.filterByPrice(minPrice, maxPrice));
                            break;
                        case 2:
                            System.out.println("Введите название: ");
                            String name = scanner.next();
                            service.printAvailableProducts(service.filterByName(name));
                            break;
                        case 3:
                            System.out.println("Введите название страны:");
                            String country = scanner.next();
                            service.printAvailableProducts(service.filterByCountry(country));
                            break;
                    }
                    break;
                case 1:
                    System.out.println();
                    System.out.print("Для добавления товара: введите ID товара: ");
                    long productId = scanner.nextLong();
                    System.out.print("Введите количество товара: ");
                    int amount = scanner.nextInt();
                    cart.addProduct(productId, amount);
                    break;
                case 2:
                    service.payment(cart);
                    id++;
                    cart = new Cart(id, warehouseService);
                    break;
                case 3:
                    warehouseService.printCart();
                    System.out.println("Для повторения заказа, введите его ID: ");
                    long cartId = scanner.nextLong();
                    cart = service.repeatOrder(cartId);
                    break;
                case 4:
                    warehouseService.printCart();
                    System.out.println("Для возврата заказа введите его ID: ");
                    cartId = scanner.nextLong();
                    service.returnOrder(cartId);
                    break;
                case 5:
                    System.out.println("Выход из приложения");
                    return;
                default:
                    System.out.println("Введена не верная команда");
                    break;
            }
        }


    }

    private static void warehouseFilling(WarehouseService warehouseService) {
        Drinks juice = new Drinks("Rich", 157.0, "Россия", 0.3, LocalDate.now(), СlassificationDrinks.JUICE);
        Drinks water1 = new Drinks("Снежка", 39.0, "Беларусь", 1.5, LocalDate.now(), СlassificationDrinks.STILL);
        Drinks water2 = new Drinks("Вода питьевая", 30.0, "Россия", 1, LocalDate.now(), СlassificationDrinks.SPARKLING_WATER);

        Fruits orange = new Fruits("Апельсин", 237, "Египет", 0.5, LocalDate.now(), "30 дней");
        Fruits apple = new Fruits("Яблоко", 140, "Турция", 1.5, LocalDate.now(), "45 дней");
        Fruits banana = new Fruits("Банан", 115, "Турция", 1.0, LocalDate.now(), "10 дней");

        warehouseService.addProduct(juice, 5);
        warehouseService.addProduct(water1, 9);
        warehouseService.addProduct(water2, 15);
        warehouseService.addProduct(orange, 12);
        warehouseService.addProduct(apple, 10);
        warehouseService.addProduct(banana, 10);

    }
}