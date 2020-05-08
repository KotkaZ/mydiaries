package Tables;

import Entries.MoneyTableEntry;
import org.joda.time.LocalDateTime;

import java.util.List;

public class MoneyTable extends DefaultTable<Entries.MoneyTableEntry> {

    public void addData(String type, LocalDateTime useDate, double amount, String description) {
        Entries.MoneyTableEntry entries = new Entries.MoneyTableEntry(type, useDate, amount, description);
        super.addData(entries);
    }

    /**
     * This method orderes list by Entry-classes object variables.
     *
     * @param type      Int typenumber
     *                  0- type
     *                  1- inputdate
     *                  2- amount
     *                  3- useDate
     * @param ascending
     * @return
     */
    public List<MoneyTableEntry> getOrderedTable(int type, boolean ascending) {
        return super.getOrderedTable((o1, o2) -> {
            int value;
            switch (type) {

                case 0:
                    value = o1.getType().compareTo(o2.getType());
                    break;
                case 1:
                    value = o1.getInputDate().compareTo(o2.getInputDate());
                    break;
                case 2:
                    value = Double.compare(o1.getAmount(), o2.getAmount());
                    break;
                case 3:
                    value = o1.getUseDate().compareTo(o2.getUseDate());
                    break;
                default:
                    throw new IllegalArgumentException("MoneyTable#getOrderedTabel wrong ordering type");
            }
            return value * (ascending ? 1 : -1);
        });
    }


    /**
     * Sums all incomes on given period and returns the value
     * @param moneyTable
     * @param start inclusive
     * @param end inclusive
     * @return Posiitive int
     */

    /*
    @Deprecated
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
     */

    /**
     * Sums all outgoes on given period and returns the value
     *
     * @param moneyTable
     * @param start inclusive
     * @param end inclusive
     * @return Negative int
     */
/*
    @Deprecated
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
    }*/

    /**
     * Sums all money movements on given period and returns the value
     *
     * @param moneyTable
     * @param start      inclusive
     * @param end        inclusive
     * @return Negative or positive int
     */
    /*
    @Deprecated
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
    }*/

    /**
     * getTotalSum Caluclates total sum.
     * @return Total sum is returned as double.
     */
    public double getTotalSum() {
        return getTabel().stream().mapToDouble(MoneyTableEntry::getAmount).sum();
    }

    /**
     * getTotalSum Caluclates total income.
     * @return Total income is returned as double.
     */
    public double getTotalIncome() {
        return getTabel().stream().mapToDouble(v -> Math.max(0,v.getAmount())).sum();
    }

    /**
     * getTotalSum Caluclates total outgo.
     * @return Total outgo is returned as double.
     */
    public double getTotalOutgo() {
        return getTabel().stream().mapToDouble(v -> Math.min(0,v.getAmount())).sum();
    }
}
