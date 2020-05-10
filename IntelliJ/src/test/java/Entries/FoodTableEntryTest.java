package Entries;

import unitItem.Toode;
import org.joda.time.LocalDate;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FoodTableEntryTest{
    private static LocalDate inputDate = LocalDate.now();
    private static String type = "eggs";

    private static LocalDate expDate = LocalDate.parse("2020-01-01") ;
    private static int amount = 5;
    private static String unit = "g";
    public static String[] UNITS = new String[]{"g", "kg", "ml", "l", "piece(s)", "package(s)"};
    final private Toode toode = new Toode(amount, unit);

    @Test
    public void savesValues(){
        FoodTableEntry foodTableEntry = new FoodTableEntry(type,expDate,amount,unit);
        assertEquals(foodTableEntry.getInputDate(), inputDate);
        assertEquals(foodTableEntry.getType(), type);
        assertEquals(foodTableEntry.getExpDate(), expDate);
        assertEquals(foodTableEntry.getToode().toString(), toode.toString());
    }

}