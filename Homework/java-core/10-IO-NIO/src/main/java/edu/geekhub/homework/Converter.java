package edu.geekhub.homework;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Converter {

    public static List<Song> stringsToEntities(List<String> songs){
        List<Song> songsEntities = new ArrayList<>();
        for(int i=0; i<songs.toArray().length;i++)
        {
            songsEntities.add(i,Converter.stringToEntity(songs.get(i)));

        }

        return  songsEntities;
    }

    public static Song stringToEntity (String song){
        String[] songParsed;
        song = song.replace(" ","");
        songParsed = song.split("\\|");
        if(songParsed.length!=5)
        {
            try (FileWriter writer = new FileWriter("resources\\log.txt",true);){
                Date date = new Date();
                String msg = date + song + "Wrong size of song declaration \n";
                writer.write(msg);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            throw new RuntimeException("Wrong size of song declaration");

        }
        Song songEntity = new Song(songParsed[0],songParsed[1],songParsed[2],songParsed[3],songParsed[4]);
        return songEntity;
    }

}
