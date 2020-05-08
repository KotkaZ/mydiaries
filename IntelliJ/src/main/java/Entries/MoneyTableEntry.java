package Entries;

import org.joda.time.LocalDateTime;

public class MoneyTableEntry extends DefaultEntry {
    private LocalDateTime useDate;
    private double amount;
    private String description;

    public MoneyTableEntry(String type, LocalDateTime useDate, double amount, String description) {
        super(type);
        this.useDate = useDate;
        this.amount = amount;
        this.description = description;
    }

    public LocalDateTime getUseDate() {
        return useDate;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    /*
    @Override
    public int compareTo(Object o) {
        return this.getUseDate().compareTo(((MoneyTableEntry)o).getUseDate());
    }
     */
}
