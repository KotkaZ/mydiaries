package com.kotkaz.mydiaries.screens;

import android.app.Activity;
import android.widget.Button;

import com.kotkaz.mydiaries.R;
import com.kotkaz.mydiaries.diary.tables.ExerciseTable;
import com.kotkaz.mydiaries.diary.tables.FoodTable;
import com.kotkaz.mydiaries.diary.tables.MoneyTable;
import com.kotkaz.mydiaries.diary.tables.ToDoTable;

import java.io.IOException;

public class MenuScreen {

    private Activity activity;

    public MenuScreen(Activity activity) {
        this.activity = activity;


        this.activity.setContentView(R.layout.menu_screen);

        Button buttonExit = this.activity.findViewById(R.id.btnExit);
        buttonExit.setOnClickListener(v -> System.exit(0));


        Button buttonFoodTable = this.activity.findViewById(R.id.btnFoodList);
        buttonFoodTable.setOnClickListener(v -> {
            FoodTable foodTable = new FoodTable();
            try {
                foodTable.loadTable("food_table.json");
            } catch (IOException e) {
                System.out.println("No saved table found!");
            }
            DiaryScreen diaryScreen = new DiaryScreen(this.activity, foodTable);
        });

        Button buttonExerciseTable = this.activity.findViewById(R.id.btnExerciseList);
        buttonExerciseTable.setOnClickListener(v -> {
            ExerciseTable exerciseTable = new ExerciseTable();
            try {
                exerciseTable.loadTable("exercise_table.json");
            } catch (IOException e) {
                System.out.println("No saved table found!");
            }
            DiaryScreen diaryScreen = new DiaryScreen(this.activity, exerciseTable);
        });

        Button buttonToDoTable = this.activity.findViewById(R.id.btnToDoList);
        buttonToDoTable.setOnClickListener(v -> {
            ToDoTable toDoTable = new ToDoTable();
            try {
                toDoTable.loadTable("todo _table.json");
            } catch (IOException e) {
                System.out.println("No saved table found!");
            }
            DiaryScreen diaryScreen = new DiaryScreen(this.activity, toDoTable);
        });

        Button buttonMoneyTable = this.activity.findViewById(R.id.btnMoneyList);
        buttonMoneyTable.setOnClickListener(v -> {
            MoneyTable moneyTable = new MoneyTable();
            try {
                moneyTable.loadTable("money_table.json");
            } catch (IOException e) {
                System.out.println("No saved table found!");
            }
            DiaryScreen diaryScreen = new DiaryScreen(this.activity, moneyTable);
        });
    }
}
