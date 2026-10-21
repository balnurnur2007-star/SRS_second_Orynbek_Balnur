package model;

/**
 * Простая проверка модели без внешних библиотек.
 */
public class MobileUsageValidationTest {
    public static void main(String[] args) {
        MobileUsage usage = new MobileUsage("12.10", "оқу күні", 1500, 6);

        assertEquals("12.10", usage.getDate(), "Дата должна сохраняться");
        assertEquals("оқу күні", usage.getDayType(), "Тип дня должен сохраняться");
        assertEquals(1500, usage.getMegabytes(), "Трафик должен сохраняться");
        assertEquals(6, usage.getWifiHours(), "Время Wi-Fi должно сохраняться");
        assertEquals(false, new MobileUsage("13.10", "демалыс", 2000, 8).isHeavyDay(), "2000 МБ не должны считаться высоким трафиком");
        assertEquals(true, new MobileUsage("14.10", "оқу күні", 2001, 4).isHeavyDay(), "Трафик выше 2000 МБ должен считаться высоким");

        expectInvalid(() -> new MobileUsage("12/10", "оқу күні", 1500, 6));
        expectInvalid(() -> new MobileUsage("", "оқу күні", 1500, 6));
        expectInvalid(() -> new MobileUsage("12.10", " ", 1500, 6));
        expectInvalid(() -> new MobileUsage("12.10", "демалыс", -1, 6));
        expectInvalid(() -> new MobileUsage("12.10", "демалыс", 100001, 6));
        expectInvalid(() -> new MobileUsage("12.10", "демалыс", 500, 25));

        System.out.println("Проверка MobileUsage успешно пройдена.");
    }

    private static void expectInvalid(Runnable action) {
        try {
            action.run();
            throw new AssertionError("Ожидалось исключение IllegalArgumentException.");
        } catch (IllegalArgumentException expected) {
            // Некорректное значение было отклонено, как и требуется.
        }
    }

    private static void assertEquals(Object expected, Object actual, String message) {
        if (!expected.equals(actual)) {
            throw new AssertionError(message + ": ожидалось " + expected + ", получено " + actual);
        }
    }
}
