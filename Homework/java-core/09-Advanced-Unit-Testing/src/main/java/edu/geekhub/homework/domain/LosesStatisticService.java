package edu.geekhub.homework.domain;

import edu.geekhub.homework.client.JsonConverter;
import edu.geekhub.homework.client.LosesStatisticHttpClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static edu.geekhub.homework.util.NotImplementedException.TODO;
import static edu.geekhub.homework.util.NotImplementedException.TODO_TYPE;

/**
 * Service should fetch loses statistic data as a {@link String} object, then convert it into a
 * {@link LosesStatistic} by using {@link } class and return a result if possible.
 * <br/>
 * <br/>
 * IMPORTANT: DO NOT CHANGE METHODS SIGNATURE
 */
public class LosesStatisticService {


    public List<LosesStatistic> losesStatistics = new ArrayList();
    public LosesStatisticService() {
        JsonConverter jsonConverter = new JsonConverter();
        LosesStatisticHttpClient losesStatisticHttpClient = new LosesStatisticHttpClient();
        try {
            losesStatistics = jsonConverter.convertToEntities(losesStatisticHttpClient.getAll());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<LosesStatistic> getAll() {
        return losesStatistics;
    }

    public LosesStatistic getById(Integer id) {
        return losesStatistics.get(id);
    }

    public void deleteAll() {
        losesStatistics = null;
    }

    public void deleteById(int id) {

        losesStatistics.remove(id);
    }

    public void create(LosesStatistic losesStatistic) {
        losesStatistics.add(losesStatistic);
    }
}
