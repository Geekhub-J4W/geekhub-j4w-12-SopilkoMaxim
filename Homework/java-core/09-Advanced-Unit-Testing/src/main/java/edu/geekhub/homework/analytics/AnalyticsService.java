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

        return losesStatistics.stream()
                .max(Comparator.comparing(LosesStatistic::toral))
                .get();
    }

    public LosesStatistic findStatisticWithMinLosesAmounts(List<LosesStatistic> losesStatistics) {

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
