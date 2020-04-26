package com.kotkaz.mydiaries.screens;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.kotkaz.mydiaries.R;
import com.kotkaz.mydiaries.validators.ValidationTextWatcher;
import com.kotkaz.mydiaries.adapters.ExerciseTableAdapter;
import com.kotkaz.mydiaries.adapters.FoodTableAdapter;
import com.kotkaz.mydiaries.adapters.MoneyTableAdapter;
import com.kotkaz.mydiaries.adapters.ToDoTableAdapter;
import com.kotkaz.mydiaries.diary.tables.DefaultTable;
import com.kotkaz.mydiaries.diary.tables.ExerciseTable;
import com.kotkaz.mydiaries.diary.tables.FoodTable;
import com.kotkaz.mydiaries.diary.tables.MoneyTable;
import com.kotkaz.mydiaries.diary.tables.ToDoTable;

import org.joda.time.LocalDate;

import java.util.Calendar;

class DiaryScreen {

    private Activity activity;
    private DefaultTable defaultTable;
    private ListView listView;

    DiaryScreen(Activity activity, DefaultTable defaultTable) {

        this.activity = activity;
        this.activity.setContentView(R.layout.diary_screen);
        this.defaultTable = defaultTable;
        TextView diaryTitle = this.activity.findViewById(R.id.txtDiaryTitle);

        this.listView = this.activity.findViewById(R.id.listEntries);
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


        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            defaultTable.removeData(position);
            BaseAdapter adapter = (BaseAdapter) listView.getAdapter();
            adapter.notifyDataSetChanged();
            return true;
        });

        Button exitButton = this.activity.findViewById(R.id.btnDiaryExit);
        exitButton.setOnClickListener(v -> {
            try {
                /*if (defaultTable instanceof FoodTable) defaultTable.saveTabel("food_table.json");
                else if (defaultTable instanceof MoneyTable) defaultTable.saveTabel("money_table.json");
                else if (defaultTable instanceof ExerciseTable) defaultTable.saveTabel("exercise_table.json");
                else if (defaultTable instanceof ToDoTable) defaultTable.saveTabel("todo _table.json");*/
                MenuScreen menuScreen = new MenuScreen(this.activity);
            }
            catch (Exception e){
                e.printStackTrace(); // TODO: 19/04/2020
            }
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

        setTextInputValidators((ViewGroup) popupView);

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
        btnAdd.setOnClickListener(v1 -> {
            addNewEntry(popupView);
            popupWindow.dismiss();
        });

    }

    private void setTextInputValidators(ViewGroup viewGroup){

            int count = viewGroup.getChildCount();
            for (int i = 0; i < count; i++) {
                View view = viewGroup.getChildAt(i);
                if (view instanceof TextInputLayout){
                    ((TextInputLayout) view).getEditText()
                            .addTextChangedListener(new ValidationTextWatcher((TextInputLayout) view, activity.getResources()));
                }
            }


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

    private void addNewEntry(View popupView) {
        if (this.defaultTable instanceof FoodTable) {
            FoodTable foodTable = (FoodTable) defaultTable;
            TextInputLayout foodTitle = popupView.findViewById(R.id.boxFoodType);
            TextInputLayout foodDate = popupView.findViewById(R.id.boxFoodExpDate);
            TextInputLayout foodAmount = popupView.findViewById(R.id.boxFoodAmount);
            foodTable.addData(foodTitle.getEditText().getText().toString(),
                    LocalDate.parse(foodDate.getEditText().getText().toString()),
                    Integer.parseInt(foodAmount.getEditText().getText().toString()));
            FoodTableAdapter foodTableAdapter = (FoodTableAdapter) this.listView.getAdapter();
            foodTableAdapter.notifyDataSetChanged();
        } else if (this.defaultTable instanceof ExerciseTable) {
            ExerciseTable exerciseTable = (ExerciseTable) defaultTable;
            TextInputLayout boxExerciseType = popupView.findViewById(R.id.boxExerciseType);
            TextInputLayout boxExerciseDate = popupView.findViewById(R.id.boxExerciseDate);
            TextInputLayout boxExerciseDesc = popupView.findViewById(R.id.boxExerciseDesc);
            TextInputLayout boxExerciseLength = popupView.findViewById(R.id.boxExerciseLength);
            TextInputLayout boxExerciseLocation = popupView.findViewById(R.id.boxExerciseLocation);
            exerciseTable.addData(
                    LocalDate.parse(boxExerciseDate.getEditText().getText().toString()),
                    boxExerciseType.getEditText().getText().toString(),
                    Double.parseDouble(boxExerciseLength.getEditText().getText().toString()),
                    boxExerciseDesc.getEditText().getText().toString(),
                    boxExerciseLocation.getEditText().getText().toString());
            ExerciseTableAdapter exerciseTableAdapter = (ExerciseTableAdapter) this.listView.getAdapter();
            exerciseTableAdapter.notifyDataSetChanged();
        } else if (this.defaultTable instanceof MoneyTable) {
            MoneyTable moneyTable = (MoneyTable) defaultTable;
            TextInputLayout boxMoneyType = popupView.findViewById(R.id.boxMoneyType);
            TextInputLayout boxMoneyUseDate = popupView.findViewById(R.id.boxMoneyUseDate);
            TextInputLayout boxMoneyAmount = popupView.findViewById(R.id.boxMoneyAmount);
            TextInputLayout boxMoneyDesc = popupView.findViewById(R.id.boxMoneyDesc);
            moneyTable.addData(boxMoneyType.getEditText().getText().toString(),
                    LocalDate.parse(boxMoneyUseDate.getEditText().getText().toString()),
                    Double.parseDouble(boxMoneyAmount.getEditText().getText().toString()),
                    boxMoneyDesc.getEditText().getText().toString());
            MoneyTableAdapter moneyTableAdapter = (MoneyTableAdapter) this.listView.getAdapter();
            moneyTableAdapter.notifyDataSetChanged();
        } else if (this.defaultTable instanceof ToDoTable) {
            ToDoTable toDoTable = (ToDoTable) defaultTable;
            TextInputLayout boxToDoDate = popupView.findViewById(R.id.boxToDoDate);
            TextInputLayout boxToDoType = popupView.findViewById(R.id.boxToDoType);
            TextInputLayout boxToDoDesc = popupView.findViewById(R.id.boxToDoDesc);
            TextInputLayout boxToDoPriority = popupView.findViewById(R.id.boxToDoPriority);
            toDoTable.addData(boxToDoType.getEditText().getText().toString(),
                    LocalDate.parse(boxToDoDate.getEditText().getText().toString()),
                    boxToDoDesc.getEditText().getText().toString(),
                    Integer.parseInt(boxToDoPriority.getEditText().getText().toString()));
            ToDoTableAdapter toDoTableAdapter = (ToDoTableAdapter) this.listView.getAdapter();
            toDoTableAdapter.notifyDataSetChanged();
        }
    }


}
