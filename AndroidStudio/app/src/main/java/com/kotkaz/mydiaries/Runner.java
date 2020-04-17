package com.kotkaz.mydiaries;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;

import com.kotkaz.mydiaries.diary.FoodTable;

import java.time.LocalDateTime;

public class Runner extends AppCompatActivity {


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_screen);

        Button buttonExit = findViewById(R.id.btnExit);
        buttonExit.setOnClickListener(v -> System.exit(0));

        FoodTable foodTable = new FoodTable();
        try {
            foodTable.addData(LocalDateTime.of(1999, 11, 23, 10, 20), "Eggs", 10);
            Thread.sleep(100);
            foodTable.addData(LocalDateTime.of(2000, 11, 23, 10, 20), "Milk", 5);
            Thread.sleep(100);
            foodTable.addData(LocalDateTime.of(2001, 11, 23, 10, 20), "Flour", 500);
            Thread.sleep(100);
            foodTable.addData(LocalDateTime.of(2012, 11, 23, 10, 20), "Bread", 2);
            Thread.sleep(100);
            foodTable.addData(LocalDateTime.of(1999, 11, 23, 10, 20), "Steak", 2);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        Button buttonFoodTable = findViewById(R.id.btnFoodList);
        buttonFoodTable.setOnClickListener(v -> {
            setContentView(R.layout.diary_screen);
            ListView listView = findViewById(R.id.listEntries);
            listView.setAdapter(new CustomAdapter(foodTable,getLayoutInflater()));
        });





        //listView.setAdapter(new CustomAdapter(foodTable,getLayoutInflater()));

    }
}
