package Entries;

import org.joda.time.LocalDate;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FoodTableEntryTest{
    private static LocalDate inputDate = LocalDate.now();
    private static String type = "eggs";

    private static LocalDate expDate = LocalDate.parse("2020-01-01") ;
    private static int amount = 5;
    private static String unit = "g";
    private static int unitIndex = 0;
    public static String[] UNITS = new String[]{"g", "kg", "ml", "l", "piece(s)", "package(s)"};

    @Test
    public void savesValues(){
        FoodTableEntry foodTableEntry = new FoodTableEntry(type,expDate,amount,unitIndex);
        assertEquals(foodTableEntry.getInputDate(), inputDate);
        assertEquals(foodTableEntry.getType(), type);
        assertEquals(foodTableEntry.getAmount(), amount);
        assertEquals(foodTableEntry.getExpDate(), expDate);
        assertEquals(foodTableEntry.getUnit(), UNITS[unitIndex]);
    }




}