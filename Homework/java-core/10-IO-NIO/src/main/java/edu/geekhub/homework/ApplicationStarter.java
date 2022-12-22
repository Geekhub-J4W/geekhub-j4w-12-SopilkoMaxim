package edu.geekhub.homework;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ApplicationStarter {
    public static void main(String[] args) {
        File file = new File("https://github.com/Geekhub-J4W/geekhub-j4w-12-SopilkoMaxim/blob/f30ab478b103b9e962c64a78f39e5f497aa95437/Homework/java-core/10-IO-NIO/src/main/resources/playlist.txt");
        Scanner input;
        List<String> songs = new ArrayList<>();


        try {
            input = new Scanner(file);
            while (input.hasNextLine()) {
                songs.add(input.nextLine());
                System.out.println(input.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


}
