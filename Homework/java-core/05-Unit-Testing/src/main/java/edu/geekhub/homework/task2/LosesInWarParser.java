package edu.geekhub.homework.task2;

import static edu.geekhub.homework.util.NotImplementedException.TODO_TYPE;

public class LosesInWarParser {

    LosesStatistic parseLosesStatistic(String input) {
        validate(input);
        String[] lines = input.split("\n");
        int[] numbers = new int[lines.length];
        for (int i = 0; i < lines.length; i++) {
            String[] splitedLine = lines[i].split(" — ");
            numbers[i] = Integer.parseInt(splitedLine[1]);
        }
        LosesStatistic parsed = LosesStatistic.newStatistic()
                .withTanks(numbers[0])
                .withArmouredFightingVehicles(numbers[1])
                .withCannons(numbers[2])
                .withMultipleRocketLaunchers(numbers[3])
                .withAntiAirDefenseDevices(numbers[4])
                .withPlanes(numbers[5])
                .withHelicopters(numbers[6])
                .withDrones(numbers[7])
                .withCruiseMissiles(numbers[8])
                .withShipsOrBoats(numbers[9])
                .withCarsAndTankers(numbers[10])
                .withSpecialEquipment(numbers[11])
                .withPersonnel(numbers[12])
                .build();
        return parsed;

        //return TODO_TYPE();
    }

    private void validate(String input) {
        validateIsEmpty(input);
        validateIsNull(input);
    }

    private void validateIsEmpty(String input) {
        if (input.isBlank()) {
            throw new NullPointerException("Input data is empty");
        }
    }

    private void validateIsNull(String input) {
        if (input == null) {
            throw new NullPointerException("Input data is NULL");
        }
    }

}
