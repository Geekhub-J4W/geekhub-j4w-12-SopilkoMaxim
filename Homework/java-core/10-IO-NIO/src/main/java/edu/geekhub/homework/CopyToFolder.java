package edu.geekhub.homework;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CopyToFolder
{
    public static void copy(Song song)
    {
        String targetCopy = "C:\\"+song.genre()+"\\"+song.group()+"\\"+song.album()+"\\"+song.name()+".mp3";
        String folderTree = "C:\\"+song.genre()+"\\"+song.group()+"\\"+song.album();
        buildFoldersTree(folderTree);
        try(InputStream input = URI.create(song.url()).toURL().openStream()){
            Files.copy(input, Paths.get(targetCopy));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void buildFoldersTree(String folder){
        File folders = new File(folder);
        if(!folders.exists())
        {
            folders.mkdirs();
        }
    }
}
