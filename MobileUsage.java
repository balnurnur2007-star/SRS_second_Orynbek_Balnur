package model;

/**
 * Одна запись об использовании мобильного интернета и Wi-Fi за день.
 */
public class MobileUsage {
    private String date;
    private String dayType;
    private int megabytes;
    private int wifiHours;

    /**
     * Создаёт запись и проверяет все переданные значения.
     *
     * @param date дата в формате ДД.ММ
     * @param dayType тип дня, например «оқу күні» или «демалыс»
     * @param megabytes объём мобильного трафика в МБ, от 0 до 100000
     * @param wifiHours время в Wi-Fi в часах, от 0 до 24
     */
    public MobileUsage(String date, String dayType, int megabytes, int wifiHours) {
        setDate(date);
        setDayType(dayType);
        setMegabytes(megabytes);
        setWifiHours(wifiHours);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        if (date == null || !date.matches("(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[0-2])")) {
            throw new IllegalArgumentException("Дата должна иметь формат ДД.ММ, например 12.10.");
        }
        this.date = date;
    }

    public String getDayType() {
        return dayType;
    }

    public void setDayType(String dayType) {
        if (dayType == null || dayType.trim().isEmpty()) {
            throw new IllegalArgumentException("Тип дня не должен быть пустым.");
        }
        this.dayType = dayType.trim();
    }

    public int getMegabytes() {
        return megabytes;
    }

    public void setMegabytes(int megabytes) {
        if (megabytes < 0 || megabytes > 100000) {
            throw new IllegalArgumentException("Мобильный трафик должен быть в диапазоне от 0 до 100000 МБ.");
        }
        this.megabytes = megabytes;
    }

    public int getWifiHours() {
        return wifiHours;
    }

    public void setWifiHours(int wifiHours) {
        if (wifiHours < 0 || wifiHours > 24) {
            throw new IllegalArgumentException("Время Wi-Fi должно быть в диапазоне от 0 до 24 часов.");
        }
        this.wifiHours = wifiHours;
    }

    public boolean isHeavyDay() {
        return megabytes > 2000;
    }

    @Override
    public String toString() {
        return "Дата: " + date
                + ", тип дня: " + dayType
                + ", мобильный трафик: " + megabytes + " МБ"
                + ", Wi-Fi: " + wifiHours + " ч.";
    }
}
