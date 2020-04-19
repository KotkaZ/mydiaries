package Entries;

import org.joda.time.LocalDate;

public class FoodTableEntry extends DefaultEntry implements Comparable{
    private LocalDate expDate;
    private int amount;
    public static String[] UNITS = new String[]{"g", "kg", "ml", "l", "piece(s)", "package(s)"};

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


    /**
     * Shows difference between two FoodTableEntry class objects
     *
     * @param o FoodTabelEntry class object
     * @return Difference between expatriation date
     */
    @Override
    public int compareTo(Object o) {
        if (! o.getClass().getTypeName().equals("FoodTableEntry"))
            throw new IllegalArgumentException();
        return this.expDate.compareTo(((FoodTableEntry)o).getExpDate());
    }
}
