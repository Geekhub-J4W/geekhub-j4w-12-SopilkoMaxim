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
    JsonConverter jsonConverter = new JsonConverter();
    LosesStatisticHttpClient losesStatisticHttpClient = new LosesStatisticHttpClient();
    public LosesStatisticService() {


    }

    public List<LosesStatistic> getAll() {
        try {
            return jsonConverter.convertToEntities(losesStatisticHttpClient.getAll());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public LosesStatistic getById(Integer id) {
        try {
            return jsonConverter.convertToEntity(losesStatisticHttpClient.getById(id));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteAll() {
        try {
            losesStatisticHttpClient.deleteAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(int id) {
        try {
            losesStatisticHttpClient.deleteById(id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void create(LosesStatistic losesStatistic) {

        try {
            losesStatisticHttpClient.create(jsonConverter.convertToJson(losesStatistic));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
