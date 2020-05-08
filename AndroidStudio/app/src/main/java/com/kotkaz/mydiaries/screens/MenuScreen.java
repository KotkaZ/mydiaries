package com.kotkaz.mydiaries.screens;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.kotkaz.mydiaries.R;
import com.kotkaz.mydiaries.diary.tables.DefaultTable;
import com.kotkaz.mydiaries.diary.tables.ExerciseTable;
import com.kotkaz.mydiaries.diary.tables.FoodTable;
import com.kotkaz.mydiaries.diary.tables.MoneyTable;
import com.kotkaz.mydiaries.diary.tables.ToDoTable;
import com.kotkaz.mydiaries.diary.tools.TableManager;

public class MenuScreen extends AppCompatActivity {

    /**
     * Main method. Shows menu screen.
     *
     * @param savedInstanceState lastSavedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.menu_screen);
        setOnClickListeners();
    }


    /**
     * Setting up all onClickListeners.
     */
    private void setOnClickListeners() {
        //Exit button will close activity.
        Button buttonExit = findViewById(R.id.btnExit);
        buttonExit.setOnClickListener(v -> finish());


        Button buttonFoodTable = findViewById(R.id.btnFoodList);
        buttonFoodTable.setOnClickListener(v -> {

            FoodTable foodTable = new FoodTable();
            TableManager.loadTable(getApplicationContext(), foodTable, "food_table.json");
            showUpDiaryScreen(foodTable);
        });


        Button buttonExerciseTable = findViewById(R.id.btnExerciseList);
        buttonExerciseTable.setOnClickListener(v -> {

            ExerciseTable exerciseTable = new ExerciseTable();
            TableManager.loadTable(getApplicationContext(), exerciseTable, "exercise_table.json");
            showUpDiaryScreen(exerciseTable);
        });


        Button buttonToDoTable = findViewById(R.id.btnToDoList);
        buttonToDoTable.setOnClickListener(v -> {

            ToDoTable toDoTable = new ToDoTable();
            TableManager.loadTable(getApplicationContext(), toDoTable, "todo _table.json");
            showUpDiaryScreen(toDoTable);
        });


        Button buttonMoneyTable = findViewById(R.id.btnMoneyList);
        buttonMoneyTable.setOnClickListener(v -> {

            MoneyTable moneyTable = new MoneyTable();
            TableManager.loadTable(getApplicationContext(), moneyTable, "money_table.json");
            showUpDiaryScreen(moneyTable);
        });
    }


    /**
     * ShowUpDiaryScreen closes current screen & activity and shows diary screen according to table.
     *
     * @param defaultTable Table (Food, Money, Exercise...) Will be passed via intent extras.
     */
    private void showUpDiaryScreen(DefaultTable defaultTable) {
        Intent diaryScreenIntent = new Intent(this, DiaryScreen.class);
        finish();  //Kill the activity from which you will go to next activity
        diaryScreenIntent.putExtra("TABLE", defaultTable); //Passes table as intent extra.
        startActivity(diaryScreenIntent);

    }
}
