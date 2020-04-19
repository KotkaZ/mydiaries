package com.kotkaz.mydiaries.diary.tables;

import com.kotkaz.mydiaries.diary.entries.MoneyTableEntry;

import org.joda.time.LocalDate;

public class MoneyTable extends DefaultTable<MoneyTableEntry> {

    public void addData(String type, LocalDate useDate, double amount, String description) {
        MoneyTableEntry entries = new MoneyTableEntry(type, useDate, amount, description);
        super.addData(entries);
    }

    /**
     * Sums all incomes on given period and returns the value
     *
     * @param moneyTable
     * @param start      inclusive
     * @param end        inclusive
     * @return Posiitive int
     */

    public static double getIncome(MoneyTable moneyTable, LocalDate start, LocalDate end) {
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
     * @param start      inclusive
     * @param end        inclusive
     * @return Negative int
     */

    public static double getOutgo(MoneyTable moneyTable, LocalDate start, LocalDate end) {
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
     * @param start      inclusive
     * @param end        inclusive
     * @return Negative or positive int
     */
    public static double getTurnover(MoneyTable moneyTable, LocalDate start, LocalDate end) {
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
