package Tables;

import Entries.ExerciseTableEntry;
import Entries.FoodTableEntry;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Arrays;
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

    /**
     * Returns sorted list of today's expiring food.
     *
     * @param foodTable
     * @return
     */
    public static List<FoodTableEntry> getTodaysExp(FoodTable foodTable){
        return getExp4Date(foodTable, LocalDate.now());
    }
    /**
     * This method orderes list by Entry-class object variables.
     *
     * @param type Int typenumber
     *             0- type
     *             1- inputdate
     *             2- expDate
     * @param ascending
     * @return
     */
    public List<FoodTableEntry> getOrderedTable(int type, boolean ascending){
        return super.getOrderedTable((o1, o2) -> {
            int value;
            switch (type){

                case 0: value = o1.getType().compareTo(o2.getType());break;
                case 1: value = o1.getInputDate().compareTo(o2.getInputDate());break;
                case 2: value = o1.getExpDate().compareTo(o2.getExpDate());break;
                default:
                    throw new IllegalArgumentException("FoodTable#getOrderedTabel wrong ordering type");
            }
            return value  * (ascending ? 1 : -1);
        });
    }


    /**
     * This method orderes list by Entry-class object variables.
     *
     * @param type Int typenumber
     *             0- type
     *             1- inputdate
     *             2- expDate
     *             3- type & inputType
     * @param ascending
     * @return
     */
    public List<FoodTableEntry> getOrderedTableTry(int type, boolean ascending){
        List<Integer> arguments;
        switch (type){
            case 0:
                arguments = Arrays.asList(0);break;
            case 1:
                arguments = Arrays.asList(1);break;
            case 2:
                arguments = Arrays.asList(2);break;
            case 3:
                arguments = Arrays.asList(0,1);break;
            default:
                throw new IllegalArgumentException("FoodTable#getOrderedTabel wrong ordering type");
        }
        return super.getOrderedTable((o1, o2) -> {
            int value = 0;
            for (int elType :
                    arguments) {
                switch (elType) {

                    case 0:
                        value = o1.getType().compareTo(o2.getType());
                        break;
                    case 1:
                        value = o1.getInputDate().compareTo(o2.getInputDate());
                        break;
                    case 2:
                        value = o1.getExpDate().compareTo(o2.getExpDate());
                        break;
                    default:
                        throw new RuntimeException("FoodTabel#getorderedTabel elType ");
                }
                if (value != 0) break;
            }
            return value  * (ascending ? 1 : -1);
        });
    }

}

