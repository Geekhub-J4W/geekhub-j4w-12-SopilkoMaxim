package org.example.hw;

public class Starter {
    public static void main(String[] args) {
        Exercises exercises = new Exercises();

        System.out.println("Country cities count:\n"+exercises.getCountryCitiesCount());
        System.out.println("Total population:\n"+ exercises.totalPopulation());
        System.out.println("Min population:\n"+exercises.minPopulatedCity());
        System.out.println("Max population city:\n"+exercises.mostPopulatedCity());
        System.out.println("Population of each country:\n"+exercises.populationOfEachCountry());
        System.out.println("Min population country:\n"+exercises.minPopulatedCountry());
        System.out.println("Max population country:\n"+exercises.mostPopulatedCountry());
        System.out.println("Population of specific country AFG:\n"+exercises.populationOfSpecificCountry("AFG"));
        System.out.println("Info of specific city Kabul:\n"+exercises.specificCy("Kabul"));
    }
}
