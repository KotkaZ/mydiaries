package com.kotkaz.mydiaries.diary.entries;


import com.kotkaz.mydiaries.diary.tools.unitItem;

import org.joda.time.LocalDate;

public class FoodTableEntry extends DefaultEntry {
    private LocalDate expDate;
    private unitItem toode;

    public FoodTableEntry(String type, LocalDate expDate, int amount, String unit) {
        super(type);
        this.expDate = expDate;
        this.toode = new unitItem(amount, unit);
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    public unitItem getToode() {
        return toode;
    }

}
