package edu.geekhub.Service;

import edu.geekhub.Repository.CryptoCoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;


@Component
public class CryptoCoinService {


    private final CryptoCoinRepository repository;

    public CryptoCoinService(CryptoCoinRepository repository) {
        this.repository = repository;
    }


    public void AddValues(HashMap<String, Float> values, LocalDateTime date){

       repository.addCoin(values,date);

    }
}
