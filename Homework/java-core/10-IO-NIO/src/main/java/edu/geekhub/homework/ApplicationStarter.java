package edu.geekhub.homework;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class ApplicationStarter {
    public static void main(String[] args) {
        String separator = File.separator;
        File file = new File("Homework"+separator+"java-core"+separator+"10-IO-NIO"+separator
                +"src"+separator+"main"+separator+"resources"+separator+"playlist.txt");

        List<String> songs = new ArrayList<>();
        List<Song> songsEntities = new ArrayList<>();
        int menu=0;
        Scanner scan = new Scanner(System.in);
        while(menu!=4){
            System.out.println("Menu:");
            System.out.println("1.Copy songs from URl from file " +
                    "\n2.Copy song from URL keyboard" +
                    "\n3.Read Log file " +
                    "\n4.Exit");
            menu = scan.nextInt();
            if(menu<0||menu>4)
            {
                System.out.println("Wrong number,try again:");
                menu = scan.nextInt();
            }
            if(menu==1) {
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
            }
            if(menu==2)
            {
                System.out.println("Enter path to the file:");
                Scanner scanLine = new Scanner(System.in);
                String pathSongFromKeyboard = scanLine.nextLine();
                ValidationOfFile.validateAll(pathSongFromKeyboard);
                CopyToFolder.copy(Converter.stringToEntity(pathSongFromKeyboard));
            }
            if(menu==3){
                try {
                    FileReader reader = new FileReader("Homework"+separator+"java-core"+separator+
                            "10-IO-NIO"+separator+"src"+separator+"main"+separator+"resources"+
                            separator+"log.txt");
                    int c;
                    while((c=reader.read())!=-1)
                    {
                        System.out.print((char)c);
                    }
                   reader.close();

                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }


        }
    }
}
