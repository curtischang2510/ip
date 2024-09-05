package io;

import java.util.Scanner;

import commands.Command;
import commands.ExitCommand;
import exceptions.InvalidTaskException;
import task.TaskList;

public class Ui {

    private TaskList taskList;

    public Ui(TaskList taskList) {
        this.taskList = taskList;
        //this.run();
    }

    public void run() {
//        System.out.println("---------------\n"
//                + "Hello! I'm BLITZ \n"
//                + "What can I do for you?");
//        Scanner scanner = new Scanner(System.in);
//        while (true) {
//            String userInput = scanner.nextLine();
//            try {
//                Command userCommand = Parser.inputToCommand(userInput);
//                if (userCommand == null) {
//                    continue;
//                }
//                userCommand.execute(taskList);
//                if (userCommand instanceof ExitCommand) {
//                    break;
//                }
//            } catch (InvalidTaskException e) {
//                System.out.println("THAT IS AN INVALID TASK LAH");
//            } catch (ArrayIndexOutOfBoundsException e) {
//                System.out.println("A valid index has not been given!!");
//            }
//        }
    }

    public static void displayExitMessage() {
        System.out.println("----------------------\n"
                + "Till we meet again, GOODBYE");
    }
}
