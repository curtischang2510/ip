package blitz;
import commands.Command;
import commands.ExitCommand;
import exceptions.InvalidTaskException;
import io.Parser;
import io.Ui;
import exceptions.InvalidDateException;
import storage.Storage;
import task.TaskList;

import java.util.Objects;


public class Blitz {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Blitz(String storagePath) {
        try {
            this.storage = Storage.createStorage(storagePath);
            this.taskList = new TaskList(storage);
            ui = new Ui(taskList);
        } catch (InvalidDateException e) {
            System.out.println("PLEASE USE THE PROPER DATE FORMAT");
        }
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public String getResponse(String userInput) {
        assert userInput.isEmpty() : "Empty userInputs should not be handled here";
        try {
            Command userCommand = Parser.inputToCommand(userInput);
            String message = userCommand.execute(taskList);

            if (userCommand instanceof ExitCommand) {
            }

            return message;
        } catch (InvalidTaskException e) {
            return "THAT IS AN INVALID TASK LAH";
        } catch (ArrayIndexOutOfBoundsException e) {
            return "A valid index has not been given!!";
        }
    }
}
