package edu.geekhub.homework.inject;

import edu.geekhub.homework.Values;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class InjectProcessor {

    public Object process(Object object) throws IllegalAccessException, InstantiationException {
        ArrayList<Values> valuesFromFile = FileReaderConverter.convertFileToEntity();
        System.out.println("Values from file is:");
        for (int i = 0; i < valuesFromFile.size(); i++) {
            System.out.println(valuesFromFile.get(i).getName() + " " + valuesFromFile.get(i).getValue());
        }

        Class<Object> clazz = (Class<Object>) object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Injectable.class)) {
                Injectable annotation = field.getAnnotation(Injectable.class);
                field.setAccessible(true);
                if (annotation.propertyName().isBlank()) {
                    System.err.println("Property for field " + field.getName() + " is empty");
                    for (Values values : valuesFromFile) {
                        if (field.getName().equals(values.getName())) {
                            if (field.getType().equals(String.class))
                                field.set(object, values.getValue());
                            else
                                field.set(object, Integer.parseInt(values.getValue()));
                        }
                    }
                    continue;

                }

                for (Values values : valuesFromFile) {
                    if (annotation.propertyName().equals(values.getName())) {
                        if (field.getType().equals(String.class))
                            field.set(object, values.getValue());
                        else
                            field.set(object, Integer.parseInt(values.getValue()));
                    }
                }
            }

        }

        return object;
    }

}
