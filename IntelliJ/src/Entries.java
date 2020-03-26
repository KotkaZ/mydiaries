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

}
