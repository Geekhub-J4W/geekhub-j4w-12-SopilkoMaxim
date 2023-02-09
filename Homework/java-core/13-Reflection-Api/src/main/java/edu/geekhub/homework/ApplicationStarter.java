package edu.geekhub.homework;

import edu.geekhub.homework.inject.InjectProcessor;


public class ApplicationStarter {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        InjectProcessor injectProcessor = new InjectProcessor();
        System.out.println(injectProcessor.process(new GeekHubCourse()));

    }

}
