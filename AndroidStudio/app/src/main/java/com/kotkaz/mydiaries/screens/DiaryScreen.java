package com.kotkaz.mydiaries.screens;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.kotkaz.mydiaries.CustomAdapter;
import com.kotkaz.mydiaries.R;
import com.kotkaz.mydiaries.diary.FoodTable;

import org.joda.time.LocalDate;

import java.util.Calendar;

class DiaryScreen {

    private Activity activity;

    DiaryScreen(Activity activity, int diaryTitleString) {

        this.activity = activity;
        this.activity.setContentView(R.layout.diary_screen);
        TextView diaryTitle = this.activity.findViewById(R.id.txtDiaryTitle);
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


        FloatingActionButton btnAddNewEntry = this.activity.findViewById(R.id.btnAddEntry);
        btnAddNewEntry.setOnClickListener(this::popupAddNewEntry);
    }

    /**
     * Pops up box, where u can enter data for table. Dismisses on click next to it.
     * @param v View that called this method.
     */
    private void popupAddNewEntry(View v) {
        //Gets this activity layout inflater.
        LayoutInflater inflater = this.activity.getLayoutInflater();
        View popupView = inflater.inflate(R.layout.add_food_entry, null);

        final int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        final int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);

        // Show the popup window.
        // Which view you pass in doesn't matter, it is only used for the window tolken.
        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

        final TextInputLayout editText = popupView.findViewById(R.id.boxFoodExpDate);

        //Gets the current date.
        final Calendar calendar = Calendar.getInstance();
        final int cDay = calendar.get(Calendar.DAY_OF_MONTH);
        final int cMonth = calendar.get(Calendar.MONTH);
        final int cYear = calendar.get(Calendar.YEAR);

        //Calendar date picker dialog.
        editText.getEditText().setText(String.format("%d-%d-%d", cYear, cMonth, cDay));
        editText.getEditText().setOnClickListener(v2 -> {
            final DatePickerDialog datePickerDialog = new DatePickerDialog(activity, (view, year, month, dayOfMonth) ->
                    editText.getEditText().setText(String.format("%d-%d-%d", year, month, dayOfMonth)), cYear, cMonth, cDay);
            datePickerDialog.show();
        });

        //Confirming adding entry.
        final Button btnAdd = popupView.findViewById(R.id.btnConfirmEntry);
        btnAdd.setOnClickListener(v1 -> System.out.println("test"));
    }


}
