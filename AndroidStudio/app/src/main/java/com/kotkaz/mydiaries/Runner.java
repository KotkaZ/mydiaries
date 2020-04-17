package com.kotkaz.mydiaries;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.kotkaz.mydiaries.screens.MenuScreen;


public class Runner extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MenuScreen menuScreen = new MenuScreen(this);

    }
}
