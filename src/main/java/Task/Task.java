package Task;

import Task.TaskType.Deadline;
import Task.TaskType.TaskType;
import Task.TaskType.Todo;
import Task.TaskType.Event;
import exceptions.InvalidTaskException;
import exceptions.NoTaskDescriptionException;

import java.util.Arrays;
import java.util.HashSet;

public class Task {
    protected String description;
    protected  boolean isDone;
    protected TaskType type;
    protected static HashSet<String> taskNames = new HashSet<>(Arrays.asList("todo", "deadline", "event"));

    public static Task createTask(String description)
            throws NoTaskDescriptionException {
        String strippedDescription = description.trim().toLowerCase();
        String[] words = description.split(" ");

        if (words.length == 1) {
            throw new NoTaskDescriptionException();
        }

        TaskType taskType = determineTaskType(strippedDescription);

        if (taskType == TaskType.TODO) {
            return new Todo(description);
        } else if (taskType == TaskType.DEADLINE) {
            String[] splitStr = description.split("/");
            String des = splitStr[0].trim();
            String deadline = splitStr[1].split(" ")[1];
            return new Deadline(des, deadline);
        } else {
            String[] splitStr = description.split("/");
            String des = splitStr[0].trim();
            String start = splitStr[1].replace("from ", "").trim();
            String end = splitStr[2].replace("to ", "").trim();
            return new Event(des, start, end);
        }
    }

    public static TaskType determineTaskType(String description) {
        if (description.startsWith("todo")) {
            return TaskType.TODO;
        } else if (description.startsWith("deadline")) {
            return TaskType.DEADLINE;
        } else {
            return TaskType.EVENT;
        }
    }

    protected Task(TaskType type, String description) {
        this.type = type;
        this.description = description;
        this.isDone = false;
    }


    public void changeStatus(boolean newIsDone) {
       this.isDone = newIsDone;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public TaskType getTaskType(){return this.type;}

    @Override
    public String toString() {
        int firstSpaceIndex = this.description.indexOf(' ');
        String removeFirstWord = this.description.substring(firstSpaceIndex + 1);
        return "[" + this.getStatusIcon() + "] " + removeFirstWord;
    }
}
