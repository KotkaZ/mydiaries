package Entries;

import org.joda.time.LocalDate;

public class FoodTableEntry {
    private LocalDate expDate;
    private String type;
    private int amount;

    public FoodTableEntry(LocalDate expDate, String type, int amount) {
        this.expDate = expDate;
        this.type = type;
        this.amount = amount;
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    public String getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

}
