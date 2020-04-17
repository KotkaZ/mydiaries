import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ToDoTable extends DefaultTable<Entries.ToDoTableEntry> {


    public void addData(LocalDate deadline, String description, int priority, String type) {
        Entries.ToDoTableEntry entries = new Entries.ToDoTableEntry(deadline, description, priority, type);
        super.addData(entries);
    }


    public Map<LocalDate, Entries.ToDoTableEntry> getOrderedByExpDate() {
        Map<LocalDate, Entries.ToDoTableEntry> toDoTableEntryMap = getTabel();

        List<Map.Entry<LocalDate, Entries.ToDoTableEntry>> entries =
                new ArrayList<>(toDoTableEntryMap.entrySet());
        entries.sort(Map.Entry.comparingByValue());
        Map<LocalDate, Entries.ToDoTableEntry> orderedByExpDate = new LinkedHashMap<>();
        for (Map.Entry<LocalDate, Entries.ToDoTableEntry> entry : entries) {
            orderedByExpDate.put(entry.getKey(), entry.getValue());
        }

        return orderedByExpDate;
    }
}


