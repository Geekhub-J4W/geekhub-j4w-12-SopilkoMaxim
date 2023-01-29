package com.geekhub;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final StudentService studentService = new StudentService();
    private static final String separator = File.separator;
    private static final File file = new File("Homework" + separator + "java-core" + separator + "12-Practice" + separator
            + "src" + separator + "main" + separator + "resources" + separator + "students.txt");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String command = scanner.nextLine();
            String[] parts = command.split(" ", 2);

            switch (parts[0]) {
                case "exit" -> {
                    return;
                }
                case "add-student" -> addStudent(parts);
                case "delete-student" -> deleteStudent(parts);
                case "list-students" -> listStudents();
                case "download-from-file" -> {
                    if(downloadFromFile())
                        System.out.println("Download completed");
                    else
                        System.out.println("Download didn't complete");
                }
                case"download-to-file" -> downloadToFileAllStudents();
                default -> System.err.println("Invalid command");
            }
        }
    }

    private static void downloadToFileAllStudents() {
        var students = studentService.getStudents();
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (Student student: students) {
                fileWriter.write("\n"+ student.getId()+" "+student.getFirstName()+" "+student.getLastName());
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static boolean downloadFromFile() {
        List<String> students = new ArrayList<>();
        String[] student;
        Scanner input = null;
        try {
            input = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (input.hasNextLine()) {
            students.add(input.nextLine());
        }
        if (students.size() == 0) {
            System.out.println("File is empty");
            return false;
        }
        var studentsFromRepo = studentService.getStudents();
        for(int i=0;i<students.size();i++)
        {
            student=students.get(i).split(" ");
            if(checkIdExist(student, studentsFromRepo))
                studentService.addStudent(new Student(Integer.parseInt(student[0]),student[1],student[2]));
        }
        return true;
    }

    private static boolean checkIdExist(String[] student, List<Student> studentsFromRepo) {
        for (Student studentFromRepo: studentsFromRepo) {
            if(studentFromRepo.getId()==Integer.parseInt(student[0])){
                System.out.println("Student with Id:"+ student[0]+" already exist");
                return false;
            }
        }
        return true;
    }

    private static void listStudents() {
        for (Student student : studentService.getStudents()) {
            System.out.println(student);
        }
    }

    private static void deleteStudent(String[] parts) {
        int id = Integer.parseInt(parts[1]);
        studentService.deleteStudent(id);
    }

    private static void addStudent(String[] parts) {
        final Student student = StudentConsoleParser.fromConsoleInput(parts[1]);
        studentService.addStudent(student);
        System.out.println("Student created with id " + student.getId());
    }

}
