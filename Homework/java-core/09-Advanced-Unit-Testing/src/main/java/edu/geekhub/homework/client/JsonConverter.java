package edu.geekhub.homework.client;

import edu.geekhub.homework.domain.LosesStatistic;

import java.util.ArrayList;
import java.util.List;

import static edu.geekhub.homework.util.NotImplementedException.TODO_TYPE;

/**
 * This class is responsible for conversion of {@link String} objects into a
 * {@link LosesStatistic} objects and otherwise
 */
public class JsonConverter {

    public List<LosesStatistic> convertToEntities(String losesStatisticsJson) {

        List<LosesStatistic> losesStatistics = new ArrayList<>();
        String[] splited = losesStatisticsJson.split("},");

        for (String oneEntity : splited) {
            losesStatistics.add(convertToEntity(oneEntity));
        }
        return losesStatistics;
    }

    public LosesStatistic convertToEntity(String losesStatisticJson) {
        losesStatisticJson=losesStatisticJson.replace("[", "");
        losesStatisticJson=losesStatisticJson.replace("]", "");
        losesStatisticJson=losesStatisticJson.replace("\"", "");
        losesStatisticJson=losesStatisticJson.replace("\\{","");
        losesStatisticJson=losesStatisticJson.replace("\\}","");

        String[] splited = losesStatisticJson.split(",");
        /*for (String one : splited) {
            System.out.println(one);
        }*/
        String[] finalSplited;
        ArrayList<Integer> values = new ArrayList();
        for (int i = 0; i < splited.length; i++) {
            finalSplited = splited[i].split(":");
            finalSplited[1]=finalSplited[1].replaceAll("[^0-9]","");
            values.add(Integer.parseInt(finalSplited[1]));
        }
        return new LosesStatistic(values.get(0), values.get(1), values.get(2), values.get(3), values.get(4)
                , values.get(5), values.get(6), values.get(7), values.get(8), values.get(9), values.get(10)
                , values.get(11), values.get(12), values.get(13));
    }

    public String convertToJson(LosesStatistic losesStatistic) {
        String json = "[{tanks\":\""+losesStatistic.tanks()+"\",\"armouredFightingVehicles\":\""
                +losesStatistic.armouredFightingVehicles()+"\",\"cannons\":\""+losesStatistic.cannons()
                +"\",\"multipleRocketLaunchers\":\""+losesStatistic.multipleRocketLaunchers()
                +"\",\"antiAirDefenseDevices\":\""+losesStatistic.antiAirDefenseDevices()
                +"\",\"planes\":\""+losesStatistic.planes()
                +"\",\"helicopters\":\""+losesStatistic.helicopters()
                +"\",\"drones\":\""+losesStatistic.drones()
                +"\",\"cruiseMissiles\":\""+losesStatistic.cruiseMissiles()
                +"\",\"shipsOrBoats\":\""+losesStatistic.shipsOrBoats()
                +"\",\"carsAndTankers\":\""+losesStatistic.carsAndTankers()
                +"\",\"specialEquipment\":\""+losesStatistic.specialEquipment()
                +"\",\"personnel\":\""+losesStatistic.personnel()
                +"\",\"id\":\""+losesStatistic.id()+"\"}]";
        return json;
    }


}
