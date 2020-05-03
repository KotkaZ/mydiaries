package com.kotkaz.mydiaries.validators;

import android.content.res.Resources;

import com.google.android.material.textfield.TextInputLayout;
import com.kotkaz.mydiaries.R;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class TimeTextWatcher extends BaseTextWatcher {

    private String formatTimeText = "";

    public TimeTextWatcher(TextInputLayout view, Resources resources) {
        super(view, resources);
    }


    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.toString().length() > getResources().getInteger(R.integer.exerciseTimeMaxLength)) {
            getTextInputLayout().getEditText().setText(formatTimeText);
        } else {
            //We have to turn off current TextWatcher, because it will get to infinity loop.
            getTextInputLayout().getEditText().removeTextChangedListener(this);

            String currentString = s.toString().replaceAll("[ min]", "");

            if (before > 0) currentString = StringUtils.chop(currentString);
            if (NumberUtils.toInt(currentString) == 0) currentString = "";
            if (currentString.length() > 0) currentString = currentString + " min";


            formatTimeText = currentString;

            getTextInputLayout().getEditText().setText(currentString);
            getTextInputLayout().getEditText().setSelection(currentString.length());

            getTextInputLayout().getEditText().addTextChangedListener(this);
        }

    }
}
