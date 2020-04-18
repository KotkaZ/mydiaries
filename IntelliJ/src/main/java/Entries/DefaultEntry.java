package Entries;


import org.joda.time.LocalDate;

public class DefaultEntry {
    private LocalDate inputDate;
    private String type;
    // TODO: 17/04/2020


    public DefaultEntry(String type) {
        this.inputDate = LocalDate.now();
        this.type = type;
    }

    public LocalDate getInputDate() {
        return inputDate;
    }

    public String getType() {
        return type;
    }
}
