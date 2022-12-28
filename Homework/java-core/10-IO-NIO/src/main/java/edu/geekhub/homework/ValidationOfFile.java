package edu.geekhub.homework;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Date;

public class ValidationOfFile {

    private static String separator = File.separator;

    public static void validateAll(String filePath)
    {
        Song song = Converter.stringToEntity(filePath);
        String targetCopy = "C:\\"+song.genre()+"\\"+song.group()+"\\"+song.album()+"\\"+song.name()+".mp3";
        validateSize(targetCopy);
        validateIsFileExist(song.url());
    }
    public static void validateSize (String filePath){
        File file = new File(filePath);
        if(file.length()/(1024*1024)>10)
        {System.out.println("File is to big");
            try (FileWriter writer = new FileWriter("Homework"+separator+"java-core"+separator+
                    "10-IO-NIO"+separator+"src"+separator+"main"+separator+"resources"+
                    separator+"log.txt",true);){
                Date date = new Date();
                String msg = date + filePath + "File is to big \n";
                writer.write(msg);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
    public static void validateIsFileExist(String filePath){
        try {
            new URL(filePath).toURI();
        } catch (URISyntaxException e) {
            try (FileWriter writer = new FileWriter("Homework"+separator+"java-core"+separator+
                    "10-IO-NIO"+separator+"src"+separator+"main"+separator+"resources"+
                    separator+"log.txt",true);){
                Date date = new Date();
                String msg = date + filePath + e+" \n";
                writer.write(msg);
            } catch (IOException io) {
                throw new RuntimeException(io);
            }
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            try (FileWriter writer = new FileWriter("Homework"+separator+"java-core"+separator+
                    "10-IO-NIO"+separator+"src"+separator+"main"+separator+"resources"+
                    separator+"log.txt",true);){
                Date date = new Date();
                String msg = date + filePath + e+" \n";
                writer.write(msg);
            } catch (IOException io) {
                throw new RuntimeException(io);
            }
            throw new RuntimeException(e);
        }
    }
}

