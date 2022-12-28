package edu.geekhub.homework;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConverterTest {

    @Test
    void tryToConvertStringToEntitySong(){
        String song = "Heavy Metal | Metallica | And Justice for All | Master of Puppets | https://ia800300.us.archive.org/35/items/MetallicaMasterOfPuppets_0/02__Master_Of_Puppets_64kb.mp3";
        Song songEntity = Converter.stringToEntity(song);
        String songGenre = "HeavyMetal";

        assertEquals(songGenre,songEntity.genre());

    }

    @Test
    void tryToConvertWrongStringToEntitySong(){
        String song = "Metallica | And Justice for All | Master of Puppets | https://ia800300.us.archive.org/35/items/MetallicaMasterOfPuppets_0/02__Master_Of_Puppets_64kb.mp3";

        assertThrows(RuntimeException.class,()->Converter.stringToEntity(song));
    }

}