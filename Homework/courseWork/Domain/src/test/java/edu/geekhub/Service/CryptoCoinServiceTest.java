package edu.geekhub.Service;

import edu.geekhub.Repository.CryptoCoinRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Date;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CryptoCoinServiceTest {

    private CryptoCoinRepository repository;
    private CryptoCoinService service;

    @BeforeEach
    public void setUp() {
        repository = mock(CryptoCoinRepository.class);
        service = new CryptoCoinService(repository);
    }

    @Test
    public void testAddValues() {
        HashMap<String, Float> values = new HashMap<>();
        values.put("BTC", 50000.0f);
        LocalDateTime date = LocalDateTime.now();

        service.AddValues(values, date);

        verify(repository).addCoin(values, date);
    }

    @Test
    public void testGetByName() {
        String name = "BTC";
        Map<Date, Float> data = new HashMap<>();
        LocalDateTime date = LocalDateTime.of(2023, 5, 12, 12, 0);
        data.put(Date.from(date.atZone(ZoneId.systemDefault()).toInstant()), 50000.0f);
        when(repository.getByName(name)).thenReturn(data);

        Map<String, Float> expectedData = new LinkedHashMap<>();
        expectedData.put("2023-05-12 12:00", 50000.0f);
        Map<String, Float> actualData = service.getByName(name);

        assertEquals(expectedData, actualData);
    }


    @Test
    public void testGetLastPriceByName() {
        String name = "BTC";
        float expectedPrice = 50000.0f;
        when(repository.getLastPriceByName(name)).thenReturn(expectedPrice);

        float actualPrice = service.getLastPriceByName(name);

        assertEquals(expectedPrice, actualPrice);
    }
}
