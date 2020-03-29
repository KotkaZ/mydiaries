import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Test {

    public static void main(String[] args) {

        List<String> tableNames = new ArrayList<>();
        tableNames.add("money");
        tableNames.add("food");
        tableNames.add("exercise");
        tableNames.add("todo");

        List<String> actionNames = new ArrayList<>();
        actionNames.add("quit");
        actionNames.add("add");
        actionNames.add("show");

        System.out.println(LocalDateTime.now());


        while (true) {
            final Scanner scanner = new Scanner(System.in);
            System.out.println("Enter action: " + Arrays.toString(actionNames.toArray()));

            String action = scanner.next().toLowerCase();
            int selection = actionNames.indexOf(action);
            if (selection == 0) break;

            else if (selection == 1) {
                System.out.println("Enter table name: " + Arrays.toString(tableNames.toArray()));
                String table = scanner.next().toLowerCase();
                int tableSelection = tableNames.indexOf(table);
                if (tableSelection == 0)
                    addMoney(scanner);
                else if (tableSelection == 1)
                    addFood(scanner);
                else if (tableSelection == 2)
                    addExercise(scanner);
                else if (tableSelection == 3)
                    addTodo(scanner);

            } else if (selection == 2) {
                System.out.println("Enter table name: " + Arrays.toString(tableNames.toArray()));
                String table = scanner.next().toLowerCase();
                int tableSelection = tableNames.indexOf(table);
                if (tableSelection == 0)
                    showMoney();

                if (tableSelection == 1)
                    showFood(scanner);

                if (tableSelection == 2)
                    showExercise();

                if (tableSelection == 3)
                    showTodo(scanner);

            }


        }


    }

    /**
     * Adding movement of money (date, amount, movement type and description) to moneyTabel.
     * @param scanner Scanner to get user input.
     */
    private static void addMoney(Scanner scanner){
        final MoneyTable moneyTable = new MoneyTable();

        try {
            moneyTable.loadTable("moneyTable.json");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } //Only for Testing. TODO.

        System.out.println("Enter use/get date:");
        final LocalDateTime date = dateTimeControl(scanner);
        System.out.println("Enter amount:");
        final double amount = scanner.nextDouble();
        System.out.println("Enter type:");
        final String type = scanner.next();
        System.out.println("Enter desc.:");
        final String desc = scanner.next();

        moneyTable.addData(date, amount, type, desc);

        try {
            moneyTable.saveTabel("moneyTable.json");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } //Only for Testing. TODO.
    }

    /**
     * Adding food (expire date, amount, type) to foodTabel.
     * @param scanner Scanner to get user input.
     */
    private static void addFood(Scanner scanner){
        final FoodTable foodTable = new FoodTable();

        try {
            foodTable.loadTable("foodTable.json");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } //Only for Testing. TODO.

        System.out.println("Enter exp. date:");
        final LocalDateTime expDate = dateTimeControl(scanner);
        System.out.println("Enter amount:");
        final int amount = scanner.nextInt();
        System.out.println("Enter type:");
        final String type = scanner.next();

        foodTable.addData(expDate, type, amount);

        try {
            foodTable.saveTabel("foodTable.json");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } //Only for Testing. TODO.
    }

    /**
     * Adding exercise (date, length, type, description, location) to excerciseTabel.
     * @param scanner Scanner to get user input.
     */
    private static void addExercise(Scanner scanner){
        final ExerciseTable exerciseTable = new ExerciseTable();

        try {
            exerciseTable.loadTable("exerciseTable.json");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } //Only for Testing. TODO.

        System.out.println("Enter date:");
        final LocalDateTime date = dateTimeControl(scanner);
        System.out.println("Enter length:");
        final double amount = scanner.nextDouble();
        System.out.println("Enter type:");
        final String type = scanner.next();
        System.out.println("Enter desc.:");
        final String desc = scanner.next();
        System.out.println("Enter location:");
        final String location = scanner.next();


        exerciseTable.addData(date, type, amount, desc, location);

        try {
            exerciseTable.saveTabel("exerciseTable.json");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } //Only for Testing. TODO.
    }

    /**
     * Adding exercise (deadline, type, priority, description) to toDoTabel.
     * @param scanner Scanner to get user input.
     */
    private static void addTodo(Scanner scanner){
        final ToDoTable toDoTable = new ToDoTable();

        try {
            toDoTable.loadTable("toDoTable.json");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } //Only for Testing. TODO.

        System.out.println("Enter deadline:");
        final LocalDateTime date = dateTimeControl(scanner);

        System.out.println("Enter type:");
        final String type = scanner.next();
        System.out.println("Enter priority:");
        final int amount = scanner.nextInt();
        System.out.println("Enter desc.:");
        final String desc = scanner.next();

        toDoTable.addData(date, desc, amount, type);

        try {
            toDoTable.saveTabel("toDoTable.json");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } //Only for Testing. TODO.
    }

    /**
     * Prints moneytabel JSON file content.
     */
    private static void showMoney(){
        final MoneyTable moneyTable = new MoneyTable();

        try {
            moneyTable.loadTable("moneyTable.json");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } //Only for Testing. TODO.

        final Map<LocalDateTime, Entries.MoneyTableEntry> tabel = moneyTable.getTabel();
        for (Map.Entry<LocalDateTime, Entries.MoneyTableEntry> entry : tabel.entrySet()) {
            System.out.printf("%-10s; %-10s; %-10s; %-10s; %-10s",
                    entry.getKey(), entry.getValue().getAmount(), entry.getValue().getType(),
                    entry.getValue().getUseDate(), entry.getValue().getDescription());
        }
    }

    /**
     * Prints foodtabel JSON file content.
     */
    private static void showFood(Scanner scanner){
        final FoodTable foodTable = new FoodTable();

        try {
            foodTable.loadTable("foodTable.json");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } //Only for Testing. TODO.

        System.out.println("Do you want sorted data? (y/n)");
        String answer = scanner.next();

        Map<LocalDateTime, Entries.FoodTableEntry> tabel = foodTable.getTabel();
        if (answer.equalsIgnoreCase("y"))
            tabel = foodTable.getOrderedByPriority();


        for (Map.Entry<LocalDateTime, Entries.FoodTableEntry> entry : tabel.entrySet()) {
            System.out.printf("%-10s; %-10s; %-10s; %-10s",
                    entry.getKey(), entry.getValue().getExpDate(), entry.getValue().getAmount(),
                    entry.getValue().getType());
        }
    }

    /**
     * Prints exercisetabel JSON file content.
     */
    private static void showExercise(){
        final ExerciseTable exerciseTable = new ExerciseTable();

        try {
            exerciseTable.loadTable("exerciseTable.json");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } //Only for Testing. TODO.

        final Map<LocalDateTime, Entries.ExerciseTableEntry> tabel = exerciseTable.getTabel();
        for (Map.Entry<LocalDateTime, Entries.ExerciseTableEntry> entry : tabel.entrySet()) {
            System.out.printf("%-10s; %-10s; %-10s; %-10s; %-10s; %-10s",
                    entry.getKey(), entry.getValue().getExerciseDate(), entry.getValue().getLength(),
                    entry.getValue().getType(), entry.getValue().getDescription(), entry.getValue().getLocation());
        }
    }

    /**
     * Prints todotabel JSON file content.
     */
    private static void showTodo(Scanner scanner){
        final ToDoTable toDoTable = new ToDoTable();

        try {
            toDoTable.loadTable("toDoTable.json");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } //Only for Testing. TODO.

        Map<LocalDateTime, Entries.ToDoTableEntry> tabel = toDoTable.getTabel();
        System.out.println("Do you want sorted data? (y/n)");
        String answer = scanner.next();
        if (answer.equalsIgnoreCase("y"))
            tabel = toDoTable.getOrderedByExpDate();

        for (Map.Entry<LocalDateTime, Entries.ToDoTableEntry> entry : tabel.entrySet()) {
            System.out.printf("%-10s; %-10s; %-10s; %-10s; %-10s",
                    entry.getKey(), entry.getValue().getDeadline(), entry.getValue().getPriority(),
                    entry.getValue().getType(), entry.getValue().getDescription());
        }
    }



    /**
     * Kontrollib, kas kuupäev on õigesti sisestatud ja kas on mõistlik sisestus.
     * @param scanner
     * @return Scannerist saadud kuupäev ajaga.
     */
    private static LocalDateTime dateTimeControl(Scanner scanner) {
        LocalDateTime date;
        String sDate;
        while (true) {
            sDate = scanner.next();
            //Kui teikib viga scanneriga, läheb catch plokki.
            try {
                //Paneb pange
                //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                //date = LocalDateTime.parse(sDate, formatter);
                date = LocalDateTime.parse(sDate);
                //Kontrollib, kas kuupäev on minevikust või rohkem kui
                // 10 aastat praegusest ja annab võimaluse parandada.
                if (0 < date.compareTo(LocalDateTime.now()) || 0 < date.compareTo(LocalDateTime.now().plusYears(10)))
                    break;
                else {
                    System.out.println("Are you sure that this date " + date + "is right? (y/n)");
                    String vastus = scanner.next().toLowerCase();
                    if (vastus.equals("y"))
                        break;
                    else
                        continue;
                }

            } catch (DateTimeParseException e) {
                System.out.println("Date input has to be in form yyyy-MM-dd HH:mm");
            }
        }
        return date;
    }

}
