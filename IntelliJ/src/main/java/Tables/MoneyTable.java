package Tables;

import org.joda.time.LocalDate;

public class MoneyTable extends DefaultTable<Entries.MoneyTableEntry> {

    public void addData(LocalDate useDate, double amount, String type, String description) {
        Entries.MoneyTableEntry entries = new Entries.MoneyTableEntry(useDate, amount, type, description);
        super.addData(entries);
    }


// TODO: 17/04/2020
    /*
    public double getTotalSum(){
        Map<LocalDate,Entries.MoneyTableEntry> moneyTable = getTabel();
        double sum = 0;
        for (Entries.MoneyTableEntry moneyTableEntry: moneyTable.values())
            sum += moneyTableEntry.getAmount();

        return sum;
    }
    */
}
