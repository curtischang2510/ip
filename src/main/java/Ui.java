import Commands.Command;
import Commands.ExitCommand;
import Parser.Parser;
import Task.TaskList;
import exceptions.InvalidTaskException;

import java.util.Scanner;

public class Ui {

    private TaskList taskList;

    public Ui(TaskList taskList){
        this.taskList = taskList;
        this.run();
    }

    public void run() {
        System.out.println("---------------\n" +
                "Hello! I'm BLITZ \n" +
                "What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            try {
                Command userCommand = Parser.inputToCommand(userInput);
                if (userCommand == null) {
                    continue;
                }
                userCommand.execute(taskList);
                if (userCommand instanceof ExitCommand) {
                    break;
                }
            } catch (InvalidTaskException e) {
                System.out.println("THAT IS AN INVALID TASK LAH");
            }
        }
    }
}
