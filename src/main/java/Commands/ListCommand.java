package Commands;

import Task.TaskList;

public class ListCommand extends Command{

    @Override
    public void execute(TaskList taskList) {
        taskList.displayList();
    }
}
