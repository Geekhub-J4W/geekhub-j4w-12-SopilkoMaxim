package edu.geekhub.orcostat;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateEnterData {
    public static int validateNumber(){
        Scanner scan = new Scanner(System.in);
        int number= scan.nextInt();
        while (number < 0 ) {
            System.out.println("Wrong number,try again:");
            number = scan.nextInt();
        }
        return number;

    }

    public static int validateMenuNumber(int enterNumber, int maxExpectedNumber) {
        while (enterNumber < 0 || enterNumber > maxExpectedNumber) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Wrong number,try again:");
            enterNumber = scan.nextInt();

        }
        return enterNumber;
    }

    public static String validateDate(){
        Scanner scan = new Scanner(System.in);
        String addDate= scan.nextLine();
        Pattern pattern = Pattern.compile("(0?[1-9]|[12][0-9]|3[01]).(0?[1-9]|1[012]).((19|20)\\d\\d)");
        Matcher matcher = pattern.matcher(addDate);
        boolean resultMatching = matcher.matches();
        while (!resultMatching) {
            System.out.println("Wrong add date,try again in format DD.MM.YYYY:");
            addDate = scan.nextLine();
            if(pattern.matcher(addDate).matches())
                resultMatching = true;
        }
        return addDate;
    }
}
