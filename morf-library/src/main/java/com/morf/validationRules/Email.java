package com.morf.validationRules;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;

import com.mobsandgeeks.saripaar.QuickRule;
import com.morf.validationRules.error.ValidationErrorMessage;

import commons.validator.routines.EmailValidator;

public class Email extends QuickRule<AppCompatEditText> {

    public Email() {
    }

    @Override
    public boolean isValid(AppCompatEditText view) {
        return EmailValidator.getInstance().isValid(view.getText() + "");
    }

    @Override
    public String getMessage(Context context) {
        return ValidationErrorMessage.EMAIL_ERROR;
    }
}
