package com.kotkaz.mydiaries.validators;

import android.content.res.Resources;
import android.text.Editable;
import android.text.TextWatcher;

import com.google.android.material.textfield.TextInputLayout;
import com.kotkaz.mydiaries.R;

/**
 * BaseTextWatcher class. Implements TextWatcher interface. Is used to set validators for input fields.
 */
public class BaseTextWatcher implements TextWatcher {
    private TextInputLayout textInputLayout;
    private Validators validators;
    private Resources resources;

    /**
     * Constructor.
     *
     * @param textInputLayout TextInputLayout that will have validator set.
     * @param resources       Application resources for colors and string values.
     */
    public BaseTextWatcher(TextInputLayout textInputLayout, Resources resources) {
        this.textInputLayout = textInputLayout;
        this.resources = resources;
        this.validators = new Validators(resources);
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //Won't be implemented.
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //Won't be implemented. Can be overrided in subclasses.
    }

    @Override
    public void afterTextChanged(Editable s) {
        //Setting validators according to textInputLayout id.
        switch (textInputLayout.getId()) {

            case R.id.boxFoodType: {
                validators.textFieldValidator(textInputLayout, 3, 30);
                break;
            }

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

    TextInputLayout getTextInputLayout() {
        return textInputLayout;
    }

    public Resources getResources() {
        return resources;
    }
}
