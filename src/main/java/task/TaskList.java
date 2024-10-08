package task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

import exceptions.InvalidDateException;
import exceptions.InvalidTagNameException;
import exceptions.InvalidTaskException;
import exceptions.NoTaskDescriptionException;
import io.Parser;
import storage.Storage;

/**
 * Manages a list of tasks. This class handles the addition, removal, updating,
 * and storage of tasks, providing an interface for interacting with a task list.
 */
public class TaskList {

    private ArrayList<Task> inputHistory;
    private Storage storage;

    /**
     * Constructs a TaskList object with a given storage mechanism.
     * The tasks are loaded from storage upon initialization.
     *
     * @param storage The storage object used to save and load tasks.
     */
    public TaskList(Storage storage) throws InvalidDateException {
        assert storage != null : "You don't have anywhere to store the data";
        inputHistory = new ArrayList<>();
        this.storage = storage;
        loadDataFromStorage();
    }

    /**
     * Loads task data from storage and populates the task list.
     * Done once upon program initialisation
     * If an error occurs during loading, appropriate error messages are displayed.
     */
    public void loadDataFromStorage() throws InvalidDateException {
        try {
            File dataFile = storage.getStorageFile();
            Scanner scanner = new Scanner(dataFile);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                try {
                    Task newTask = Task.createTask(Parser.dataInputToUserInput(line));
                    inputHistory.add(newTask);
                } catch (NoTaskDescriptionException | InvalidTaskException e) {
                    // this line should never run because data from storage all have correct format
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            // this line should never run because storage will create the correct file
            // if one does not already exist
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
    public Task removeTask(int deleteIndex) throws IndexOutOfBoundsException {
        Task taskToDelete = inputHistory.get(deleteIndex);
        inputHistory.remove(taskToDelete);
        return taskToDelete;
    }

    /**
     * Changes the status of a task (mark as done or undone) based on the action provided.
     *
     * @param action The action to perform ("mark" or "unmark").
     * @param indexToChange The index of the task to change.
     * @return True if task is done. False otherwise.
     * @throws IndexOutOfBoundsException If the provided index is out of range.
     */
    public boolean changeTaskStatus(String action, int indexToChange) throws IndexOutOfBoundsException {
        Task task = inputHistory.get(indexToChange);
        if (action.equals("mark")) {
            task.changeStatus(true);
        } else {
            task.changeStatus(false);
        }
        return task.isDone;
    }

    /**
     * Filters tasks in the task list by their description.
     *
     * @param desc The description to filter tasks by.
     * @return A list of tasks that contain the specified description.
     */
    public ArrayList<Task> filterTaskByDescription(String desc) {
        return inputHistory.stream()
                .filter(x -> x.description.contains(desc))
                .collect(Collectors.toCollection(ArrayList :: new));
    }

    /**
     * Filters tasks in the task list by their tags.
     *
     * @param tags An array of tags to filter tasks by.
     * @return A list of tasks that contain all the specified tags.
     * @throws InvalidTagNameException If the specified tags are invalid.
     */
    public ArrayList<Task> filterTaskByTags(String[] tags) throws InvalidTagNameException {
        ArrayList<Tag> tagLst = TagManager.stringArrToTags(tags);
        return inputHistory.stream()
                .filter(task -> task.tagsLst.containsAll(tagLst))
                .collect(Collectors.toCollection(ArrayList :: new));
    }

    /**
     * Displays the current list of tasks.
     * Each task is printed along with its index and status.
     */
    public int getSize() {
        return inputHistory.size();
    }

    /**
     * Retrieves the task at the specified index in the task list.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return inputHistory.get(index);
    }


    /**
     * Returns the current list of tasks.
     * Each task is printed along with its index and status.
     */
    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder();
        inputHistory.stream().forEach(task ->
                resultString.append((inputHistory.indexOf(task) + 1))
                        .append(". ")
                        .append(task)
                        .append("\n")
        );

        return resultString.toString();
    }
}
