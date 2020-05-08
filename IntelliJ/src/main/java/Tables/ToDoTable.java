package Tables;

import Entries.ToDoTableEntry;
import org.joda.time.LocalDateTime;

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
    /*
    @Deprecated
    public static List<ToDoTableEntry> getTasks4Date(ToDoTable toDoTable, LocalDateTime date){
        List<ToDoTableEntry> list = new ArrayList<>();
        for (ToDoTableEntry entry :
                toDoTable.getTabel()) {
            if (entry.getDeadline().equals(date) )
                list.add(entry);
        }
        Collections.sort(list);
        return list;
    }*/

    /**
     * Returns sorted list of today's tasks.
     *
     * @param toDoTable
     * @return
     */
    /*
    @Deprecated
    public static List<ToDoTableEntry> getTodaysTasks(ToDoTable toDoTable){
        return getTasks4Date(toDoTable, LocalDateTime.now());
    }

     */

    /**
     * This method orderes list by Entry-class object variables.
     *
     * @param type Int typenumber
     *             0- type
     *             1- inputdate
     *             2- deadline
     *             3- priority
     * @param ascending
     * @return
     */
    public List<ToDoTableEntry> getOrderedTable(int type, boolean ascending){
        return super.getOrderedTable((o1, o2) -> {
            int value;
            switch (type){
                case 0: value = o1.getType().compareTo(o2.getType());break;
                case 1: value = o1.getInputDate().compareTo(o2.getInputDate());break;
                case 2: value = o1.getDeadline().compareTo(o2.getDeadline());break;
                case 3: value = Integer.compare(o1.getPriority(),o2.getPriority());break;
                default:
                    throw new IllegalArgumentException("ToDoTable#getOrderedTabel wrong ordering type");
            }
            return value  * (ascending ? 1 : -1);
        });
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



