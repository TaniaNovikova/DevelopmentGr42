package de.ait.javalessons.lesson_06.code;

public class WeatherTestData {
    public static java.util.List<Weather> getWeatherList() {
        return java.util.Arrays.asList(
                new Weather("New York", 10.5, false),
                new Weather("Los Angeles", 18.2, false),
                new Weather("Chicago", -2.0, true),
                new Weather("Houston", 25.0, false),
                new Weather("Miami", 30.5, true),
                new Weather("Paris", 12.0, false),
                new Weather("London", 7.5, true),
                new Weather("Berlin", 3.0, true),
                new Weather("Sydney", 22.3, false),
                new Weather("Tokyo", 15.6, true)
        );
    }
}
