package edu.geekhub.homework.task2;

import java.util.regex.Pattern;

import static edu.geekhub.homework.util.NotImplementedException.TODO_TYPE;

public class LosesInWarParser {

    LosesStatistic parseLosesStatistic(String input) {

        if (input.contains("Танки") && input.contains("ББМ") && input.contains("Гармати") && input.contains("РСЗВ") &&
                input.contains("Засоби ППО") && input.contains("Літаки")&& input.contains("Гелікоптери") &&
                input.contains("БПЛА") && input.contains("Крилаті ракети")&& input.contains("Кораблі (катери)")&&
                input.contains("Автомобілі та автоцистерни")&&input.contains("Спеціальна техніка")&&input.contains("Особовий склад"))
        {
            validate(input);
            String[] lines = input.split("\n");
            int[] numbers = new int[lines.length];
            for (int i = 0; i < lines.length; i++)
            {
                String[] splitedLine = lines[i].split(" — ");
                splitedLine[1]=splitedLine[1].replaceAll("[^0-9]","");
                if(splitedLine[1].isBlank())
                    numbers[i] = 0;
                else
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
        }
        else
            return LosesStatistic.empty();

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
        if (input.equals(null)) {
            throw new NullPointerException("Input data is NULL");
        }
    }

}
