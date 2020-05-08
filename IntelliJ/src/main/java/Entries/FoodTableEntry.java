package Entries;

import Toode.Toode;
import org.joda.time.LocalDate;

public class FoodTableEntry extends DefaultEntry implements Comparable{
    private LocalDate expDate;
    private Toode toode;
    public static String[] UNITS = new String[]{"g", "kg", "ml", "l", "piece(s)", "package(s)"};

    public FoodTableEntry(String type, LocalDate expDate, int amount, String unit) {
        super(type);
        this.expDate = expDate;
        this.toode = new Toode(amount,unit);
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    public Toode getToode() {
        return toode;
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
