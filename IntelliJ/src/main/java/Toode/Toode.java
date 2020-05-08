package Toode;

public class Toode {
    private int kogus;
    private String ühik;

    public Toode(int kogus, String ühik) {
        this.kogus = kogus;
        this.ühik = ühik;
    }

    @Override
    public String toString() {
        return  kogus + " " + ühik;
    }
}
