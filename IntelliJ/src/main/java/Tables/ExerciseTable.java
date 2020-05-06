package Tables;

import org.joda.time.LocalDateTime;

public class ExerciseTable extends DefaultTable<Entries.ExerciseTableEntry> {


    public void addData(LocalDateTime exerciseDate, String type, int length, String description, String location) {
        Entries.ExerciseTableEntry entries = new Entries.ExerciseTableEntry( type, exerciseDate, length, description, location);
        super.addData(entries);
    }


}