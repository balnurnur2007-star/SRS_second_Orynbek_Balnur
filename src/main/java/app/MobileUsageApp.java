package app;

import model.MobileUsage;
import service.MobileUsageService;

import java.util.Scanner;

/**
 * Точка входа в консольное приложение MobileUsage.
 */
public class MobileUsageApp {
    private final MobileUsageService service = new MobileUsageService();
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new MobileUsageApp().run();
    }

    private void run() {
        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addUsage();
                    break;
                case "2":
                    service.printAll();
                    break;
                case "3":
                    service.printHeavyDays();
                    break;
                case "4":
                    addDemoData();
                    break;
                case "5":
                    printByCategory();
                    break;
                case "6":
                    service.printSummary();
                    break;
                case "0":
                    running = false;
                    System.out.println("Программа завершена.");
                    break;
                default:
                    System.out.println("Выберите пункт от 0 до 6.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== MobileUsage ===");
        System.out.println("1. Добавить запись");
        System.out.println("2. Показать все записи");
        System.out.println("3. Показать дни с высоким трафиком");
        System.out.println("4. Добавить демонстрационные данные");
        System.out.println("5. Фильтровать по типу дня");
        System.out.println("6. Показать итоговую статистику");
        System.out.println("0. Выход");
        System.out.print("Ваш выбор: ");
    }

    private void addUsage() {
        try {
            System.out.print("Дата (ДД.ММ): ");
            String date = scanner.nextLine();
            System.out.print("Тип дня (например, оқу күні или демалыс): ");
            String dayType = scanner.nextLine();
            System.out.print("Мобильный трафик (МБ): ");
            int megabytes = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Время в Wi-Fi (часы): ");
            int wifiHours = Integer.parseInt(scanner.nextLine().trim());

            service.addUsage(new MobileUsage(date, dayType, megabytes, wifiHours));
            System.out.println("Запись добавлена.");
        } catch (NumberFormatException exception) {
            System.out.println("Трафик и время Wi-Fi нужно вводить целыми числами.");
        } catch (IllegalArgumentException exception) {
            System.out.println("Ошибка: " + exception.getMessage());
        }
    }

    private void addDemoData() {
        service.addUsage(new MobileUsage("12.10", "оқу күні", 1500, 6));
        service.addUsage(new MobileUsage("13.10", "демалыс", 2450, 8));
        service.addUsage(new MobileUsage("14.10", "оқу күні", 800, 4));
        System.out.println("Добавлены 3 демонстрационные записи.");
    }

    private void printByCategory() {
        System.out.print("Введите тип дня: ");
        try {
            service.printByCategory(scanner.nextLine());
        } catch (IllegalArgumentException exception) {
            System.out.println("Ошибка: " + exception.getMessage());
        }
    }
}
