package Task;

import Parser.Parser;
import Storage.Storage;
import exceptions.InvalidTaskException;
import exceptions.NoTaskDescriptionException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {

    protected ArrayList<Task> inputHistory;
    protected Storage storage;

    public TaskList(Storage storage) {
       inputHistory = new ArrayList<>();
       this.storage = storage;
       loadDataFromStorage();
    }

    public void loadDataFromStorage() {
        try {
            File dataFile = storage.getStorageFile();
            Scanner scanner = new Scanner(dataFile);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                try {
//                    System.out.println("This is the formatted task: " + Parser.dataInputToUserInput(line));
                    Task newTask = Task.createTask(Parser.dataInputToUserInput(line));
                    inputHistory.add(newTask);
                } catch (NoTaskDescriptionException e) {
                    System.out.println("Wah, no description then I record what?");
                }
            }
        } catch (IOException e) {
            System.out.println("Error while reading from file");
        }
    }

    public void writeToStorage() {
        try {
            FileWriter writer = new FileWriter(storage.getStorageFile());

            for (int i = 0; i < inputHistory.size(); i++) {
                Task task = inputHistory.get(i);
                writer.write((i + 1) + ". " + task + "\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file");
            e.printStackTrace();
        }
    }

    public void addTask(Task newTask) {
        inputHistory.add(newTask);
    }

    public Task removeTask(int deleteIndex) {
        Task taskToDelete = inputHistory.get(deleteIndex);
        inputHistory.remove(taskToDelete);
        return taskToDelete;
    }

    public void changeTaskStatus(String action, int indexToChange) {
        System.out.println("---------------");
        Task task = inputHistory.get(indexToChange);
        if (action.equals("mark")) {
            task.changeStatus(true);
            System.out.println("GOOD RIDDANCE! Finally, this task is done:\n" +
                    task);
        } else {
            task.changeStatus(false);
            System.out.println("Alright, this task is not done yet faster finish leh:\n" +
                    task);
        }
        System.out.println("---------------");
    }

    public void displayList() {
        System.out.println("---------------");
        inputHistory.forEach(task -> System.out.println((this.inputHistory.indexOf(task) + 1) +
                ". " +
                task));
        System.out.println("---------------\n");
    }

    public int getSize() {
        return inputHistory.size();
    }
}
