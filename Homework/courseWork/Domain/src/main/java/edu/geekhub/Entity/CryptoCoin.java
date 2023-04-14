package edu.geekhub.Entity;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CryptoCoin {
    private int id;
    private String name;
    private HashMap<Date,Float> values;

    public CryptoCoin(int id, String name) {
        this.id = id;
        this.name = name;
        this.values = new HashMap<>();
    }

    public void addValue(Date date, Float value)
    {
        values.put(date,value);
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<Date, Float> getValues() {
        return values;
    }
}
