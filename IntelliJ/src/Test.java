import java.time.LocalDateTime;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws InterruptedException {


        //Tavalise teksti päeviku näide.
        /*
        final DefaultTable<String> defaultTable = new DefaultTable<>();
        defaultTable.addData("Vahva maailm!");
        defaultTable.addData("Täna on ilus päev!");

        defaultTable.setName("test.json");
        defaultTable.saveTable();

        final DefaultTable<String> newDefaultTable = new DefaultTable<>();
        newDefaultTable.setName("test.json");
        newDefaultTable.loadTable();

        Map tabel = newDefaultTable.getTabel();
        for (Object o : tabel.keySet()) {
            System.out.println(o.toString() + ":\t" + tabel.get(o));
        }*/

        final FoodListTable foodListTable = new FoodListTable();
        foodListTable.addData(LocalDateTime.now().plusDays(10),"Eggs",10);

        Map<LocalDateTime,Entries.FoodTableEntry> foodTableEntryMap = foodListTable.getTabel();
        for (LocalDateTime o : foodTableEntryMap.keySet()) {
            //System.out.println(o.toString());
            Entries.FoodTableEntry foodTableEntry = foodTableEntryMap.get(o);
            System.out.println("ExpDate: " + foodTableEntry.getExpDate());
            System.out.println(foodTableEntry.getType());
            System.out.println(foodTableEntry.getAmount());
        }

        //Rahapäeviku näide.
        /*
        final MoneyTable moneyTable = new MoneyTable();
        moneyTable.addData(LocalDateTime.now(),-10.2,"Toit","Kivilinna konsum");
        Thread.sleep(10);
        moneyTable.addData(LocalDateTime.now().plusDays(1),1000,"Palk","");
        Thread.sleep(10);
        moneyTable.addData(LocalDateTime.now().plusDays(3),160,"Stipendium","");
        Thread.sleep(10);
        moneyTable.addData(LocalDateTime.now().plusDays(1),1200,"Palk","");
        Map moneyTabel = moneyTable.getTabel();
        System.out.println(moneyTabel.keySet().size());
        for (Object o : moneyTabel.keySet()) {
            Object[] objects =  (Object[]) moneyTabel.get(o);
            System.out.print("Sissekanne: " +o.toString());
            System.out.print("\tKulutamise aeg: " +objects[0].toString());
            System.out.print("\tKulutatud summa: " +objects[1].toString());
            System.out.print("\tTüüp: " +objects[2].toString());
            System.out.println("\tKirjeldus: " +objects[3].toString());
        }*/

    }
}
