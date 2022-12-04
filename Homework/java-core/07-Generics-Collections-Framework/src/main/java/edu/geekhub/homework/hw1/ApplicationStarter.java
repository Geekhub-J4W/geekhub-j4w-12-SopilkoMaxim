package edu.geekhub.homework.hw1;

public class ApplicationStarter {


    public static void main(String[] args) {
        OddIndexIterable<Integer> integers = new OddIndexIterable<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        integers.add(6);

        for (Integer integer : integers)
            System.out.println(integer);
    }
}
