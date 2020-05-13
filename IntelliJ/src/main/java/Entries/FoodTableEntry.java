package Entries;

import Tools.unitItem;
import org.joda.time.LocalDate;

public class FoodTableEntry extends DefaultEntry {
    private LocalDate expDate;
    private unitItem unitItem;
    public static String[] UNITS = new String[]{"g", "kg", "ml", "l", "piece(s)", "package(s)"};

    public FoodTableEntry(String type, LocalDate expDate, int amount, String unit) {
        super(type);
        this.expDate = expDate;
        this.unitItem = new unitItem(amount, unit);
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    public unitItem getToode() {
        return unitItem;
    }

    /**
     * Shows difference between two FoodTableEntry class objects
     *
     * @param o FoodTabelEntry class object
     * @return Difference between expatriation date
     */
    /*@Override
    public int compareTo(Object o) {
        if (!(o instanceof FoodTableEntry))
            throw new IllegalArgumentException();
        return this.expDate.compareTo(((FoodTableEntry)o).getExpDate());
    }
         */
}
