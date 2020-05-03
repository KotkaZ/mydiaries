package com.kotkaz.mydiaries.validators;

import android.content.res.Resources;
import android.text.Editable;
import android.text.TextWatcher;

import com.google.android.material.textfield.TextInputLayout;
import com.kotkaz.mydiaries.R;

public class BaseTextWatcher implements TextWatcher {
    private TextInputLayout textInputLayout;
    private Validators validators;
    private Resources resources;

    public BaseTextWatcher(TextInputLayout view, Resources resources) {
        this.textInputLayout = view;
        this.resources = resources;
        this.validators = new Validators(resources);
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        switch (textInputLayout.getId()) {

            case R.id.boxFoodType:
            case R.id.boxExerciseType:
            case R.id.boxExerciseLocation:
            case R.id.boxMoneyType:
            case R.id.boxToDoType: {
                validators.textFieldValidator(textInputLayout, 4, 30);
                break;
            }

            case R.id.boxToDoPriority: {
                validators.numberFieldValdiator(textInputLayout, 1, 3);
                break;
            }

            case R.id.boxMoneyAmount: {
                validators.numberFieldValdiator(textInputLayout, -1_000_000_00, 1_000_000_000);
                break;
            }
            case R.id.boxExerciseLength:
            case R.id.boxFoodAmount: {
                validators.numberFieldValdiator(textInputLayout, 1, 1000);
                break;
            }

            case R.id.boxToDoDate:
            case R.id.boxMoneyUseDate:
            case R.id.boxExerciseDate:
            case R.id.boxFoodExpDate: {
                break;
            }


            case R.id.boxExerciseDesc:
            case R.id.boxToDoDesc:
            case R.id.boxMoneyDesc: {
                validators.textFieldValidator(textInputLayout, 4, 200);
                break;
            }
        }
    }

    public TextInputLayout getTextInputLayout() {
        return textInputLayout;
    }

    public Resources getResources() {
        return resources;
    }
}
