package edu.geekhub.homework.inject;

import edu.geekhub.homework.Values;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReaderConverter {

    private static final String separator = File.separator;
    private static final File file = new File("Homework" + separator + "java-core" + separator + "13-Reflection-Api" + separator
            + "src" + separator + "main" + separator + "resources" + separator + "application.properties");

    public static ArrayList<Values> convertFileToEntity(){
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
            valuesEntity.add(new Values(splitedValue[0], splitedValue[1]));
        }
        return valuesEntity;
    }
}
