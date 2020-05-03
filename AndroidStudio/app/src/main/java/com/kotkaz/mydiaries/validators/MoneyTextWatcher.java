package com.kotkaz.mydiaries.validators;

import android.content.res.Resources;

import com.google.android.material.textfield.TextInputLayout;
import com.kotkaz.mydiaries.R;

import org.apache.commons.lang3.math.NumberUtils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MoneyTextWatcher extends BaseTextWatcher {

    private String formattedText = "";

    public MoneyTextWatcher(TextInputLayout view, Resources resources) {
        super(view, resources);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //We reserve place for comma separator. That's why -1 is used.
        if (s.toString().length() > getResources().getInteger(R.integer.moneyAmountMaxLength) - 1) {
            getTextInputLayout().getEditText().setText(formattedText);
        } else {
            //We have to turn off current TextWatcher, because it will get to infinity loop.
            getTextInputLayout().getEditText().removeTextChangedListener(this);

            String cleanString = s.toString().replaceAll("[$€,.]", "");

            double parsedMoney;
            if (cleanString.equals("-")) {
                parsedMoney = NumberUtils.toDouble("-" + "0");
            } else parsedMoney = NumberUtils.toDouble(cleanString);


            //String currentText = NumberFormat.getCurrencyInstance().format((parsedMoney / 100)); <-- OLD

            DecimalFormat moneyFormat = new DecimalFormat("€#,##0.00;-€#,##0.00");
            String currentText = moneyFormat.format(parsedMoney / 100);


            formattedText = currentText;

            getTextInputLayout().getEditText().setText(currentText);
            getTextInputLayout().getEditText().setSelection(currentText.length());

            getTextInputLayout().getEditText().addTextChangedListener(this);
        }
    }
}
