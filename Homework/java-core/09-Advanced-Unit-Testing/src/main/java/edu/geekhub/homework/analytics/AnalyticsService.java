package edu.geekhub.homework.analytics;

import edu.geekhub.homework.domain.LosesStatistic;
import edu.geekhub.homework.domain.LosesStatisticService;

import java.util.*;
import java.util.stream.Collectors;

import static edu.geekhub.homework.util.NotImplementedException.TODO;
import static edu.geekhub.homework.util.NotImplementedException.TODO_TYPE;

/**
 * Service shows interesting analytics information
 */
public class AnalyticsService {

    public AnalyticsService() {

    }

    public LosesStatistic findStatisticWithMaxLosesAmounts(List<LosesStatistic> losesStatistics) {
        /*List <Integer> totalLost = new ArrayList<>();
        for (LosesStatistic each: losesStatistics) {
            totalLost.add(each.id(),each.toral());
        }
        int idMaxLoses = totalLost.indexOf(Collections.max(totalLost));
        return losesStatistics.get(idMaxLoses);*/
        return losesStatistics.stream()
                .max(Comparator.comparing(LosesStatistic::toral))
                .get();
    }

    public LosesStatistic findStatisticWithMinLosesAmounts(List<LosesStatistic> losesStatistics) {
        /*List <Integer> totalLost = new ArrayList<>();
        for (LosesStatistic each: losesStatistics) {
            totalLost.add(each.id(),each.toral());
        }
        int idMinLoses = totalLost.indexOf(Collections.min(totalLost));
        return losesStatistics.get(idMinLoses);*/
        return losesStatistics.stream()
                .min(Comparator.comparing(LosesStatistic::toral))
                .get();
    }

    public int totalCountOfLosesForStatistic(LosesStatistic losesStatistic) {
        return losesStatistic.toral();
    }

    public int totalCountOfLosesForAllStatistics(List<LosesStatistic> losesStatistics) {
        return losesStatistics.stream().
                collect(Collectors.summingInt(LosesStatistic::toral));
    }

}
