package org.example.hw;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.stream.Collectors;

public class Exercises {
    private static final Cities citiesRepo = new Cities();


    public Map<String, Long> getCountryCitiesCount() {
        // Find the number of cities of each country (use grouping)
        var cities = citiesRepo.getAllCities();
        return cities.values().stream()
                .collect(Collectors.groupingBy(City::getCountryCode,
                                Collectors.counting()
                        )
                );
    }

    public City mostPopulatedCity() {
        var cities = citiesRepo.getAllCities();

        return cities.values().stream()
                .max(Comparator.comparing(City::getPopulation))
                .get();
    }

    public City minPopulatedCity() {
        var cities = citiesRepo.getAllCities();

        return cities.values().stream()
                .min(Comparator.comparing(City::getPopulation))
                .get();

    }

    public String mostPopulatedCountry() {
        var cities = citiesRepo.getAllCities();

        return cities.values().stream()
                .collect(Collectors.groupingBy(City::getCountryCode, Collectors.summingInt(City::getPopulation)))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();
    }

    public String minPopulatedCountry() {
        var cities = citiesRepo.getAllCities();

        return cities.values().stream()
                .collect(Collectors.groupingBy(City::getCountryCode, Collectors.summingInt(City::getPopulation)))
                .entrySet()
                .stream()
                .min(Map.Entry.comparingByValue())
                .get()
                .getKey();

    }

    public Long totalPopulation() {
        var cities = citiesRepo.getAllCities();

        return cities.values().stream()
                .collect(Collectors.summarizingLong(City::getPopulation))
                .getSum();

    }

    public Map<String,Integer> populationOfEachCountry() {
        var cities = citiesRepo.getAllCities();

        return cities.values().stream()
                .collect(Collectors.groupingBy(City::getCountryCode, Collectors.summingInt(City::getPopulation)));
    }

    public Integer populationOfSpecificCountry(String countryCode) {
        var cities = citiesRepo.getAllCities();

        return cities.values().stream()
                .collect(Collectors.groupingBy(City::getCountryCode, Collectors.summingInt(City::getPopulation)))
                .entrySet()
                .stream()
                .filter(e -> e.getKey().equals(countryCode))
                .findAny()
                .get()
                .getValue();


    }

    public City specificCy(String city) {
        var cities = citiesRepo.getAllCities();

        return cities.values().stream()
                .filter(e -> e.getName().equals(city))
                .findAny()
                .get();

    }

}
