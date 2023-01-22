package edu.geekhub.homework;

import java.io.*;
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
        while(menu!=5){
            System.out.println("Menu:");
            System.out.println("1.Copy songs from URl from file " +
                    "\n2.Copy song from URL keyboard" +
                    "\n3.Read Log file " +
                    "\n4.Copy songs from URL from file with threads" +
                    "\n5.Exit");
            menu = scan.nextInt();
            if(menu<0||menu>5)
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
            if (menu==4){
                try {
                    Scanner input = new Scanner(file);
                    while (input.hasNextLine()) {
                        songs.add(input.nextLine());
                    }
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                songsEntities = Converter.stringsToEntities(songs);
                /*DownloaderThreads downloaderTreads0 = new DownloaderThreads(songsEntities.get(0));
                Thread firstSong = new Thread(downloaderTreads0);
                DownloaderThreads downloaderTreads1 = new DownloaderThreads(songsEntities.get(1));
                Thread secondSong = new Thread(downloaderTreads1);
                DownloaderThreads downloaderTreads2 = new DownloaderThreads(songsEntities.get(2));
                Thread thirdSong = new Thread(downloaderTreads2);
                DownloaderThreads downloaderTreads3 = new DownloaderThreads(songsEntities.get(3));
                Thread fourthSong = new Thread(downloaderTreads3);
                DownloaderThreads downloaderTreads4 = new DownloaderThreads(songsEntities.get(4));
                Thread fifthSong = new Thread(downloaderTreads4);
                firstSong.start();
                secondSong.start();
                thirdSong.start();
                fourthSong.start();
                fifthSong.start();*/
                List<DownloaderThreads> songsRunnableEntity= new ArrayList<>();
                List<Thread> songsThreads = new ArrayList<>();
                for(int i=0;i<songsEntities.size();i++){
                    songsRunnableEntity.add(i,new DownloaderThreads(songsEntities.get(i)));
                    songsThreads.add(i,new Thread(songsRunnableEntity.get(i)));
                    songsThreads.get(i).start();
                    try {
                        songsThreads.get(i).join();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }


        }
    }
}
