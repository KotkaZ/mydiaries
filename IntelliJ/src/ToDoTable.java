import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ToDoTable extends DefaultTable<Entries.ToDoTableEntry> {


    public void addData(LocalDateTime deadline, String description, int priority, String type) {
        Entries.ToDoTableEntry entries = new Entries.ToDoTableEntry(deadline, description, priority, type);
        super.addData(entries);
    }

    public void showOrderedByPriority() {
        Map<LocalDateTime, Entries.ToDoTableEntry> mapThis = this.getTabel();
        Object[] toDoArray = mapThis.values().toArray();
        Arrays.sort(toDoArray);
        System.out.printf("%-10s%-30s%-20s%n", "Priority", "Deadline", "Type", "Description");
        for (Entries.ToDoTableEntry entry :
                (Entries.ToDoTableEntry[]) toDoArray) {
            System.out.printf("%-10s%-30s%-20s%n", entry.getPriority(), entry.getDeadline(), entry.getType());
            System.out.println("Description: " + entry.getDescription());
        }
    }
}
/*
class TestToDoTabel {
    public static void main(String[] args) {
        System.out.printf("%-10s%-35s%-20s%n", "Priority", "Deadline", "Type");
        System.out.printf("%-10s%-35s%-20s%n", 999, LocalDateTime.now(), "Raske värk");
        System.out.println("Description: " + "Vaja teha palju staffuidalösdfh öaldfasjkdhfa,sdfnökasbckld.favökjs fk hsnk fnaskdjf");

    }
}
/*
 */

