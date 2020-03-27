import java.time.LocalDateTime;

public class FoodTable extends DefaultTable<Entries.FoodTableEntry> {


    public void addData(LocalDateTime expDate, String type, int amount) {
        Entries.FoodTableEntry entries = new Entries.FoodTableEntry(expDate,type,amount);
        super.addData(entries);
    }

}