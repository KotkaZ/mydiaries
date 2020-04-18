package Entries;

import org.joda.time.LocalDate;

public class ExerciseTableEntry extends DefaultEntry {
    private LocalDate exerciseDate;
    private double length;
    private String description;
    private String location;

    public ExerciseTableEntry(String type, LocalDate exerciseDate, double length, String description, String location) {
        super(type);
        this.exerciseDate = exerciseDate;
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
