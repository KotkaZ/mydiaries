package Tables;

import Entries.ExerciseTableEntry;
import org.joda.time.LocalDateTime;

import java.util.List;

public class ExerciseTable extends DefaultTable<Entries.ExerciseTableEntry> {


    public void addData(LocalDateTime exerciseDate, String type, int length, String description, String location) {
        Entries.ExerciseTableEntry entries = new Entries.ExerciseTableEntry( type, exerciseDate, length, description, location);
        super.addData(entries);
    }

    /**
     * Sums total length of exercises.
     *
     * @return
     */
    public int totalLenght(){
        int totalLenght = 0;
        for (ExerciseTableEntry entry :
                this.getTabel()) {
            totalLenght+= entry.getLength();
        }
        return totalLenght;
    }


    /**
     * This method orders list by Entry-class object variables.
     *
     * @param type Int typenumber
     *             0- type
     *             1- inputdate
     *             2- excerciseDate
     *             3- lenght;
     *             4- location
     *             5- description
     * @param ascending
     * @return
     */
    public List<ExerciseTableEntry> getOrderedTable(int type, boolean ascending){
        return super.getOrderedTable((o1, o2) -> {
            int value;
            switch (type){

                case 0: value = o1.getType().compareTo(o2.getType());break;
                case 1: value = o1.getInputDate().compareTo(o2.getInputDate());break;
                case 2: value = o1.getExerciseDate().compareTo(o2.getExerciseDate());break;
                case 3: value = Integer.compare(o1.getLength(), o2.getLength());break;
                case 4: value = o1.getLocation().compareTo(o2.getLocation());break;
                case 5: value = o1.getDescription().compareTo(o2.getDescription());break;
                default:
                    throw new IllegalArgumentException("ExcerciseTable#getOrderedTabel wrong ordering type");
            }
            return value  * (ascending ? 1 : -1);
        });
    }


}