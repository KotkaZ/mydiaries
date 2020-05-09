package com.kotkaz.mydiaries.diary.tables;


import com.kotkaz.mydiaries.diary.entries.ToDoTableEntry;

import org.joda.time.LocalDateTime;

import java.util.List;

public class ToDoTable extends DefaultTable<ToDoTableEntry> {


    public void addData(String type, LocalDateTime deadline, String description, int priority) {
        ToDoTableEntry entries = new ToDoTableEntry(type, deadline, description, priority);
        super.addData(entries);
    }

    /**
     * This method orderes list by Entry-class object variables.
     *
     * @param type      Int typenumber
     *                  0- type
     *                  1- inputdate
     *                  2- deadline
     *                  3- priority
     * @param ascending
     * @return
     */
    public List<ToDoTableEntry> getOrderedTable(int type, boolean ascending) {
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
                    value = o1.getDeadline().compareTo(o2.getDeadline());
                    break;
                case 3:
                    value = Integer.compare(o1.getPriority(), o2.getPriority());
                    break;
                default:
                    throw new IllegalArgumentException("ToDoTable#getOrderedTabel wrong ordering type");
            }
            return value * (ascending ? 1 : -1);
        });
    }

}



