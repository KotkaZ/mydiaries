package com.kotkaz.mydiaries.screens;

import android.app.Activity;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.kotkaz.mydiaries.CustomAdapter;
import com.kotkaz.mydiaries.R;
import com.kotkaz.mydiaries.diary.FoodTable;

import org.joda.time.LocalDate;

class DiaryScreen {

    private Activity activity;

    DiaryScreen(Activity activity, int diaryTitleString) {

        this.activity = activity;
        this.activity.setContentView(R.layout.diary_screen);
        TextView diaryTitle =  this.activity.findViewById(R.id.txtDiaryTitle);
        diaryTitle.setText(diaryTitleString);

        //For testing purposes only!!
        FoodTable foodTable = new FoodTable();
        try {
            foodTable.addData(LocalDate.parse("1999-10-10"), "Eggs", 10);
            Thread.sleep(100);
            foodTable.addData(LocalDate.parse("1998-10-10"), "Milk", 5);
            Thread.sleep(100);
            foodTable.addData(LocalDate.parse("1999-11-10"), "Flour", 500);
            Thread.sleep(100);
            foodTable.addData(LocalDate.parse("1999-10-12"), "Bread", 2);
            Thread.sleep(100);
            foodTable.addData(LocalDate.parse("2012-11-11"), "Steak", 2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ListView listView = this.activity.findViewById(R.id.listEntries);
        listView.setAdapter(new CustomAdapter(foodTable, this.activity.getLayoutInflater()));

        Button exitButton = this.activity.findViewById(R.id.btnDiaryExit);
        exitButton.setOnClickListener(v -> {
            MenuScreen menuScreen = new MenuScreen(this.activity);
        });

    }


}
