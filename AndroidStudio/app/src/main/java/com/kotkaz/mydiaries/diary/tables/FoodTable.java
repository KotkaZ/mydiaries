package com.kotkaz.mydiaries.diary.tables;

import com.kotkaz.mydiaries.diary.entries.FoodTableEntry;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class FoodTable extends DefaultTable<FoodTableEntry> {


    public void addData(String type, LocalDate expDate, int amount) {
        FoodTableEntry entries = new FoodTableEntry(type, expDate, amount);
        super.addData(entries);
    }

    /**
     * Returns sorted list of FoodTableEntry object list
     *
     * @param foodTable
     * @param date
     * @return
     */
    public static List<FoodTableEntry> getExp4Date(FoodTable foodTable, LocalDate date) {
        List<FoodTableEntry> list = new ArrayList<>();
        for (FoodTableEntry entry :
                foodTable.getOrderedTable(FoodTableEntry::compareTo)) {
            if (entry.getExpDate() == date)
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
    public static List<FoodTableEntry> getTodaysExp(FoodTable foodTable) {
        return getExp4Date(foodTable, LocalDate.now());
    }

}

