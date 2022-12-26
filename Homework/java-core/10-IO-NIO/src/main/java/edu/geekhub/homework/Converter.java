package edu.geekhub.homework;

import java.util.ArrayList;
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
        Song songEntity = new Song(songParsed[0],songParsed[1],songParsed[2],songParsed[3],songParsed[4]);
        return songEntity;
    }

}
