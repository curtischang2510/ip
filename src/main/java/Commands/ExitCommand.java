package Commands;

import Task.TaskList;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList) {
        System.out.println("----------------\n" +
                "Till we meet again, GOODBYE!");
    }
}
