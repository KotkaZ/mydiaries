package Tables;

import Entries.FoodTableEntry;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FoodTable extends DefaultTable<Entries.FoodTableEntry> {


    public void addData(String type, LocalDate expDate, int amount, String unit) {
        Entries.FoodTableEntry entries = new Entries.FoodTableEntry(type, expDate,  amount, unit);
        super.addData(entries);
    }

    /**
     * Returns sorted list of FoodTableEntry object list
     *
     * @param foodTable
     * @param date
     * @return
     */
    public static List<FoodTableEntry> getExp4Date(FoodTable foodTable, LocalDate date){
        List<FoodTableEntry> list = new ArrayList<>();
        for (FoodTableEntry entry :
                foodTable.getOrderedTable(FoodTableEntry::compareTo)) {
            if (entry.getExpDate().equals(date))
                list.add(entry);
        }
        return list;
    }

    @Override
    public List<FoodTableEntry> getOrderedTable(int n) {
        return super.getOrderedTable(comparator);
    }

    /**
     * Returns sorted list of today's expiring food.
     *
     * @param foodTable
     * @return
     */
    public static List<FoodTableEntry> getTodaysExp(FoodTable foodTable){
        return getExp4Date(foodTable, LocalDate.now());
    }

}

