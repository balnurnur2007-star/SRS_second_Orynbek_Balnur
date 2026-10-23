package service;

import model.MobileUsage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Хранит записи об использовании интернета и выводит их по условиям.
 */
public class MobileUsageService {
    private final List<MobileUsage> usages = new ArrayList<>();

    public void addUsage(MobileUsage usage) {
        if (usage == null) {
            throw new IllegalArgumentException("Запись не должна быть null.");
        }
        usages.add(usage);
    }

    public List<MobileUsage> getAllUsages() {
        return Collections.unmodifiableList(usages);
    }

    public List<MobileUsage> getHeavyDays() {
        List<MobileUsage> heavyDays = new ArrayList<>();
        for (MobileUsage usage : usages) {
            if (usage.isHeavyDay()) {
                heavyDays.add(usage);
            }
        }
        return heavyDays;
    }

    public int getTotalMegabytes() {
        int totalMegabytes = 0;
        for (MobileUsage usage : usages) {
            totalMegabytes += usage.getMegabytes();
        }
        return totalMegabytes;
    }

    public int getTotalWifiHours() {
        int totalWifiHours = 0;
        for (MobileUsage usage : usages) {
            totalWifiHours += usage.getWifiHours();
        }
        return totalWifiHours;
    }

    public List<MobileUsage> getByCategory(String dayType) {
        if (dayType == null || dayType.trim().isEmpty()) {
            throw new IllegalArgumentException("Тип дня не должен быть пустым.");
        }

        List<MobileUsage> filteredUsages = new ArrayList<>();
        for (MobileUsage usage : usages) {
            if (usage.getDayType().equalsIgnoreCase(dayType.trim())) {
                filteredUsages.add(usage);
            }
        }
        return filteredUsages;
    }

    /**
     * Выводит на экран записи выбранного типа дня.
     * Это соответствует пункту 5 меню задания.
     */
    public void printByCategory(String dayType) {
        List<MobileUsage> filteredUsages = getByCategory(dayType);

        if (filteredUsages.isEmpty()) {
            System.out.println("Записи с типом дня «" + dayType.trim() + "» не найдены.");
            return;
        }

        System.out.println("Записи с типом дня «" + dayType.trim() + "»:");
        printUsages(filteredUsages);
    }

    public void printAll() {
        if (usages.isEmpty()) {
            System.out.println("Список записей пока пуст.");
            return;
        }
        printUsages(usages);
    }

    public void printHeavyDays() {
        List<MobileUsage> heavyDays = getHeavyDays();
        if (heavyDays.isEmpty()) {
            System.out.println("Дней с высоким расходом трафика нет.");
            return;
        }

        System.out.println("Дни с трафиком больше 2000 МБ:");
        printUsages(heavyDays);
    }

    public void printSummary() {
        System.out.println("Количество записей: " + usages.size());
        System.out.println("Всего мобильного трафика: " + getTotalMegabytes() + " МБ");
        System.out.println("Всего времени в Wi-Fi: " + getTotalWifiHours() + " ч.");
        System.out.println("Дней с высоким трафиком: " + getHeavyDays().size());
    }

    private void printUsages(List<MobileUsage> entries) {
        for (MobileUsage usage : entries) {
            System.out.println(usage);
        }
    }
}
