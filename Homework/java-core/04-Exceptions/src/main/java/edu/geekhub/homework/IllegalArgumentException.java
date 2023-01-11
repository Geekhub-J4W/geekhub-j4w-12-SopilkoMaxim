package edu.geekhub.homework;

public class IllegalArgumentException extends RuntimeException {

    private final String[] messages;

    public IllegalArgumentException(String... messages) {

        this.messages = messages;
    }
}
