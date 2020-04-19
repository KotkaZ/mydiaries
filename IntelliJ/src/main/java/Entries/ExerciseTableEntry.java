package Entries;

import org.joda.time.LocalDateTime;

public class ExerciseTableEntry extends DefaultEntry implements Comparable {
    private LocalDateTime exerciseDate;
    private double length;
    private String description;
    private String location;

    public ExerciseTableEntry(String type, LocalDateTime exerciseDate, double length, String description, String location) {
        super(type);
        this.exerciseDate = exerciseDate;
        this.length = length;
        this.description = description;
        this.location = location;
    }

    public LocalDateTime getExerciseDate() {
        return exerciseDate;
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

    @Override
    public int compareTo(Object o) {
        return this.exerciseDate.compareTo(((ExerciseTableEntry)o).exerciseDate);
    }
}
