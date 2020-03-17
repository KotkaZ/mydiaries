import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultTable<T> {
    private List<LocalDateTime> inputDate = new ArrayList<>();
    private List<T> tabelData = new ArrayList<>();


    public DefaultTable(List<LocalDateTime> inputDate, List<T> tabelData) {
        this.inputDate = inputDate;
        this.tabelData = tabelData;
    }

    public DefaultTable() {
    }


    public void addData(T data){
        inputDate.add(LocalDateTime.now());
        tabelData.add(data);
    }


    public Map<LocalDateTime, T> getTabel(){
        Map<LocalDateTime, T> defaultTable = new HashMap<>();
        for (int i = 0; i < inputDate.size(); i++) {
            defaultTable.put(inputDate.get(i),tabelData.get(i));
        }

        return defaultTable;
    }
}
