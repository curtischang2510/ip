package Task.TaskType;

import Task.Task;

public class Todo extends Task {

    public Todo(String description) {
        super(TaskType.TODO, description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
