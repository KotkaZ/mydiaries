import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FoodTable extends DefaultTable<Entries.FoodTableEntry> {


    public void addData(LocalDateTime expDate, String type, int amount) {
        Entries.FoodTableEntry entries = new Entries.FoodTableEntry(expDate, type, amount);
        super.addData(entries);
    }

    /**
     * Sorteerib kuupäeva alusel, vanemad ees, ja väljastab tabeli kujul.
     */
    public Map<LocalDateTime, Entries.FoodTableEntry> getOrderedByPriority() {
        Map<LocalDateTime, Entries.FoodTableEntry> toDoTableEntryMap = getTabel();

        List<Map.Entry<LocalDateTime, Entries.FoodTableEntry>> entries =
                new ArrayList<>(toDoTableEntryMap.entrySet());
        entries.sort(Map.Entry.comparingByValue());
        Map<LocalDateTime, Entries.FoodTableEntry> orderedByPriority = new LinkedHashMap<>();
        for (Map.Entry<LocalDateTime, Entries.FoodTableEntry> entry : entries) {
            orderedByPriority.put(entry.getKey(), entry.getValue());
        }

        return orderedByPriority;
    }
}

