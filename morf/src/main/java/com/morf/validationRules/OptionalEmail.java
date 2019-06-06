package com.morf.validationRules;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;

import com.mobsandgeeks.saripaar.QuickRule;
import com.morf.utils.MorfEmptyUtil;
import com.morf.validationRules.error.ValidationErrorMessage;

import commons.validator.routines.EmailValidator;

public class OptionalEmail extends QuickRule<AppCompatEditText> {

    public OptionalEmail() {
    }

    @Override
    public boolean isValid(AppCompatEditText view) {
        return MorfEmptyUtil.isEmpty(view.getText() + "") || EmailValidator.getInstance(false).isValid(view.getText() + "");
    }

    @Override
    public String getMessage(Context context) {
        return ValidationErrorMessage.EMAIL_ERROR;
    }
}
