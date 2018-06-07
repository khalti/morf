package com.morf.validationRules;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;

import com.mobsandgeeks.saripaar.QuickRule;
import com.morf.validationRules.error.ValidationErrorMessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Date extends QuickRule<AppCompatEditText> {

    public Date() {
    }

    @Override
    public boolean isValid(AppCompatEditText view) {
        String s = view.getText() + "";
        if (s.length() > 0) {
            Pattern pattern = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2})");
            Matcher matcher = pattern.matcher(s);

            return matcher.find();
        }

        return true;
    }

    @Override
    public String getMessage(Context context) {
        return ValidationErrorMessage.DATE_ERROR;
    }
}
