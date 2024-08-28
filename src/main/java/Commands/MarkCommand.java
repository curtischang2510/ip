package Commands;

import Task.TaskList;

public class MarkCommand extends Command{
    private int indexToMark;

    public MarkCommand(int indexToMark) {
        this.indexToMark = indexToMark;
    }

    @Override
    public void execute(TaskList taskList) {
        taskList.changeTaskStatus("mark", indexToMark);
    }
}
