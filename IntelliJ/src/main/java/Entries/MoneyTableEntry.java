package Entries;

import org.joda.time.LocalDate;

public class MoneyTableEntry  extends DefaultEntry implements Comparable{
    private LocalDate useDate;
    private double amount;
    private String description;

    public MoneyTableEntry(String type, LocalDate useDate, double amount, String description) {
        super(type);
        this.useDate = useDate;
        this.amount = amount;
        this.description = description;
    }

    public LocalDate getUseDate() {
        return useDate;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int compareTo(Object o) {
        return this.getUseDate().compareTo(((MoneyTableEntry)o).getUseDate());
    }
}
