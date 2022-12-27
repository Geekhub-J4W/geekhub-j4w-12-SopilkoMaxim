package edu.geekhub.homework;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class ApplicationStarter {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\777\\IdeaProjects\\geekhub-j4w-12-SopilkoMaxim\\Homework\\java-core\\10-IO-NIO\\src\\main\\resources\\playlist.txt");

        List<String> songs = new ArrayList<>();
        List<Song> songsEntities = new ArrayList<>();
        int menu=0;
        while(menu!=4){
            System.out.println("Menu:");
            System.out.println("1.Copy songs from URl from file " +
                    "\n2.Copy song from URL keyboard" +
                    "\n3.Read Log file " +
                    "\n4.Exit");
            Scanner scan = new Scanner(System.in);
            menu = scan.nextInt();
            if(menu<0||menu>4)
            {
                System.out.println("Wrong number,try again:");
                menu = scan.nextInt();
            }
            if(menu==1){
                try {
                    Scanner input = new Scanner(file);
                    while (input.hasNextLine()) {
                        songs.add(input.nextLine());
                    }
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                songsEntities = Converter.stringsToEntities(songs);
                songsEntities.forEach(CopyToFolder::copy);
            if(menu==2)
            {
                String pathSongFromKeyboard = scan.next();
                ValidationOfFile.validateAll(pathSongFromKeyboard);
                CopyToFolder.copy(Converter.stringToEntity(pathSongFromKeyboard));
            }
            if(menu==3){
                try {
                    FileReader reader = new FileReader("resources\\log.txt");
                    int c;
                    while((c=reader.read())!=-1)
                    {
                        System.out.print((char)c);
                    }

                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            }
        }
    }
}
