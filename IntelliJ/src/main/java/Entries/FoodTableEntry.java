package Entries;

import org.joda.time.LocalDate;

public class FoodTableEntry extends DefaultEntry implements Comparable{
    private LocalDate expDate;
    private int amount;
    private String unit;
    public static String[] UNITS = new String[]{"g", "kg", "ml", "l", "piece(s)", "package(s)"};

    public FoodTableEntry(String type, LocalDate expDate, int amount, int unitIndex) {
        super(type);
        this.expDate = expDate;
        this.amount = amount;
        this.unit = UNITS[unitIndex];
    }

    public LocalDate getExpDate() {
        return expDate;
    }


    public int getAmount() {
        return amount;
    }

    public String getUnit() {
        return unit;
    }

    /**
     * Shows difference between two FoodTableEntry class objects
     *
     * @param o FoodTabelEntry class object
     * @return Difference between expatriation date
     */
    @Override
    public int compareTo(Object o) {
        if (!(o instanceof FoodTableEntry))
            throw new IllegalArgumentException();
        return this.expDate.compareTo(((FoodTableEntry)o).getExpDate());
    }
}
