package edu.geekhub.homework.inject;

import edu.geekhub.homework.GeekHubCourse;
import edu.geekhub.homework.Values;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InjectProcessor {

    private static final String separator = File.separator;
    private static final File file = new File("Homework" + separator + "java-core" + separator + "13-Reflection-Api" + separator
            + "src" + separator + "main" + separator + "resources" + separator + "application.properties");

    public void process(Object object) throws IllegalAccessException, InstantiationException {
        ArrayList<Values> valuesFromFile = parsingFile();
        System.out.println("Values from file is:");
        for (int i = 0; i < valuesFromFile.size(); i++) {
            System.out.println(valuesFromFile.get(i).getName() + " " + valuesFromFile.get(i).getValue());
        }

        Class<Object> clazz = (Class<Object>) object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        //Object object = clazz.newInstance();

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
        System.out.println(object);

    }

    private static ArrayList<Values> parsingFile() {
        List<String> valuesSplitedInOneLine = new ArrayList<>();
        Scanner input = null;
        String[] splitedValue;
        ArrayList<Values> valuesEntity = new ArrayList<>();
        try {
            input = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (input.hasNextLine()) {
            valuesSplitedInOneLine.add(input.nextLine());
        }
        for (String line : valuesSplitedInOneLine) {
            splitedValue = line.split("=");
            splitedValue[0] = splitedValue[0].replace("gh.inject.", "");
            /*splitedValue[0] = splitedValue[0].replace("course", "");
            splitedValue[0] = splitedValue[0].toLowerCase();*/
            valuesEntity.add(new Values(splitedValue[0], splitedValue[1]));
        }
        return valuesEntity;
    }
}
