package commands;

import task.TaskList;

public abstract class Command {
    public abstract void execute(TaskList taskList);
}
