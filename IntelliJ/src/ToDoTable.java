import java.time.LocalDateTime;

public class ToDoTable extends DefaultTable<Entries.ToDoTableEntry> {


    public void addData(LocalDateTime deadline, String description, int priority, String type) {
        Entries.ToDoTableEntry entries = new Entries.ToDoTableEntry(deadline, description, priority, type);
        super.addData(entries);
    }

}