package com.kotkaz.mydiaries.diary;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;

public class ExerciseTable extends DefaultTable<Entries.ExerciseTableEntry> {


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addData(LocalDateTime exerciseDate, String type, double length, String description, String location) {
        Entries.ExerciseTableEntry entries = new Entries.ExerciseTableEntry(exerciseDate, type, length, description, location);
        super.addData(entries);
    }


}