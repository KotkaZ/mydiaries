import java.time.LocalDateTime;

public class FoodListTable extends DefaultTable<Object[]> {


    public void addData(LocalDateTime expDate, String type, int amount) {
        Object[] data = new Object[]{expDate, type, amount};
        super.addData(data);
    }

}