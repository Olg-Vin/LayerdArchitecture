package org.vinio.presentation;

import org.vinio.application.InventoryService;
import org.vinio.domain.Product;
import org.vinio.domain.TemperatureMode;
import org.vinio.infrastructure.InMemoryInventoryRepository;

import java.time.LocalDate;
import java.util.Scanner;

public class ConsoleApp {
    public static void main(String[] args) {
        InventoryService service = new InventoryService(new InMemoryInventoryRepository());
        Scanner scanner = new Scanner(System.in);
        seedTestProducts(service);

        while (true) {
            System.out.println("1. Добавить продукт");
            System.out.println("2. Использовать продукт");
            System.out.println("3. Списать просроченные продукты");
            System.out.println("4. Показать продукты ниже критического уровня");
            System.out.println("5. Все продукты");
            System.out.println("0. Выход");
            System.out.print("Выбор: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Название: ");
                    String name = scanner.nextLine();
                    System.out.print("Количество: ");
                    int quantity = scanner.nextInt();
                    System.out.print("Срок годности (yyyy-mm-dd): ");
                    LocalDate expiry = LocalDate.parse(scanner.next());
                    System.out.print("Темп. режим (FROZEN/CHILLED/ROOM_TEMPERATURE): ");
                    TemperatureMode mode = TemperatureMode.valueOf(scanner.next());
                    System.out.print("Мин. запас: ");
                    int min = scanner.nextInt();
                    System.out.print("Оптим. запас: ");
                    int opt = scanner.nextInt();
                    System.out.print("Крит. уровень: ");
                    int crit = scanner.nextInt();

                    Product p = new Product(id, name, quantity, expiry, mode, min, opt, crit);
                    service.addProduct(p);
                    break;

                case 2:
                    System.out.print("ID продукта: ");
                    String pid = scanner.nextLine();
                    System.out.print("Сколько использовать: ");
                    int q = scanner.nextInt();
                    service.useProduct(pid, q);
                    break;

                case 3:
                    service.writeOffExpired();
                    System.out.println("Просроченные продукты списаны.");
                    break;

                case 4:
                    service.getCriticalProducts().forEach(prod ->
                            System.out.println(prod.getName() + " | Остаток: " + prod.getQuantity()));
                    break;

                case 5:
                    service.getAllProducts().forEach(prod ->
                            System.out.println(prod.getName() + " | Кол-во: " + prod.getQuantity()));
                    break;

                case 0:
                    return;
            }
        }
    }
    private static void seedTestProducts(InventoryService service) {
        service.addProduct(new Product("P001", "Картофель", 50,
                LocalDate.now().plusDays(10), TemperatureMode.ROOM_TEMPERATURE,
                20, 70, 15));

        service.addProduct(new Product("P002", "Котлета", 30,
                LocalDate.now().plusDays(5), TemperatureMode.CHILLED,
                15, 50, 10));

        service.addProduct(new Product("P003", "Салат", 10,
                LocalDate.now().minusDays(1), TemperatureMode.CHILLED,
                5, 30, 5)); // просрочен

        service.addProduct(new Product("P004", "Булочка", 80,
                LocalDate.now().plusDays(2), TemperatureMode.ROOM_TEMPERATURE,
                30, 100, 20));
    }
}

