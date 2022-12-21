package edu.geekhub.homework.domain;

import edu.geekhub.homework.client.JsonConverter;
import edu.geekhub.homework.client.LosesStatisticHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ApplicationStarterTest {


    @Mock
    private LosesStatisticService losesStatisticService;
    @Captor
    private ArgumentCaptor<LosesStatistic> losesStatisticCaptor;

    @Captor
    private ArgumentCaptor<Integer> idCaptor;

    private JsonConverter jsonConverter;
    private List<LosesStatistic> example;

    LosesStatistic exampleLoses;

    @BeforeEach
    void setUp() {
        jsonConverter = new JsonConverter();
        example = new ArrayList<>();
        example.add(0, new LosesStatistic(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14));
        exampleLoses = new LosesStatistic(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
    }

    @Test
    void tryToGetAll() {
        when(losesStatisticService.getAll()).thenReturn(example);

        List<LosesStatistic> all = losesStatisticService.getAll();

        assertEquals(14, all.get(0).id());

    }

    @Test
    void tryGetById() {
        when(losesStatisticService.getById(0)).thenReturn(exampleLoses);

        LosesStatistic loses = losesStatisticService.getById(0);

        assertEquals(15, loses.id());
    }

    @Test
    void tryToCreate() {
        losesStatisticService.create(new LosesStatistic(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15));
        verify(losesStatisticService).create(losesStatisticCaptor.capture());

        LosesStatistic captured = losesStatisticCaptor.getValue();

        assertThat(captured).extracting(LosesStatistic::id).isEqualTo(15);
    }

    @Test
    void tryToDeleteById(){
        losesStatisticService.deleteById(1);
        verify(losesStatisticService).deleteById(idCaptor.capture());

        assertEquals(1,idCaptor.getValue());

        }

}