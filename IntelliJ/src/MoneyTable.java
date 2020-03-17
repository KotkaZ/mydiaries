import java.time.LocalDateTime;

public class MoneyTable extends DefaultTable<Object[]> {


    public void addData(LocalDateTime useDate, double amount, String type, String description) {
        Object[] data = new Object[]{useDate, amount, type, description};
        super.addData(data);
    }

}
