package com.kotkaz.mydiaries.diary.tables;

import com.kotkaz.mydiaries.diary.entries.ToDoTableEntry;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ToDoTable extends DefaultTable<ToDoTableEntry> {


    public void addData(String type, LocalDate deadline, String description, int priority) {
        ToDoTableEntry entries = new ToDoTableEntry(type, deadline, description, priority);
        super.addData(entries);
    }

    /**
     * Returns sorted list of tasks, that deadlines is on given date.
     *
     * @param toDoTable
     * @param date
     * @return
     */
    public static List<ToDoTableEntry> getTasks4Date(ToDoTable toDoTable, LocalDate date) {
        List<ToDoTableEntry> list = new ArrayList<>();
        for (ToDoTableEntry entry :
                toDoTable.getTabel()) {
            if (entry.getDeadline() == date)
                list.add(entry);
        }
        Collections.sort(list);
        return list;
    }

    /**
     * Returns sorted list of today's tasks.
     *
     * @param toDoTable
     * @return
     */
    public static List<ToDoTableEntry> getTodaysTasks(ToDoTable toDoTable) {
        return getTasks4Date(toDoTable, LocalDate.now());
    }


}



