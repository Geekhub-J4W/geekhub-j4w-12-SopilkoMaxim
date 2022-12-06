package edu.geekhub.homework.hw2;

import edu.geekhub.homework.hw2.entity.Task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ToDoListImpl<E extends Task> implements ToDoList<E> {
    private final List<E> tasksStorage = new ArrayList<>();

    @Override
    public E getTopPriorityTask() {
        int maxPriority = 0;
        int maxPriorityIndex = 0;
        for (int index = 0; index < tasksStorage.size(); index++) {
            if (maxPriority <= tasksStorage.get(index).getPriority()) {
                maxPriority = tasksStorage.get(index).getPriority();
                maxPriorityIndex = index;
            }
        }
        return tasksStorage.get(maxPriorityIndex);
    }

    @Override
    public E getTaskByIndex(int index) {
        return tasksStorage.get(index);
    }

    @Override
    public List<E> getAllTasks() {
        return tasksStorage;
    }

    @Override
    public List<E> getSortedPriorityTasks() {
        List<E> priorityTasks = tasksStorage;

        priorityTasks.sort(new Comparator<E>() {
            @Override
            public int compare(E o1, E o2) {

                return o1.getPriority() - o2.getPriority();
            }
        });
        return priorityTasks;
    }

    @Override
    public List<E> getSortedByAlphabetTasks() {
        List<E> nameTasks = tasksStorage;

        nameTasks.sort(new Comparator<E>() {
            @Override
            public int compare(E o1, E o2) {

                return o1.getName().compareTo(o2.getName());
            }
        });
        return nameTasks;
    }

    @Override
    public boolean addTaskToTheEnd(E task) {
        try {
            tasksStorage.add(tasksStorage.size(), task);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean addTaskToTheStart(E task) {
        int index = 0;
        try {
            tasksStorage.add(index, task);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteTaskByIndex(E task) {
        int index = tasksStorage.indexOf(task);
        try {
            tasksStorage.remove(index);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }
}
