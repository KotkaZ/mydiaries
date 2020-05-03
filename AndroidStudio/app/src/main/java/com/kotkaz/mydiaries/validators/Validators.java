package com.kotkaz.mydiaries.validators;


import android.content.res.Resources;

import com.google.android.material.textfield.TextInputLayout;
import com.kotkaz.mydiaries.R;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Objects;

class Validators {

    private Resources resources;

    Validators(Resources resources) {
        this.resources = resources;
    }

    boolean textFieldValidator(TextInputLayout textInputLayout, final int MIN_TEXTSIZE, final int MAX_TEXTSIZE) {

        String text = Objects.requireNonNull(textInputLayout.getEditText()).getText().toString();

        if (text.trim().isEmpty()) {
            textInputLayout.setError(resources.getString(R.string.textEmpty));
            return false;
        } else if (text.length() < MIN_TEXTSIZE) {
            textInputLayout.setError(resources.getString(R.string.textTooShort, MIN_TEXTSIZE));
            return false;
        } else if (text.length() > MAX_TEXTSIZE) {
            textInputLayout.setError(resources.getString(R.string.textTooLong, MAX_TEXTSIZE));
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }


    boolean numberFieldValdiator(TextInputLayout textInputLayout, final int MIN_NUMBER, final int MAX_NUMBER) {

        String text = Objects.requireNonNull(textInputLayout.getEditText()).getText().toString();
        text = text.replaceAll("[$€,.]", ""); //Money field
        text = text.replaceAll("[ min]", ""); //Time field


        //Shouldn't be problem, because edittext accepts only numbers. But just in case.
        if (!NumberUtils.isParsable(text)) {
            textInputLayout.setError(resources.getString(R.string.textNotNumbers));
            return false;
        }

        long number = NumberUtils.toLong(text);

        if (number == 0 || text.trim().isEmpty()) {
            textInputLayout.setError(resources.getString(R.string.textEmpty));
            return false;
        } else if (number < MIN_NUMBER) {
            textInputLayout.setError(resources.getString(R.string.textNumberTooSmall, MIN_NUMBER));
            return false;
        } else if (number > MAX_NUMBER) {
            textInputLayout.setError(resources.getString(R.string.textNumberTooBig, MAX_NUMBER));
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }

}
