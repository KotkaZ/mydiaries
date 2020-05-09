package com.kotkaz.mydiaries.diary.tables;

import com.kotkaz.mydiaries.diary.entries.FoodTableEntry;

import org.joda.time.LocalDate;

import java.util.List;

public class FoodTable extends DefaultTable<FoodTableEntry> {


    public void addData(String type, LocalDate expDate, int amount, String unit) {
        FoodTableEntry entries = new FoodTableEntry(type, expDate, amount, unit);
        super.addData(entries);
    }


    /**
     * This method orderes list by Entry-class object variables.
     *
     * @param type      Int typenumber
     *                  0- type
     *                  1- inputdate
     *                  2- expDate
     * @param ascending
     * @return
     */
    public List<FoodTableEntry> getOrderedTable(int type, boolean ascending) {
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
                    value = o1.getExpDate().compareTo(o2.getExpDate());
                    break;
                default:
                    throw new IllegalArgumentException("FoodTable#getOrderedTabel wrong ordering type");
            }
            return value * (ascending ? 1 : -1);
        });
    }

}

