package edu.geekhub.Service;

import edu.geekhub.Repository.CryptoCoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


@Component
public class CryptoCoinService {


    private final CryptoCoinRepository repository;

    public CryptoCoinService(CryptoCoinRepository repository) {
        this.repository = repository;
    }


    public void AddValues(HashMap<String, Float> values, LocalDateTime date) {

        repository.addCoin(values, date);

    }

    public Map<Date, Float> returnListBts() {
        return repository.listOfBtc();
    }

    public Map<String, Float> getByName(String name) {
        Map<Date, Float> data = repository.getByName(name);
        Map<String, Float> formattedData = new LinkedHashMap<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (Date date : data.keySet()) {
            formattedData.put(sdf.format(date), data.get(date));
        }
        return formattedData;
    }
}
