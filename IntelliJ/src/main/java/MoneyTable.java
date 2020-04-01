import java.time.LocalDateTime;
import java.util.Map;

public class MoneyTable extends DefaultTable<Entries.MoneyTableEntry> {

    public void addData(LocalDateTime useDate, double amount, String type, String description) {
        Entries.MoneyTableEntry entries = new Entries.MoneyTableEntry(useDate, amount, type, description);
        super.addData(entries);
    }

    public double getTotalSum(){
        Map<LocalDateTime,Entries.MoneyTableEntry> moneyTable = getTabel();
        double sum = 0;
        for (Entries.MoneyTableEntry moneyTableEntry: moneyTable.values())
            sum += moneyTableEntry.getAmount();

        return sum;
    }

}
