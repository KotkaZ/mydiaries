package com.kotkaz.mydiaries.validators;

import android.content.res.Resources;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.kotkaz.mydiaries.R;

import org.apache.commons.lang3.math.NumberUtils;

import java.text.DecimalFormat;

public class MoneyTextWatcher extends BaseTextWatcher {

    private String formattedText = "";

    public MoneyTextWatcher(TextInputLayout textInputLayout, Resources resources) {
        super(textInputLayout, resources);
    }

    /**
     * onTextChanged method is used to format user input to money format.
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        //Getting editText from textInputLayout. May return null.
        EditText editText = getTextInputLayout().getEditText();
        if (editText == null) return;

        //Maximum length is reached. We reserve place for comma separator. That's why -1 is used.
        if (s.toString().length() > getResources().getInteger(R.integer.moneyAmountMaxLength) - 1) {
            editText.setText(formattedText);
        } else {
            //We have to turn off current TextWatcher, because it will get to infinity loop.
            editText.removeTextChangedListener(this);

            String cleanString = s.toString().replaceAll("[$€,.]", "");

            double parsedMoney;
            //Setting double to negative value, if input starts with minus.
            if (cleanString.equals("-")) {
                parsedMoney = NumberUtils.toDouble("-" + "0");
            } else parsedMoney = NumberUtils.toDouble(cleanString);

            //Formatting text.
            DecimalFormat moneyFormat = new DecimalFormat("€#,##0.00;-€#,##0.00");
            String currentText = moneyFormat.format(parsedMoney / 100);

            formattedText = currentText;

            editText.setText(currentText);
            editText.setSelection(currentText.length());

            editText.addTextChangedListener(this);
        }
    }
}
