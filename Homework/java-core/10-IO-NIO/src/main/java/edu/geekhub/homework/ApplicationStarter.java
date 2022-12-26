package edu.geekhub.homework;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ApplicationStarter {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\777\\IdeaProjects\\geekhub-j4w-12-SopilkoMaxim\\Homework\\java-core\\10-IO-NIO\\src\\main\\resources\\playlist.txt");

        List<String> songs = new ArrayList<>();
        List<Song> songsEntities = new ArrayList<>();

        try {
            Scanner input = new Scanner(file);
            while (input.hasNextLine()) {
                songs.add(input.nextLine());
                }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        //songs.forEach(System.out::println);
        songsEntities = Converter.stringsToEntities(songs);
        songsEntities.forEach(System.out::println);
        songsEntities.forEach(CopyToFolder::copy);
        //CopyToFolder.copy(songsEntities.get(1));
        //System.out.println(songsEntitys);

    }


}
