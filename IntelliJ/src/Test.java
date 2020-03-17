import java.time.LocalDateTime;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws InterruptedException {


        //Tavalise teksti päeviku näide.

        final DefaultTable<String> tDefaultTable = new DefaultTable<>();
        tDefaultTable.addData("Tere päevik!");
        tDefaultTable.addData("Täna on ilus päev!");
        Map tabel = tDefaultTable.getTabel();
        for (Object o : tabel.keySet()) {
            System.out.println(o.toString() + ":\t" + tabel.get(o));
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
