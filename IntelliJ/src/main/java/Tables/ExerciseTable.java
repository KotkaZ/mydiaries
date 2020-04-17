package Tables;

import org.joda.time.LocalDate;

public class ExerciseTable extends DefaultTable<Entries.ExerciseTableEntry> {


    public void addData(LocalDate exerciseDate, String type, double length, String description, String location) {
        Entries.ExerciseTableEntry entries = new Entries.ExerciseTableEntry(exerciseDate, type, length, description, location);
        super.addData(entries);
    }


}