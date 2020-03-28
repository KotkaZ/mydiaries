import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;

public class FoodTable extends DefaultTable<Entries.FoodTableEntry> {


    public void addData(LocalDateTime expDate, String type, int amount) {
        Entries.FoodTableEntry entries = new Entries.FoodTableEntry(expDate, type, amount);
        super.addData(entries);
    }

    /**
     * Sorteerib kuupäeva alusel, vanemad ees, ja väljastab tabeli kujul.
     */
    public void showOrderedByExpDate() {
        Map<LocalDateTime, Entries.FoodTableEntry> mapThis = this.getTabel();
        Object[] expiringArray = mapThis.values().toArray();
        Arrays.sort(expiringArray);
        System.out.printf("%-30s%-10s%-20s%n", "Expire date", "Amount", "Type");
        for (Entries.FoodTableEntry entry :
                (Entries.FoodTableEntry[]) expiringArray) {
            System.out.printf("%-30s%-10s%-20s%n", entry.getExpDate(), entry.getAmount(), entry.getType());
        }
    }
}
/*

class TestFoodTabel {
    public static void main(String[] args) {
        System.out.printf("%-30s%-10s%-20s%n", "Expire date", "Amount", "Type");
        System.out.printf("%-30s%-10s%-20s%n", LocalDateTime.now(), 999, "Sitaks piima");
    }
}
/*
 */
