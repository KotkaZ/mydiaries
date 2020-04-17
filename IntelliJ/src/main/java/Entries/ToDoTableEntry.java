package Entries;

import org.joda.time.LocalDate;

public class ToDoTableEntry {
    private LocalDate deadline;
    private String description;
    private int priority;
    private String type;

    public ToDoTableEntry(LocalDate deadline, String description, int priority, String type) {
        this.deadline = deadline;
        this.description = description;
        this.priority = priority;
        this.type = type;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public String getType() {
        return type;
    }

}
