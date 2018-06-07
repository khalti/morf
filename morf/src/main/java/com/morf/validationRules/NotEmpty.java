package com.morf.validationRules;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;

import com.mobsandgeeks.saripaar.QuickRule;
import com.morf.utils.EmptyUtil;
import com.morf.validationRules.error.ValidationErrorMessage;

public class NotEmpty extends QuickRule<AppCompatEditText> {

    public NotEmpty() {
    }

    @Override
    public boolean isValid(AppCompatEditText view) {
        return EmptyUtil.isNotNull(view.getText()) && EmptyUtil.isNotEmpty(view.getText().toString());
    }

    @Override
    public String getMessage(Context context) {
        return ValidationErrorMessage.EMPTY_ERROR;
    }
}
