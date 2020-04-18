package Entries;

import org.joda.time.LocalDate;

public class ToDoTableEntry extends DefaultEntry{
    private LocalDate deadline;
    private String description;
    private int priority;

    public ToDoTableEntry(String type, LocalDate deadline, String description, int priority) {
        super(type);
        this.deadline = deadline;
        this.description = description;
        this.priority = priority;
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

}
