package com.morf.validationRules;

import android.content.Context;
import androidx.appcompat.widget.AppCompatEditText;

import com.mobsandgeeks.saripaar.QuickRule;
import com.morf.validationRules.error.ValidationErrorMessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import commons.validator.routines.EmailValidator;

public class MobileEmail extends QuickRule<AppCompatEditText> {

    public MobileEmail() {
    }

    @Override
    public boolean isValid(AppCompatEditText view) {
        String s = view.getText() + "";
        if (s.length() > 0) {
            Pattern pattern = Pattern.compile("[9][678][0-9]{8}");
            Matcher matcher = pattern.matcher(s);

            return matcher.find() || EmailValidator.getInstance().isValid(s);
        }

        return true;
    }

    @Override
    public String getMessage(Context context) {
        return ValidationErrorMessage.MOBILE_EMAIL_ERROR;
    }
}
