package com.kotkaz.mydiaries.screens;

import android.app.Activity;
import android.widget.Button;

import com.kotkaz.mydiaries.R;

public class MenuScreen {

    private Activity activity;

    public MenuScreen(Activity activity) {
        this.activity = activity;


        this.activity.setContentView(R.layout.menu_screen);

        Button buttonExit = this.activity.findViewById(R.id.btnExit);
        buttonExit.setOnClickListener(v -> System.exit(0));


        Button buttonFoodTable = this.activity.findViewById(R.id.btnFoodList);
        buttonFoodTable.setOnClickListener(v -> {
            DiaryScreen diaryScreen = new DiaryScreen(this.activity, R.string.FoodList);
        });

        Button buttonExerciseTable = this.activity.findViewById(R.id.btnExerciseList);
        buttonExerciseTable.setOnClickListener(v -> {
            DiaryScreen diaryScreen = new DiaryScreen(this.activity, R.string.Exercise);
        });

        Button buttonToDoTable = this.activity.findViewById(R.id.btnToDoList);
        buttonToDoTable.setOnClickListener(v -> {
            DiaryScreen diaryScreen = new DiaryScreen(this.activity, R.string.ToDoList);
        });

        Button buttonMoneyTable = this.activity.findViewById(R.id.btnMoneyList);
        buttonMoneyTable.setOnClickListener(v -> {
            DiaryScreen diaryScreen = new DiaryScreen(this.activity, R.string.MoneyList);
        });
    }
}
