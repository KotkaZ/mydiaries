package com.kotkaz.mydiaries.screens;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
import com.kotkaz.mydiaries.diary.tools.TableManager;
import com.kotkaz.mydiaries.validators.BaseTextWatcher;
import com.kotkaz.mydiaries.validators.MoneyTextWatcher;
import com.kotkaz.mydiaries.validators.TimeTextWatcher;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.util.Calendar;
import java.util.Locale;

public class DiaryScreen extends AppCompatActivity {

    private DefaultTable defaultTable;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_screen);

        Intent intent = this.getIntent();
        defaultTable = (DefaultTable) intent.getSerializableExtra("TABLE");

        setUpTileAndListView();
        setListViewItemListeners();
        setUpExitButtonClickListener();
        addSpinnerValues();

        //Setting up addButton onClickListener that pops up adding entry screen.
        FloatingActionButton btnAddNewEntry = findViewById(R.id.btnAddEntry);
        btnAddNewEntry.setOnClickListener(v -> popupAddNewEntry(v.getRootView()));
    }


    /**
     * Pops up box, where u can enter data for table. Dismisses on click next to it.
     *
     * @param v View that called this method.
     */
    private void popupAddNewEntry(View v) {

        View popupView = popupView((ViewGroup) v);
        if (popupView == null) return;

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
        if (editText != null) {
            editText.setText(String.format(Locale.getDefault(), "%d-%d-%d", cYear, cMonth + 1, cDay));

            editText.setOnClickListener(v2 -> {
                final DatePickerDialog datePickerDialog = new DatePickerDialog(
                        this, (view, year, month, dayOfMonth) ->
                        editText.setText(String.format(Locale.getDefault(), "%d-%d-%d",
                                year, month + 1, dayOfMonth)), cYear, cMonth, cDay);

                datePickerDialog.show();
                datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(getApplicationContext().getColor(R.color.colorDarkMenu));
                datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(getApplicationContext().getColor(R.color.colorDarkMenu));
            });
        }

        //Confirming adding entry.
        final Button btnAdd = popupView.findViewById(R.id.btnConfirmEntry);
        btnAdd.setOnClickListener(v1 -> {
            ViewGroup viewGroup = (ViewGroup) v1.getParent();
            int count = viewGroup.getChildCount();
            boolean errorEnabled = false;

            for (int i = 0; i < count; i++) {
                View view = viewGroup.getChildAt(i);
                if (view instanceof TextInputLayout) {
                    EditText textInputLayoutEditText = ((TextInputLayout) view).getEditText();
                    if (textInputLayoutEditText == null) continue;

                    //If textfield is empty, then call out onTextChanged event for showing error.
                    if (textInputLayoutEditText.getText().toString().trim().equals(""))
                        textInputLayoutEditText.setText("");
                    if (((TextInputLayout) view).isErrorEnabled()) errorEnabled = true;
                }
            }
            if (errorEnabled) return;

            addNewEntry(popupView);
            popupWindow.dismiss();
        });

    }


    /**
     * Sets up listView item onClickListeners.
     */
    private void setListViewItemListeners() {
        //Clicking once will show deleting guide.
        listView.setOnItemClickListener((parent, view, position, id) ->
                Toast.makeText(DiaryScreen.this, getResources().
                        getString(R.string.toastHoldToDelete), Toast.LENGTH_SHORT).show());

        //Long click will delete table entry.
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            defaultTable.removeData(position);
            BaseAdapter adapter = (BaseAdapter) listView.getAdapter();
            adapter.notifyDataSetChanged(); //Updates listView.
            return true;
        });
    }

    /**
     * setUpExitButtonClickListener method will try to save table and then exit.
     */
    private void setUpExitButtonClickListener() {
        Button exitButton = findViewById(R.id.btnDiaryExit);
        exitButton.setOnClickListener(v -> {
            if (defaultTable instanceof FoodTable)
                TableManager.saveTable(getApplicationContext(), defaultTable, "food_table.json");
            else if (defaultTable instanceof MoneyTable)
                TableManager.saveTable(getApplicationContext(), defaultTable, "money_table.json");
            else if (defaultTable instanceof ExerciseTable)
                TableManager.saveTable(getApplicationContext(), defaultTable, "exercise_table.json");
            else if (defaultTable instanceof ToDoTable)
                TableManager.saveTable(getApplicationContext(), defaultTable, "todo _table.json");

            goToMenuScreen(); //Closes activity and shows menu screen.
        });
    }


    /**
     * setUpTileAndListView replaces diaryScreen title with correct title and shows entries in listView.
     */
    private void setUpTileAndListView() {
        TextView diaryTitle = findViewById(R.id.txtDiaryTitle);
        listView = findViewById(R.id.listEntries);

        if (defaultTable instanceof FoodTable) {
            diaryTitle.setText(R.string.FoodList);
            listView.setAdapter(new FoodTableAdapter((FoodTable) defaultTable, getLayoutInflater()));
        } else if (defaultTable instanceof MoneyTable) {
            diaryTitle.setText(R.string.MoneyList);
            listView.setAdapter(new MoneyTableAdapter((MoneyTable) defaultTable, getLayoutInflater(), getApplicationContext()));
        } else if (defaultTable instanceof ExerciseTable) {
            diaryTitle.setText(R.string.Exercise);
            listView.setAdapter(new ExerciseTableAdapter((ExerciseTable) defaultTable, getLayoutInflater()));
        } else if (defaultTable instanceof ToDoTable) {
            diaryTitle.setText(R.string.ToDoList);
            listView.setAdapter(new ToDoTableAdapter((ToDoTable) defaultTable, getLayoutInflater(), getApplicationContext()));
        }
    }


    /**
     * setTextInputValidators adds textWatchers to editTexts.
     *
     * @param viewGroup TextInputLayout parent viewGroup.
     */
    private void setTextInputValidators(ViewGroup viewGroup) {
        int count = viewGroup.getChildCount();

        for (int i = 0; i < count; i++) {
            View view = viewGroup.getChildAt(i);
            if (view instanceof TextInputLayout) {
                EditText editText = ((TextInputLayout) view).getEditText();
                if (editText == null) return;

                if (view.getId() == R.id.boxMoneyAmount)
                    editText.addTextChangedListener(new MoneyTextWatcher((TextInputLayout) view, getResources()));
                else if (view.getId() == R.id.boxExerciseLength)
                    editText.addTextChangedListener(new TimeTextWatcher((TextInputLayout) view, getResources()));
                else
                    editText.addTextChangedListener(new BaseTextWatcher((TextInputLayout) view, getResources()));
            }
        }


    }


    /**
     * popupView method inflates adding entries box and returns it.
     *
     * @param parent popupView parent.
     * @return Returns inflated popupView.
     */
    private View popupView(ViewGroup parent) {
        LayoutInflater inflater = getLayoutInflater();
        if (defaultTable instanceof FoodTable) {
            return inflater.inflate(R.layout.add_food_entry, parent, false);
        } else if (defaultTable instanceof MoneyTable) {
            return inflater.inflate(R.layout.add_money_entry, parent, false);
        } else if (defaultTable instanceof ToDoTable) {
            return inflater.inflate(R.layout.add_todo_entry, parent, false);
        } else if (defaultTable instanceof ExerciseTable) {
            return inflater.inflate(R.layout.add_exercise_entry, parent, false);
        } else return null;
    }


    /**
     * findDateBox method finds TextInputLayout that is used to enter date and returns that.
     *
     * @param popupView editText parent view.
     * @return Returns TextInputLayout which is used to enter date.
     */
    private TextInputLayout findDateBox(View popupView) {
        if (defaultTable instanceof FoodTable) {
            return popupView.findViewById(R.id.boxFoodExpDate);
        } else if (defaultTable instanceof MoneyTable) {
            return popupView.findViewById(R.id.boxMoneyUseDate);
        } else if (defaultTable instanceof ToDoTable) {
            return popupView.findViewById(R.id.boxToDoDate);
        } else if (defaultTable instanceof ExerciseTable) {
            return popupView.findViewById(R.id.boxExerciseDate);
        } else return null;
    }


    /**
     * addNewEntry method is getting values from used input and adds new entry to table.
     *
     * @param popupView popupView where are userInputFields.
     */
    private void addNewEntry(View popupView) {
        if (defaultTable instanceof FoodTable) {
            FoodTable foodTable = (FoodTable) defaultTable;
            TextInputLayout foodTitle = popupView.findViewById(R.id.boxFoodType);
            TextInputLayout foodDate = popupView.findViewById(R.id.boxFoodExpDate);
            TextInputLayout foodAmount = popupView.findViewById(R.id.boxFoodAmount);
            foodTable.addData(foodTitle.getEditText().getText().toString(),
                    LocalDate.parse(foodDate.getEditText().getText().toString()),
                    Integer.parseInt(foodAmount.getEditText().getText().toString()), "pcs");
            // TODO: 09/05/2020
            FoodTableAdapter foodTableAdapter = (FoodTableAdapter) listView.getAdapter();
            foodTableAdapter.notifyDataSetChanged();
        } else if (defaultTable instanceof ExerciseTable) {
            ExerciseTable exerciseTable = (ExerciseTable) defaultTable;
            TextInputLayout boxExerciseType = popupView.findViewById(R.id.boxExerciseType);
            TextInputLayout boxExerciseDate = popupView.findViewById(R.id.boxExerciseDate);
            TextInputLayout boxExerciseDesc = popupView.findViewById(R.id.boxExerciseDesc);
            TextInputLayout boxExerciseLength = popupView.findViewById(R.id.boxExerciseLength);
            TextInputLayout boxExerciseLocation = popupView.findViewById(R.id.boxExerciseLocation);
            exerciseTable.addData(
                    LocalDateTime.parse(boxExerciseDate.getEditText().getText().toString()),
                    boxExerciseType.getEditText().getText().toString(),
                    Integer.parseInt(boxExerciseLength.getEditText().getText().toString().replaceAll("[ min]", "")),
                    boxExerciseDesc.getEditText().getText().toString(),
                    boxExerciseLocation.getEditText().getText().toString());
            ExerciseTableAdapter exerciseTableAdapter = (ExerciseTableAdapter) listView.getAdapter();
            exerciseTableAdapter.notifyDataSetChanged();
        } else if (defaultTable instanceof MoneyTable) {
            MoneyTable moneyTable = (MoneyTable) defaultTable;
            TextInputLayout boxMoneyType = popupView.findViewById(R.id.boxMoneyType);
            TextInputLayout boxMoneyUseDate = popupView.findViewById(R.id.boxMoneyUseDate);
            TextInputLayout boxMoneyAmount = popupView.findViewById(R.id.boxMoneyAmount);
            TextInputLayout boxMoneyDesc = popupView.findViewById(R.id.boxMoneyDesc);
            moneyTable.addData(boxMoneyType.getEditText().getText().toString(),
                    LocalDateTime.parse(boxMoneyUseDate.getEditText().getText().toString()),
                    Double.parseDouble(boxMoneyAmount.getEditText().getText().toString().replaceAll("[$€,.]", "")),
                    boxMoneyDesc.getEditText().getText().toString());
            MoneyTableAdapter moneyTableAdapter = (MoneyTableAdapter) listView.getAdapter();
            moneyTableAdapter.notifyDataSetChanged();
        } else if (defaultTable instanceof ToDoTable) {
            ToDoTable toDoTable = (ToDoTable) defaultTable;
            TextInputLayout boxToDoDate = popupView.findViewById(R.id.boxToDoDate);
            TextInputLayout boxToDoType = popupView.findViewById(R.id.boxToDoType);
            TextInputLayout boxToDoDesc = popupView.findViewById(R.id.boxToDoDesc);
            TextInputLayout boxToDoPriority = popupView.findViewById(R.id.boxToDoPriority);
            toDoTable.addData(boxToDoType.getEditText().getText().toString(),
                    LocalDateTime.parse(boxToDoDate.getEditText().getText().toString()),
                    boxToDoDesc.getEditText().getText().toString(),
                    Integer.parseInt(boxToDoPriority.getEditText().getText().toString()));
            ToDoTableAdapter toDoTableAdapter = (ToDoTableAdapter) listView.getAdapter();
            toDoTableAdapter.notifyDataSetChanged();
        }
    }

    /**
     * Add selectable elements to sortSpinner.
     */
    private void addSpinnerValues() {
        Spinner sortSpinner = findViewById(R.id.spr_sortItem);

        String[] arraySpinner = null;
        if (defaultTable instanceof FoodTable) {
            arraySpinner = new String[]{
                    getString(R.string.sortTitle),
                    getString(R.string.sortDate),
                    getString(R.string.sortAmount),
            };
        }


        if (arraySpinner != null) {
            ArrayAdapter<String> spinnerAdatper = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_item, arraySpinner);
            spinnerAdatper.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            sortSpinner.setAdapter(spinnerAdatper);
        }
    }


    /**
     * goToMenuScreen closes current screen & activity, goes back to menu screen.
     */
    private void goToMenuScreen() {
        Intent menuScreenIntent = new Intent(this, MenuScreen.class);
        finish();
        startActivity(menuScreenIntent);
    }


}
