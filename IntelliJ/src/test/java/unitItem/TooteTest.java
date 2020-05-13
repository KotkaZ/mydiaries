package unitItem;

import Tools.unitItem;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TooteTest {
    final private int amount = 5;
    final private String unit = "kg";
    final private String str = amount +" " + unit;
    final private unitItem toode = new unitItem(amount, unit);
    @Test
    public void savedToode(){
        assertEquals(toode.toString(),str);
    }
}
