package Entries;

import org.joda.time.LocalDate;

public class MoneyTableEntry {
    private LocalDate useDate;
    private double amount;
    private String type;
    private String description;

    public MoneyTableEntry(LocalDate useDate, double amount, String type, String description) {
        this.useDate = useDate;
        this.amount = amount;
        this.type = type;
        this.description = description;
    }

    public LocalDate getUseDate() {
        return useDate;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
