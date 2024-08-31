import storage.Storage;
import task.TaskList;


public class Blitz {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Blitz(String storagePath) {
        this.storage = Storage.createStorage(storagePath);
        this.taskList = new TaskList(storage);
        ui = new Ui(taskList);
    }

    public static void main(String[] args) {
        String path = "src/main/data/blitz.txt";
        Blitz blitzJr = new Blitz(path);
    }

    public TaskList getTaskList() {
        return taskList;
    }
}
