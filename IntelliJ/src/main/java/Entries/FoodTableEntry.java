package Entries;

import org.joda.time.LocalDate;

public class FoodTableEntry extends DefaultEntry{
    private LocalDate expDate;
    private int amount;

    public FoodTableEntry(String type, LocalDate expDate, int amount) {
        super(type);
        this.expDate = expDate;
        this.amount = amount;
    }

    public LocalDate getExpDate() {
        return expDate;
    }


    public int getAmount() {
        return amount;
    }

}
