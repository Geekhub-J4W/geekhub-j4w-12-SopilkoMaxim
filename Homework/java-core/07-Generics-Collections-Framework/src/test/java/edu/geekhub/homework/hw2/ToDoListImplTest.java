package edu.geekhub.homework.hw2;

import edu.geekhub.homework.hw1.OddIndexIterable;
import edu.geekhub.homework.hw2.entity.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListImplTest {

    private ToDoListImpl<Task> toDoList;

    @BeforeEach
    void setUp() {
        toDoList = new ToDoListImpl<>();
        toDoList.addTaskToTheEnd(new Task("First", "Simple", 1));
        toDoList.addTaskToTheEnd(new Task("Second", "Medium", 2));
        toDoList.addTaskToTheEnd(new Task("Third", "Hard", 3));
    }

    @Test
    void tryToGetTopPriorityTask() {
        String nameMaxPriority = toDoList.getTopPriorityTask().getName();

        assertEquals("Third", nameMaxPriority);
    }

    @Test
    void tryToGetTaskByIndex() {
        String nameByIndex = toDoList.getTaskByIndex(0).getName();

        assertEquals("First", nameByIndex);
    }

    @Test
    void tryToSortByPriority() {
        toDoList.addTaskToTheEnd(new Task("Fourth", "Low", 0));
        List<Task> sortedList = toDoList.getSortedPriorityTasks();
        String nameOfFirstElement = sortedList.get(0).getName();

        assertEquals("Fourth", nameOfFirstElement);
    }
    @Test
    void tryToSortByAlphabet() {
        toDoList.addTaskToTheEnd(new Task("AAAA", "some", 1));
        List<Task> sortedList = toDoList.getSortedByAlphabetTasks();
        String nameOfFirstElement = sortedList.get(0).getName();

        assertEquals("AAAA", nameOfFirstElement);
    }

    @Test
    void tryToAddTaskToTheEnd(){
        toDoList.addTaskToTheEnd(new Task("Last", "some", 1));
        String nameOfTheLast = toDoList.getTaskByIndex(3).getName();

        assertEquals("Last", nameOfTheLast);
    }

    @Test
    void tryToAddTaskToTheStart(){
        toDoList.addTaskToTheStart(new Task("Zero", "some", 1));
        String nameOfTheStart = toDoList.getTaskByIndex(0).getName();

        assertEquals("Zero", nameOfTheStart);

    }


}