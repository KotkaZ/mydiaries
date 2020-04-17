package Entries;

import org.joda.time.LocalDate;

public class ExerciseTableEntry {
    private LocalDate exerciseDate;
    private String type;
    private double length;
    private String description;
    private String location;

    public ExerciseTableEntry(LocalDate exerciseDate, String type, double length, String description, String location) {
        this.exerciseDate = exerciseDate;
        this.type = type;
        this.length = length;
        this.description = description;
        this.location = location;
    }

    public LocalDate getExerciseDate() {
        return exerciseDate;
    }

    public String getType() {
        return type;
    }

    public double getLength() {
        return length;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }
}
