package de.ait.javalessons.streamapi;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.LinkedList;

public class Homework05 {
    public static List<String> countries = Arrays.asList("Germany", "France", "Brazil", "Argentina", "Canada", "China", "Australia", "India");
    public static List<String> cities = Arrays.asList("Berlin", "Buenos Aires", "Paris", "Los Angeles", "New York", "London", "Beijing");
    public static List<String> cities1 = Arrays.asList("Berlin", "Oslo", "Buenos Aires", "Paris", "Los Angeles", "New York", "London", "Beijing");
    public static List<String> rivers = Arrays.asList("Amazon", "Nile", "Yangtze", "Mississippi", "Danube", "Main", "Ganges");
    public static List<String> continents = Arrays.asList("Europe", "Asia", "Africa", "Australia", "Antarctica", "South America", "North America");
    public static List<String> continents1 = Arrays.asList("Europe", "Asia", "South America", "North America");
    public static List<String> countries1 = Arrays.asList("Mexico", "Sweden", "Brazil", "USA", "Canada", "France", "Norway");
    public static List<String> countries2 = Arrays.asList("Mexico", "Sweden", "Egypt");

    public static void main(String[] args) {
         // System.out.println(getCountriesWhoseNamesStartWithTheLetterC());
         //System.out.println(getRiversWhoseNamesHaveAnEvenNumberOfLetters());
         //System.out.println(getContinentsWhoseNamesAreShorterThan7Characters());
         //System.out.println(getCitiesWhoseNamesAreLongerThan6Characters());
         //System.out.println(getCountriesWhoseNamesConsistOf6Letters());
        // System.out.println(getCountriesContainingTheLetterA(countries));
        // System.out.println(getCountriesContainingTheLetterA(countries1));
        //  System.out.println(getCountriesContainingTheLetterA(countries2));
        // System.out.println(getCitiesWhoseNamesEndWithO(cities1));
        // System.out.println(getRiversWhoseNamesContainMoreThan7Letters(rivers));
       // System.out.println(getContinentsWhoseNamesStartWithA(continents1));




         LinkedList<String> stringLinkedList = new LinkedList<>();
        stringLinkedList.add("Germany");
        stringLinkedList.add("France");
        stringLinkedList.add("Brazil");
        stringLinkedList.add("Argentina");
        stringLinkedList.add("Canada");
        stringLinkedList.add("China");
        stringLinkedList.add("Australia");

        System.out.println(stringLinkedList);
        //Using Stream API, filter the countries whose names start with the letter "C".
       LinkedList<String> stringLinkedList1 = stringLinkedList.stream()
               .filter(c->c.startsWith("C"))
               .collect(Collectors.toCollection(LinkedList::new));
        System.out.println(stringLinkedList1);


    }

    //1. Filtering Countries by First Letter
    public static List<String> getCountriesWhoseNamesStartWithTheLetterC() {
        List<String> countriesWhoseNamesStartWithTheLetterC = countries.stream()
                .filter(country -> country.charAt(0) == 'C').collect(Collectors.toList());
        return countriesWhoseNamesStartWithTheLetterC;
    }

    //2. Filtering Cities by Name Length
    //Using **Stream API**, filter the cities whose names are longer than **6 characters**.
    public static List<String> getCitiesWhoseNamesAreLongerThan6Characters() {
        List<String> citiesWhoseNamesAreLongerThan6Characters = cities.stream()
                .filter(city -> city.length() > 6).collect(Collectors.toList());
        return citiesWhoseNamesAreLongerThan6Characters;
    }

    // 3. Filtering Rivers by Even Letter Count
    //Using **Stream API**, filter only the rivers whose names have an **even number of letters**.

    public static List<String> getRiversWhoseNamesHaveAnEvenNumberOfLetters() {
        List<String> riversWhoseNamesHaveAnEvenNumberOfLetters = rivers.stream()
                .filter(river -> river.length() % 2 == 0).collect(Collectors.toList());
        return riversWhoseNamesHaveAnEvenNumberOfLetters;
    }

    //4. Filtering Continents by Name Length
//Using **Stream API**, filter the continents whose **names are shorter than 7 characters**.
    public static List<String> getContinentsWhoseNamesAreShorterThan7Characters() {
        List<String> continentsWhoseNamesAreShorterThan7Characters = continents.stream()
                .filter(continent -> continent.length() < 7).collect(Collectors.toList());
        return continentsWhoseNamesAreShorterThan7Characters;
    }

    //5. Filtering Countries with Six-Letter Names
    // Using **Stream API**, **filter** the countries whose names consist of **6 letters**.
    public static List<String> getCountriesWhoseNamesConsistOf6Letters() {
        List<String> countriesWhoseNamesConsistOf6Letters = countries1.stream()
                .filter(country -> country.length() == 6).collect(Collectors.toList());
        return countriesWhoseNamesConsistOf6Letters;
    }

    // 6. Finding Countries Containing the Letter "a"
    public static List<String> getCountriesContainingTheLetterA(List<String> countries) {
        List<String> countriesContainingTheLetterA = countries.stream()
                .filter(country -> country.toLowerCase().contains("a")).collect(Collectors.toList());
        return countriesContainingTheLetterA;
    }

    /*7. Filtering Cities by Last Letter
    Using **Stream API**, filter the cities whose names **end with "o"**.*/
    public static List<String> getCitiesWhoseNamesEndWithO(List<String> cities) {
        List<String> citiesWhoseNamesEndWithO = cities.stream()
                .filter(city -> city.endsWith("o")).collect(Collectors.toList());
        return citiesWhoseNamesEndWithO;
    }

    /*8. Finding Rivers with More Than 7 Letters
    Using **Stream API**, filter the rivers whose names **contain more than 7 letters**.*/
    public static List<String> getRiversWhoseNamesContainMoreThan7Letters(List<String> rivers) {
        List<String> riversWhoseNamesContainMoreThan7Letters = rivers.stream()
                .filter(river -> river.length() > 7).collect(Collectors.toList());
        return riversWhoseNamesContainMoreThan7Letters;
    }

    /*9. Filtering Continents by First Letter
    Using **Stream API**, filter the continents whose names **start with "A"**.*/
    public static List<String> getContinentsWhoseNamesStartWithA(List<String> continents) {
        List<String> continentsWhoseNamesStartWithA = continents.stream()
                .filter(continent -> continent.startsWith("A")).collect(Collectors.toList());
        return continentsWhoseNamesStartWithA;
    }



}