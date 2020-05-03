package com.kotkaz.mydiaries.diary.tables;

import com.kotkaz.mydiaries.diary.entries.ExerciseTableEntry;

import org.joda.time.LocalDate;

public class ExerciseTable extends DefaultTable<ExerciseTableEntry> {


    public void addData(LocalDate exerciseDate, String type, int length, String description, String location) {
        ExerciseTableEntry entries = new ExerciseTableEntry(type, exerciseDate, length, description, location);
        super.addData(entries);
    }


}