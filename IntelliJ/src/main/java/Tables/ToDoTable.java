package Tables;

import Entries.ToDoTableEntry;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ToDoTable extends DefaultTable<Entries.ToDoTableEntry> {


    public void addData(String type, LocalDateTime deadline, String description, int priority) {
        Entries.ToDoTableEntry entries = new Entries.ToDoTableEntry(type, deadline, description, priority);
        super.addData(entries);
    }

    /**
     * Returns sorted list of tasks, that deadlines is on given date.
     *
     * @param toDoTable
     * @param date
     * @return
     */
    public static List<ToDoTableEntry> getTasks4Date(ToDoTable toDoTable, LocalDateTime date){
        List<ToDoTableEntry> list = new ArrayList<>();
        for (ToDoTableEntry entry :
                toDoTable.getTabel()) {
            if (entry.getDeadline().equals(date) )
                list.add(entry);
        }
        Collections.sort(list);
        return list;
    }

    /**
     * Returns sorted list of today's tasks.
     *
     * @param toDoTable
     * @return
     */
    public static List<ToDoTableEntry> getTodaysTasks(ToDoTable toDoTable){
        return getTasks4Date(toDoTable, LocalDateTime.now());
    }


    /**
     * OUTDATED
     * @return
     */
    /*
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
    }*/
}



