package com.kotkaz.mydiaries.diary.entries;


import org.joda.time.LocalDate;

import java.io.Serializable;

public class DefaultEntry implements Serializable {
    private LocalDate inputDate;
    private String type;
    // TODO: 17/04/2020


    DefaultEntry(String type) {
        this.inputDate = LocalDate.now();
        this.type = type;
    }

    public LocalDate getInputDate() {
        return inputDate;
    }

    public String getType() {
        return type;
    }
}
