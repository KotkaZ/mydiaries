package Tools;

import java.io.Serializable;

public class unitItem implements Serializable {
    private int kogus;
    private String ühik;

    public unitItem(int kogus, String ühik) {
        this.kogus = kogus;
        this.ühik = ühik;
    }

    @Override
    public String toString() {
        return kogus + " " + ühik;
    }
}
