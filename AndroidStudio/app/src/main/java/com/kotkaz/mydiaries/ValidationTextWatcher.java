package com.kotkaz.mydiaries;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

public class ValidationTextWatcher implements TextWatcher {
    private View view;

    public ValidationTextWatcher(View view) {
        this.view = view;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        switch (view.getId()){
            case R.id.boxFoodAmount:{
                break;
            }
            case R.id.boxFoodExpDate:{
                break;
            }
            case R.id.boxFoodType:{
                break;
            }
        }

    }
}
