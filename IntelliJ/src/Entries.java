import java.time.LocalDateTime;

public class Entries {

    static class StringEntry{
        private String string;

        public StringEntry(String string) {
            this.string = string;
        }
    }

    static class FoodTableEntry{
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
    }

    static class MoneyEntry{
        private LocalDateTime useDate;
        private double amount;
        private String type;
        private String description;

        public MoneyEntry(LocalDateTime useDate, double amount, String type, String description) {
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


}


