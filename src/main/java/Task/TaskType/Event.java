package Task.TaskType;

import Task.Task;

public class Event extends Task {

    String start;
    String end;

    public Event(String description, String start, String end) {
        super(TaskType.EVENT, description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        String dateStr = "from: " + start + " to: " + end;
        return "[E]" + super.toString() + " (" + dateStr + ")";
    }
}
