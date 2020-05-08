package com.kotkaz.mydiaries.validators;


import android.content.res.Resources;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.kotkaz.mydiaries.R;

import org.apache.commons.lang3.math.NumberUtils;

class Validators {

    private Resources resources;

    Validators(Resources resources) {
        this.resources = resources;
    }

    /**
     * Validator method, that checks for valid text input and sets errors accordingly.
     *
     * @param textInputLayout TextInputLayout that will be checked for valid input.
     * @param MIN_TEXTSIZE    Minimum allowed text length.
     * @param MAX_TEXTSIZE    Maximum allowed text length.
     * @return If error is occurred then false. No error then return true.
     */
    boolean textFieldValidator(TextInputLayout textInputLayout, final int MIN_TEXTSIZE, final int MAX_TEXTSIZE) {

        EditText editText = textInputLayout.getEditText();
        if (editText == null) return false;

        String text = editText.getText().toString().trim();

        if (text.isEmpty()) {
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
            return true;
        }

    }


    /**
     * Validator method, that checks for valid numeric input and sets errors accordingly.
     *
     * @param textInputLayout TextInputLayout that will be checked for valid input.
     * @param MIN_NUMBER      Minimum allowed number.
     * @param MAX_NUMBER      Maximum allowed number.
     * @return If error is occurred then false. No error then return true.
     */
    boolean numberFieldValdiator(TextInputLayout textInputLayout, final int MIN_NUMBER, final int MAX_NUMBER) {
        EditText editText = textInputLayout.getEditText();
        if (editText == null) return false;

        //Cleaning text from formats.
        String text = editText.getText().toString();
        text = text.replaceAll("[$€,.]", ""); //Money field
        text = text.replaceAll("[ min]", ""); //Time field
        text = text.trim();

        //Shouldn't be problem, because editText accepts only numbers and formatted correctly.
        if (!text.isEmpty() && !NumberUtils.isParsable(text)) {
            textInputLayout.setError(resources.getString(R.string.textNotNumbers));
            return false;
        }

        long number = NumberUtils.toLong(text);

        if (number == 0 || text.isEmpty()) {
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
            return true;
        }
    }

}
