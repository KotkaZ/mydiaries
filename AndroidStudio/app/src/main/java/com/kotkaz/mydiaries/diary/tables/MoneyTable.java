package com.kotkaz.mydiaries.diary.tables;

import com.kotkaz.mydiaries.diary.entries.MoneyTableEntry;

import org.joda.time.LocalDateTime;

import java.util.List;

public class MoneyTable extends DefaultTable<MoneyTableEntry> {

    public void addData(String type, LocalDateTime useDate, double amount, String description) {
        MoneyTableEntry entries = new MoneyTableEntry(type, useDate, amount, description);
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


}
