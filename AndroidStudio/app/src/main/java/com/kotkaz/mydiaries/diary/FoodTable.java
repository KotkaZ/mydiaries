package com.kotkaz.mydiaries.diary;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FoodTable extends DefaultTable<Entries.FoodTableEntry> {


    public void addData(LocalDate expDate, String type, int amount) {
        Entries.FoodTableEntry entries = new Entries.FoodTableEntry(expDate, type, amount);
        super.addData(entries);
    }

    /**
     * Sorteerib kuupäeva alusel, vanemad ees, ja väljastab tabeli kujul.
     */
    public Map<LocalDate, Entries.FoodTableEntry> getOrderedByPriority() {
        Map<LocalDate, Entries.FoodTableEntry> toDoTableEntryMap = getTabel();

        List<Map.Entry<LocalDate, Entries.FoodTableEntry>> entries =
                new ArrayList<>(toDoTableEntryMap.entrySet());
        entries.sort(Map.Entry.comparingByValue());
        Map<LocalDate, Entries.FoodTableEntry> orderedByPriority = new LinkedHashMap<>();
        for (Map.Entry<LocalDate, Entries.FoodTableEntry> entry : entries) {
            orderedByPriority.put(entry.getKey(), entry.getValue());
        }

        return orderedByPriority;
    }
}

