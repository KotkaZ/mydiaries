package com.kotkaz.mydiaries.validators;

import android.content.res.Resources;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.kotkaz.mydiaries.R;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class TimeTextWatcher extends BaseTextWatcher {

    private String formattedText = "";

    public TimeTextWatcher(TextInputLayout view, Resources resources) {
        super(view, resources);
    }


    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //Getting editText from textInputLayout. May return null.
        EditText editText = getTextInputLayout().getEditText();
        if (editText == null) return;

        if (s.toString().length() > getResources().getInteger(R.integer.exerciseTimeMaxLength)) {
            editText.setText(formattedText);
        } else {
            //We have to turn off current TextWatcher, because it will get to infinity loop.
            editText.removeTextChangedListener(this);

            String currentFormat = s.toString().replaceAll("[ min]", "");

            //If user is deleting then we remove the last character with chopping.
            if (before > 0) currentFormat = StringUtils.chop(currentFormat);

            if (NumberUtils.toInt(currentFormat) == 0) currentFormat = ""; //Field is empty.
            if (currentFormat.length() > 0) currentFormat = currentFormat + " min";

            formattedText = currentFormat;

            editText.setText(currentFormat);
            editText.setSelection(currentFormat.length());

            editText.addTextChangedListener(this);
        }

    }
}
