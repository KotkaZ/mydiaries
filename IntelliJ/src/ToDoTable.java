import java.time.LocalDateTime;

public class ToDoTable extends DefaultTable<Object[]> {


    public void addData(LocalDateTime deadline, String description, int priority, String type) {
        Object[] data = new Object[]{deadline, description, priority, type};
        super.addData(data);
    }

}