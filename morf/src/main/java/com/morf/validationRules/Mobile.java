package com.morf.validationRules;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;

import com.mobsandgeeks.saripaar.QuickRule;
import com.morf.utils.MorfEmptyUtil;
import com.morf.validationRules.error.ValidationErrorMessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mobile extends QuickRule<AppCompatEditText> {

    public Mobile() {
    }

    @Override
    public boolean isValid(AppCompatEditText view) {
        if (MorfEmptyUtil.isNotEmpty(view.getText() + "")) {
            Pattern pattern = Pattern.compile("[9][678][0-9]{8}");
            Matcher matcher = pattern.matcher(view.getText() + "");

            return matcher.find();
        }

        return true;
    }

    @Override
    public String getMessage(Context context) {
        return ValidationErrorMessage.MOBILE_ERROR;
    }
}
