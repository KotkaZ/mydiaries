import java.time.LocalDateTime;

public class Entries {

    static class TextEntry{
        private String text;

        public TextEntry(String text) {
            this.text = text;
        }
    }

    static class FoodTableEntry  implements Comparable<FoodTableEntry> {
        private LocalDateTime expDate;
        private String type;
        private int amount;

        public FoodTableEntry(LocalDateTime expDate, String type, int amount) {
            this.expDate = expDate;
            this.type = type;
            this.amount = amount;
        }

        public LocalDateTime getExpDate() {
            return expDate;
        }

        public String getType() {
            return type;
        }

        public int getAmount() {
            return amount;
        }

        //minevikule lähemad tooted eespool
        @Override
        public int compareTo(FoodTableEntry o) {
            return this.expDate.compareTo(o.expDate);
        }
    }

    static class MoneyTableEntry{
        private LocalDateTime useDate;
        private double amount;
        private String type;
        private String description;

        public MoneyTableEntry(LocalDateTime useDate, double amount, String type, String description) {
            this.useDate = useDate;
            this.amount = amount;
            this.type = type;
            this.description = description;
        }

        public LocalDateTime getUseDate() {
            return useDate;
        }

        public double getAmount() {
            return amount;
        }

        public String getType() {
            return type;
        }

        public String getDescription() {
            return description;
        }
    }

    static class ToDoTableEntry implements Comparable<ToDoTableEntry> {
        private LocalDateTime deadline;
        private String description;
        private int priority;
        private String type;

        public ToDoTableEntry(LocalDateTime deadline, String description, int priority, String type) {
            this.deadline = deadline;
            this.description = description;
            this.priority = priority;
            this.type = type;
        }

        public LocalDateTime getDeadline() {
            return deadline;
        }

        public String getDescription() {
            return description;
        }

        public int getPriority() {
            return priority;
        }

        public String getType() {
            return type;
        }

        //Kõrgem prioriteet eespool ja siis minevikule lähemad ülesanded eespool
        @Override
        public int compareTo(ToDoTableEntry o) {
            int dif = this.priority-o.priority;
            if (dif == 0)
                dif = this.deadline.compareTo(o.deadline);
            return dif;
        }
    }

    static class ExerciseTableEntry {
        private LocalDateTime exerciseDate;
        private String type;
        private double length;
        private String description;
        private String location;

        public ExerciseTableEntry(LocalDateTime exerciseDate, String type, double length, String description, String location) {
            this.exerciseDate = exerciseDate;
            this.type = type;
            this.length = length;
            this.description = description;
            this.location = location;
        }

        public LocalDateTime getExerciseDate() {
            return exerciseDate;
        }

        public String getType() {
            return type;
        }

        public double getLength() {
            return length;
        }

        public String getDescription() {
            return description;
        }

        public String getLocation() {
            return location;
        }
    }


}


