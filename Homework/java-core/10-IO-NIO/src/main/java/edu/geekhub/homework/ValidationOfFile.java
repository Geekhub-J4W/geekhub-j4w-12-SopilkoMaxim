package edu.geekhub.homework;

import java.io.File;

public class ValidationOfFile {
    public static void validateSize (File filePath){
        if(filePath.length()/(1024*1024)>10)
            System.out.println("File is to big");
    }
    public static void validateIsFileExist(File filePath){
        if (!filePath.exists())
            System.out.println("File not exist");
    }
}

