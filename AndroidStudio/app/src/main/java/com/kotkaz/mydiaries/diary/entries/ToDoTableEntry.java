package com.kotkaz.mydiaries.diary.entries;

import org.joda.time.LocalDateTime;

public class ToDoTableEntry extends DefaultEntry {
    private LocalDateTime deadline;
    private String description;
    private int priority;

    public ToDoTableEntry(String type, LocalDateTime deadline, String description, int priority) {
        super(type);
        this.deadline = deadline;
        this.description = description;
        this.priority = priority;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

}
