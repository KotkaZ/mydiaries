package com.kotkaz.mydiaries.screens;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.kotkaz.mydiaries.BuildConfig;
import com.kotkaz.mydiaries.R;
import com.kotkaz.mydiaries.diary.tables.DefaultTable;
import com.kotkaz.mydiaries.diary.tables.ExerciseTable;
import com.kotkaz.mydiaries.diary.tables.FoodTable;
import com.kotkaz.mydiaries.diary.tables.MoneyTable;
import com.kotkaz.mydiaries.diary.tables.ToDoTable;
import com.kotkaz.mydiaries.diary.tools.TableManager;

import org.apache.commons.io.FileUtils;

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

        //This part of code will delete old data if new version have changed the saving system.
        final int workingDataVersion = 3;
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        int knownDataVersion = sharedPref.getInt("dataVersion", 0);

        if (knownDataVersion < workingDataVersion) {
            FileUtils.deleteQuietly(getFilesDir());
            saveDataVersion();
        }

        setOnClickListeners();


    }


    /**
     * Saves the current Version_Code to lastKnownDataVersion.
     * Will be used to detect software updates.
     */
    private void saveDataVersion() {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("dataVersion", BuildConfig.VERSION_CODE);
        editor.apply();
    }


    /**
     * Setting up all onClickListeners.
     */
    private void setOnClickListeners() {
        //Exit button will close activity.
        Button buttonExit = findViewById(R.id.btnExit);
        buttonExit.setOnClickListener(v -> {
            saveDataVersion();
            finish();
        });


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

    @Override
    public void onBackPressed() {
        saveDataVersion();
        finish();
    }
}
