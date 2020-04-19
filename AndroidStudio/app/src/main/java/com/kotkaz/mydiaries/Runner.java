package com.kotkaz.mydiaries;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.kotkaz.mydiaries.screens.MenuScreen;

/**
 * Start class.
 */
public class Runner extends AppCompatActivity {


    /**
     * Main method. Shows menu screen.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Shows menu screen.
        MenuScreen menuScreen = new MenuScreen(this);

    }
}
