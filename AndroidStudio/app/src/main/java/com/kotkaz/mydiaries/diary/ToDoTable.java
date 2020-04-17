package com.kotkaz.mydiaries.diary;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ToDoTable extends DefaultTable<Entries.ToDoTableEntry> {


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addData(LocalDateTime deadline, String description, int priority, String type) {
        Entries.ToDoTableEntry entries = new Entries.ToDoTableEntry(deadline, description, priority, type);
        super.addData(entries);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public Map<LocalDateTime, Entries.ToDoTableEntry> getOrderedByExpDate() {
        Map<LocalDateTime, Entries.ToDoTableEntry> toDoTableEntryMap = getTabel();

        List<Map.Entry<LocalDateTime, Entries.ToDoTableEntry>> entries =
                new ArrayList<>(toDoTableEntryMap.entrySet());
        entries.sort(Map.Entry.comparingByValue());
        Map<LocalDateTime, Entries.ToDoTableEntry> orderedByExpDate = new LinkedHashMap<>();
        for (Map.Entry<LocalDateTime, Entries.ToDoTableEntry> entry : entries) {
            orderedByExpDate.put(entry.getKey(), entry.getValue());
        }

        return orderedByExpDate;
    }
}


