package Tables;

import Entries.DefaultEntry;
import Entries.MoneyTableEntry;
import org.joda.time.LocalDateTime;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class MoneyTable extends DefaultTable<Entries.MoneyTableEntry> {

    public void addData(String type, LocalDateTime useDate, double amount, String description) {
        Entries.MoneyTableEntry entries = new Entries.MoneyTableEntry(type, useDate, amount,  description);
        super.addData(entries);
    }

    public List<MoneyTableEntry> getOrderedTable(int type, boolean ascending){
        switch (type){
            case 0: {
                //Kui ascending on tõene, siis tagastakse kasvavases järjekorras, kus on sorteeritud tüübi alusel.
                //VAstasel juhul tagastatakse kahanevas tüüvi järjekorras.
                return ascending ? super.getOrderedTable(Comparator.comparing(DefaultEntry::getType)) :
                        super.getOrderedTable((moneyTableEntry, t1) -> t1.getType().compareTo(moneyTableEntry.getType()));

            }
            case 1: {
                //Todo
                return super.getOrderedTable(Comparator.comparing(DefaultEntry::getInputDate));
            }
            case 2: {
                //Todo
                return super.getOrderedTable(Comparator.comparingDouble(MoneyTableEntry::getAmount));
            }
            case 3: {
                //Esmalt võrreldakse tüübi alusel. Kui need on võrdsed siis võrreldakse sisestamis kuupäeva alusel. Kasvav järjekord.
                //KUi on ascending false siis tagastataskse kahanevas järjekorras.
                return ascending ? super.getOrderedTable(Comparator.comparing((Function<MoneyTableEntry, String>) DefaultEntry::getType).thenComparing(DefaultEntry::getInputDate)) :
                        super.getOrderedTable((moneyTableEntry, t1) -> {
                            int sComp = t1.getType().compareTo(moneyTableEntry.getType());

                            if(sComp != 0) return sComp;
                            return t1.getInputDate().compareTo(moneyTableEntry.getInputDate());
                        });
            }
            default:return getTabel();
        }
    }



    /**
     * Sums all incomes on given period and returns the value
     * @param moneyTable
     * @param start inclusive
     * @param end inclusive
     * @return Posiitive int
     */

    public static double getIncome(MoneyTable moneyTable, LocalDateTime start, LocalDateTime end ){
        double income = 0;
        for (MoneyTableEntry entry :
                moneyTable.getOrderedTable(MoneyTableEntry::compareTo)) {
            //ToDo
            if (start.compareTo(entry.getUseDate()) >= 0 && end.compareTo(entry.getUseDate()) <= 0) {
                double amount = entry.getAmount();
                if (amount >= 0)
                    income += amount;
            }
        }
        return income;
    }

    /**
     * Sums all outgoes on given period and returns the value
     *
     * @param moneyTable
     * @param start inclusive
     * @param end inclusive
     * @return Negative int
     */

    public static double getOutgo(MoneyTable moneyTable, LocalDateTime start, LocalDateTime end){
        double outgo = 0;
        //ToDo
        for (MoneyTableEntry entry :
                moneyTable.getOrderedTable(MoneyTableEntry::compareTo)) {
            //ToDo
            if (start.compareTo(entry.getUseDate()) >= 0 && end.compareTo(entry.getUseDate()) <= 0) {
                double amount = entry.getAmount();
                if (amount < 0)
                    outgo += amount;
            }
        }
        return outgo;
    }

    /**
     * Sums all money movements on given period and returns the value
     *
     * @param moneyTable
     * @param start inclusive
     * @param end inclusive
     * @return Negative or positive int
     */
    public static double getTurnover(MoneyTable moneyTable, LocalDateTime start, LocalDateTime end){
        double turnover = 0;
        //ToDo
        for (MoneyTableEntry entry :
                moneyTable.getOrderedTable(MoneyTableEntry::compareTo)) {
            //ToDo
            if (start.compareTo(entry.getUseDate()) >= 0 && end.compareTo(entry.getUseDate()) <= 0) {
                turnover += entry.getAmount();
            }
        }
        return turnover;
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
