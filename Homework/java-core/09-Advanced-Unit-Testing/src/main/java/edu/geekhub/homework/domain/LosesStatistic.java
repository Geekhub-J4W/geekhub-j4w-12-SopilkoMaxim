package edu.geekhub.homework.domain;

import edu.geekhub.homework.client.LosesStatisticHttpClient;

import java.util.Objects;

import static edu.geekhub.homework.util.NotImplementedException.TODO;
import static edu.geekhub.homework.util.NotImplementedException.TODO_TYPE;

/**
 * This class should contain data received through {@link LosesStatisticHttpClient}
 * via <a href="https://en.wikipedia.org/wiki/JSON">JSON</a> String
 */
public record LosesStatistic(int tanks,int armouredFightingVehicles,int cannons, int multipleRocketLaunchers
                ,int antiAirDefenseDevices,int planes,int helicopters,int drones,int cruiseMissiles
                ,int shipsOrBoats,int carsAndTankers,int specialEquipment,int personnel, int id
                ) {

    public LosesStatistic {
        Objects.requireNonNull(tanks);
        Objects.requireNonNull(armouredFightingVehicles);
        Objects.requireNonNull(cannons);
        Objects.requireNonNull(multipleRocketLaunchers);
        Objects.requireNonNull(antiAirDefenseDevices);
        Objects.requireNonNull(planes);
        Objects.requireNonNull(helicopters);
        Objects.requireNonNull(drones);
        Objects.requireNonNull(cruiseMissiles);
        Objects.requireNonNull(shipsOrBoats);
        Objects.requireNonNull(carsAndTankers);
        Objects.requireNonNull(specialEquipment);
        Objects.requireNonNull(personnel);
        Objects.requireNonNull(id);

    }

    public int id() {
        return id;
    }

    public int toral(){
        return tanks+armouredFightingVehicles+cannons+multipleRocketLaunchers+antiAirDefenseDevices
                +planes+helicopters+drones+cruiseMissiles+shipsOrBoats+carsAndTankers+specialEquipment+personnel;
    }
}
