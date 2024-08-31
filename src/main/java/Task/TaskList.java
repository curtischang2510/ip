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

/**
 * Manages a list of tasks. This class handles the addition, removal, updating,
 * and storage of tasks, providing an interface for interacting with a task list.
 */
public class TaskList {

    protected ArrayList<Task> inputHistory;
    protected Storage storage;

    /**
     * Constructs a TaskList object with a given storage mechanism.
     * The tasks are loaded from storage upon initialization.
     *
     * @param storage The storage object used to save and load tasks.
     */
    public TaskList(Storage storage) {
       inputHistory = new ArrayList<>();
       this.storage = storage;
       loadDataFromStorage();
    }

    /**
     * Loads task data from storage and populates the task list.
     * Done once upon program initialisation
     * If an error occurs during loading, appropriate error messages are displayed.
     */
    public void loadDataFromStorage() {
        try {
            File dataFile = storage.getStorageFile();
            Scanner scanner = new Scanner(dataFile);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                try {
                    Task newTask = Task.createTask(Parser.dataInputToUserInput(line));
                    inputHistory.add(newTask);
                } catch (NoTaskDescriptionException e) {
                    System.out.println("Wah, no description then I record what?");
                } catch (InvalidTaskException e) {
                    System.out.println("This is not a valid task lah!!");
                }
            }
        } catch (IOException e) {
            System.out.println("Error while reading from file");
        }
    }

    /**
     * Writes the current list of tasks to the storage file.
     * Done after every {@code Command} except {@code ListCommand}
     * If an error occurs during writing, an error message is displayed.
     */
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

    /**
     * Adds a new task to the task list.
     *
     * @param newTask The new task to be added.
     */
    public void addTask(Task newTask) {
        inputHistory.add(newTask);
    }

    /**
     * Removes a task from the task list at the specified index.
     *
     * @param deleteIndex The index of the task to remove.
     * @return The task that was removed.
     * @throws IndexOutOfBoundsException If the provided index is out of range.
     */
    public Task removeTask(int deleteIndex) throws IndexOutOfBoundsException{
        Task taskToDelete = inputHistory.get(deleteIndex);
        inputHistory.remove(taskToDelete);
        return taskToDelete;
    }

    /**
     * Changes the status of a task (mark as done or undone) based on the action provided.
     *
     * @param action The action to perform ("mark" or "unmark").
     * @param indexToChange The index of the task to change.
     * @throws IndexOutOfBoundsException If the provided index is out of range.
     */
    public void changeTaskStatus(String action, int indexToChange) throws IndexOutOfBoundsException {
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

    /**
     * Displays the current list of tasks.
     * Each task is printed along with its index and status.
     */
    public void displayList() {
        System.out.println("---------------");
        inputHistory.forEach(task -> System.out.println((this.inputHistory.indexOf(task) + 1) +
                ". " +
                task));
        System.out.println("---------------\n");
    }

    /**
     * Displays the current list of tasks.
     * Each task is printed along with its index and status.
     */
    public int getSize() {
        return inputHistory.size();
    }
}
