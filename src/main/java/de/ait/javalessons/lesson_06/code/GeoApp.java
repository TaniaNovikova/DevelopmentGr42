package de.ait.javalessons.lesson_06.code;

public class GeoApp {

    private static java.util.List<String> countries = java.util.Arrays.asList("Germany", "France", "Brazil", "Argentina", "Canada", "China", "Australia", "India");
    private static java.util.List<String> cities = java.util.Arrays.asList("Oslo", "Berlin", "Buenos Aires", "Paris", "Los Angeles", "New York", "London", "Beijing");
    private static java.util.List<String> rivers = java.util.Arrays.asList("Amazon", "Nile", "Yangtze", "Mississippi", "Danube", "Main", "Ganges");
    private static java.util.List<String> continents = java.util.Arrays.asList("Europe", "Asia", "Africa", "Australia", "Antarctica", "South America", "North America");
    private static java.util.List<String> countriesNew = java.util.Arrays.asList("Mexico", "Sweden", "Brazil", "USA", "Canada", "France", "Norway");


    public static void main(String[] args) {
        java.util.List<String> resultCountriesWithC = countries.stream()
                .filter(c -> c.startsWith("C"))
                .collect(java.util.stream.Collectors.toList());
        System.out.println(resultCountriesWithC);

        java.util.List<String> resultCitiesMore6Letters = cities.stream()
                .filter(c -> c.length() > 6)
                .collect(java.util.stream.Collectors.toList());
        System.out.println(resultCitiesMore6Letters);

        java.util.List<String> riverResult = rivers.stream()
                .filter(river -> river.length() % 2 == 0)
                .collect(java.util.stream.Collectors.toList());
        System.out.println(riverResult);

        java.util.List<String> resultContinentsMore7Letters = continents.stream()
                .filter(continent -> continent.length() > 7)
                .collect(java.util.stream.Collectors.toList());
        System.out.println(resultContinentsMore7Letters);

        java.util.List<String> resultCountries6Letters = countriesNew.stream()
                .filter(country -> country.trim().length() == 6)
                .collect(java.util.stream.Collectors.toList());
        System.out.println(resultCountries6Letters);


        java.util.List<String> countriesContainingTheLetterA = countriesNew.stream()
                .filter(country -> country.toLowerCase().contains("a")).collect(java.util.stream.Collectors.toList());
        System.out.println(countriesContainingTheLetterA);


        java.util.List<String> citiesWhoseNamesEndWithO = cities.stream()
                .filter(city -> city.endsWith("o")).collect(java.util.stream.Collectors.toList());
        System.out.println(citiesWhoseNamesEndWithO);

        java.util.List<String> riversWhoseNamesContainMoreThan7Letters = rivers.stream()
                .filter(river -> river.length() > 7)
                .collect(java.util.stream.Collectors.toList());
        System.out.println(riversWhoseNamesContainMoreThan7Letters);

        java.util.List<String> continentsWhoseNamesStartWithA = continents.stream()
                .filter(continent -> continent.startsWith("A"))
                .collect(java.util.stream.Collectors.toList());
        System.out.println(continentsWhoseNamesStartWithA);


    }
}
