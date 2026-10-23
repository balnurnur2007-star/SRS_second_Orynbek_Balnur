package service;

import model.MobileUsage;

import java.util.List;

/** Простая проверка фильтрации по типу дня. */
public class MobileUsageServiceTest {
    public static void main(String[] args) {
        MobileUsageService service = new MobileUsageService();
        service.addUsage(new MobileUsage("12.10", "оқу күні", 1500, 6));
        service.addUsage(new MobileUsage("13.10", "демалыс", 2450, 8));
        service.addUsage(new MobileUsage("14.10", "оқу күні", 800, 4));

        List<MobileUsage> studyDays = service.getByCategory("ОҚУ КҮНІ");
        if (studyDays.size() != 2) {
            throw new AssertionError("Должны быть найдены две записи типа «оқу күні».");
        }
        if (service.getHeavyDays().size() != 1) {
            throw new AssertionError("Должен быть найден один день с высоким трафиком.");
        }

        System.out.println("Проверка фильтрации по типу дня успешно пройдена.");
    }
}
