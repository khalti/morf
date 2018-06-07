package com.morf.validationRules;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;

import com.mobsandgeeks.saripaar.QuickRule;
import com.morf.validationRules.error.ValidationErrorMessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FullName extends QuickRule<AppCompatEditText> {

    public FullName() {
    }

    @Override
    public boolean isValid(AppCompatEditText view) {
        if (view.getText().length() > 0) {
            Pattern pattern = Pattern.compile("[a-zA-Z]+\\s[a-zA-Z\\s]+");
            Matcher matcher = pattern.matcher(view.getText() + "");

            return matcher.find();
        }

        return true;
    }

    @Override
    public String getMessage(Context context) {
        return ValidationErrorMessage.FULL_NAME_ERROR;
    }
}
