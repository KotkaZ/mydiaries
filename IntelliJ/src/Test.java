import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Test {
    private static final Set<String> tS = Set.of("toit", "treening", "todo", "raha");


    public static void main(String[] args) throws InterruptedException {
        List<String> tableNames = new ArrayList<>();
        tableNames.add("raha");
        tableNames.add("toit");
        tableNames.add("treening");
        tableNames.add("todo");
        int selected = -1;

        while (true) {
            Scanner tableSelection = new Scanner(System.in);
            System.out.println("Sisestage, millist tabelit tahate avada: (raha, treening, ToDo, toit)");
            System.out.println("Väljumiseks, sisestage 'quit'");
            String tS = tableSelection.nextLine().toLowerCase();

            if (tableNames.contains(tS)) {
                selected = tableNames.indexOf(tS);
                break;
            } else if (tS.equals("quit")) return;
        }

        if (selected == 1) {
            final FoodTable FoodTable = new FoodTable();
            FoodTable.setName("foodlist.json");
            FoodTable.loadTable();
            while (true) {
                final Scanner scanner = new Scanner(System.in);
                if (scanner.nextLine().equals("valmis")) break;
                System.out.println("Sisesta exp date:");
                final LocalDateTime date = dateTimeControl(scanner);
                System.out.println("Sisesta toode:");
                final String type = scanner.next();
                System.out.println("Sisesta kogus");
                final int amount = scanner.nextInt();
                FoodTable.addData(date, type, amount);
            }
            FoodTable.saveTable();
        }


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

        /*
        final FoodTable foodListTable = new FoodTable();

        foodListTable.addData(LocalDateTime.now().plusDays(10),"Eggs",10);

        Map<LocalDateTime,Entries.FoodTableEntry> foodTableEntryMap = foodListTable.getTabel();
        for (LocalDateTime o : foodTableEntryMap.keySet()) {
            //System.out.println(o.toString());
            Entries.FoodTableEntry foodTableEntry = foodTableEntryMap.get(o);
            System.out.println("ExpDate: " + foodTableEntry.getExpDate());
            System.out.println(foodTableEntry.getType());
            System.out.println(foodTableEntry.getAmount());
        }
        */


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

    /**
     * Kontrollib, kas kuupäev on õigesti sisestatud ja kas on mõistlik sisestus.
     * @param scanner
     * @return Scannerist saadud kuupäev ajaga.
     */
    private static LocalDateTime dateTimeControl(Scanner scanner) {
        LocalDateTime date;
        while (true) {
            String sDate = scanner.next();

            //Kui teikib viga scanneriga, läheb catch plokki.
            try {
                date = LocalDateTime.parse(sDate);

                //Kontrollib, kas kuupäev on minevikust või rohkem kui
                // 10 aastat praegusest ja annab võimaluse parandada.
                if (0 < date.compareTo(LocalDateTime.now()) || 0 < date.compareTo(LocalDateTime.now().plusYears(10)))
                    break;
                else {
                    System.out.println("Oled kindel, et see kuupäev " + date + "on õige? (y/n)");
                    String vastus = scanner.next().toLowerCase();
                    if (vastus.equals("y"))
                        break;
                }

            } catch (DateTimeParseException e) {
                System.out.println("Kuupäeav peab olema kujul AAAA-KK-PP");
                continue;
            }
        }
        return date;
    }

}
