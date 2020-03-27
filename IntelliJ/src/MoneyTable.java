import java.time.LocalDateTime;

public class MoneyTable extends DefaultTable<Entries.MoneyTableEntry> {

    public void addData(LocalDateTime useDate, double amount, String type, String description) {
        Entries.MoneyTableEntry entries = new Entries.MoneyTableEntry(useDate, amount, type, description);
        super.addData(entries);
    }

}
