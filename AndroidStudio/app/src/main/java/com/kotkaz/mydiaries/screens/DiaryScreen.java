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
import com.google.android.material.textfield.TextInputLayout;
import com.kotkaz.mydiaries.R;
import com.kotkaz.mydiaries.adapters.ExerciseTableAdapter;
import com.kotkaz.mydiaries.adapters.FoodTableAdapter;
import com.kotkaz.mydiaries.adapters.MoneyTableAdapter;
import com.kotkaz.mydiaries.adapters.ToDoTableAdapter;
import com.kotkaz.mydiaries.diary.tables.DefaultTable;
import com.kotkaz.mydiaries.diary.tables.ExerciseTable;
import com.kotkaz.mydiaries.diary.tables.FoodTable;
import com.kotkaz.mydiaries.diary.tables.MoneyTable;
import com.kotkaz.mydiaries.diary.tables.ToDoTable;

import java.util.Calendar;

class DiaryScreen {

    private Activity activity;
    private DefaultTable defaultTable;

    DiaryScreen(Activity activity, DefaultTable defaultTable) {

        this.activity = activity;
        this.activity.setContentView(R.layout.diary_screen);
        this.defaultTable = defaultTable;
        TextView diaryTitle = this.activity.findViewById(R.id.txtDiaryTitle);

        ListView listView = this.activity.findViewById(R.id.listEntries);
        if (defaultTable instanceof FoodTable) {
            diaryTitle.setText(R.string.FoodList);
            listView.setAdapter(new FoodTableAdapter((FoodTable) defaultTable, this.activity.getLayoutInflater()));
        } else if (defaultTable instanceof MoneyTable) {
            diaryTitle.setText(R.string.MoneyList);
            listView.setAdapter(new MoneyTableAdapter((MoneyTable) defaultTable, this.activity.getLayoutInflater()));
        } else if (defaultTable instanceof ExerciseTable) {
            diaryTitle.setText(R.string.Exercise);
            listView.setAdapter(new ExerciseTableAdapter((ExerciseTable) defaultTable, this.activity.getLayoutInflater()));
        } else if (defaultTable instanceof ToDoTable) {
            diaryTitle.setText(R.string.ToDoList);
            listView.setAdapter(new ToDoTableAdapter((ToDoTable) defaultTable, this.activity.getLayoutInflater()));
        }


        Button exitButton = this.activity.findViewById(R.id.btnDiaryExit);
        exitButton.setOnClickListener(v -> {
            MenuScreen menuScreen = new MenuScreen(this.activity);
        });


        FloatingActionButton btnAddNewEntry = this.activity.findViewById(R.id.btnAddEntry);
        btnAddNewEntry.setOnClickListener(this::popupAddNewEntry);
    }

    /**
     * Pops up box, where u can enter data for table. Dismisses on click next to it.
     *
     * @param v View that called this method.
     */
    private void popupAddNewEntry(View v) {
        //Gets this activity layout inflater.

        View popupView = popupView();

        final int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        final int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);

        // Show the popup window.
        // Which view you pass in doesn't matter, it is only used for the window token.
        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

        final EditText editText = findDateBox(popupView).getEditText();

        //Gets the current date.
        final Calendar calendar = Calendar.getInstance();
        final int cDay = calendar.get(Calendar.DAY_OF_MONTH);
        final int cMonth = calendar.get(Calendar.MONTH);
        final int cYear = calendar.get(Calendar.YEAR);


        //Calendar date picker dialog.
        editText.setText(String.format("%d-%d-%d", cYear, cMonth, cDay));
        editText.setOnClickListener(v2 -> {
            final DatePickerDialog datePickerDialog = new DatePickerDialog(activity, (view, year, month, dayOfMonth) ->
                    editText.setText(String.format("%d-%d-%d", year, month, dayOfMonth)), cYear, cMonth, cDay);
            datePickerDialog.show();
        });

        //Confirming adding entry.
        final Button btnAdd = popupView.findViewById(R.id.btnConfirmEntry);
        btnAdd.setOnClickListener(v1 -> System.out.println("test"));
    }


    private View popupView() {
        LayoutInflater inflater = this.activity.getLayoutInflater();
        if (this.defaultTable instanceof FoodTable) {
            return inflater.inflate(R.layout.add_food_entry, null);
        } else if (this.defaultTable instanceof MoneyTable) {
            return inflater.inflate(R.layout.add_money_entry, null);
        } else if (this.defaultTable instanceof ToDoTable) {
            return inflater.inflate(R.layout.add_todo_entry, null);
        } else if (this.defaultTable instanceof ExerciseTable) {
            return inflater.inflate(R.layout.add_exercise_entry, null);
        } else return null;
    }

    private TextInputLayout findDateBox(View popupView) {
        if (this.defaultTable instanceof FoodTable) {
            return popupView.findViewById(R.id.boxFoodExpDate);
        } else if (this.defaultTable instanceof MoneyTable) {
            return popupView.findViewById(R.id.boxMoneyUseDate);
        } else if (this.defaultTable instanceof ToDoTable) {
            return popupView.findViewById(R.id.boxToDoDate);
        } else if (this.defaultTable instanceof ExerciseTable) {
            return popupView.findViewById(R.id.boxExerciseDate);
        } else return null;
    }


}
