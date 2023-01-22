package edu.geekhub.homework;

public class DownloaderThreads implements Runnable {

    private final Song song;

    public DownloaderThreads(Song song) {
        this.song = song;
    }

    @Override
    public void run() {
        CopyToFolder.copy(song);
    }
}
