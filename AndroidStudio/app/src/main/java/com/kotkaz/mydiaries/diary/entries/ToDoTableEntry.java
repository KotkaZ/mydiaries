package com.kotkaz.mydiaries.diary.entries;

import org.joda.time.LocalDate;

public class ToDoTableEntry extends DefaultEntry implements Comparable {
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

    /**
     * Shows difference between two ToDoTableEntry class objects
     *
     * @param o ToDoTabelEntry class object
     * @return Difference between priorities and  dates
     */
    @Override
    public int compareTo(Object o) {
        if (!(o instanceof ToDoTableEntry))
            throw new IllegalArgumentException();

        int priorityDiff = this.priority - ((ToDoTableEntry) o).priority;
        int deadLineDiff = this.deadline.compareTo(((ToDoTableEntry) o).deadline);

        if (deadLineDiff == 0)
            return priorityDiff;
        else
            return deadLineDiff;
    }
}
