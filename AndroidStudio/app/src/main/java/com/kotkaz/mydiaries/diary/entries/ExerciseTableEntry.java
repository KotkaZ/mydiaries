package com.kotkaz.mydiaries.diary.entries;

import org.joda.time.LocalDate;

public class ExerciseTableEntry extends DefaultEntry implements Comparable {
    private LocalDate exerciseDate;
    private int length;
    private String description;
    private String location;

    public ExerciseTableEntry(String type, LocalDate exerciseDate, int length, String description, String location) {
        super(type);
        this.exerciseDate = exerciseDate;
        this.length = length;
        this.description = description;
        this.location = location;
    }

    public LocalDate getExerciseDate() {
        return exerciseDate;
    }

    public int getLength() {
        return length;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public int compareTo(Object o) {
        return this.exerciseDate.compareTo(((ExerciseTableEntry) o).exerciseDate);
    }
}
