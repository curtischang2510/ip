package Commands;

import Task.TaskList;

public class UnmarkCommand extends Command {

    private int indexToUnmark;

    public UnmarkCommand(int indexToUnmark) {
        this.indexToUnmark = indexToUnmark;
    }

    @Override
    public void execute(TaskList taskList) {
        taskList.changeTaskStatus("unmark", indexToUnmark);
    }
}
