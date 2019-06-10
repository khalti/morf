package com.morf.validationRules;

import android.content.Context;
import androidx.appcompat.widget.AppCompatEditText;

import com.mobsandgeeks.saripaar.QuickRule;
import com.morf.validationRules.error.ValidationErrorMessage;

public class PIN extends QuickRule<AppCompatEditText> {

    public PIN() {
    }

    @Override
    public boolean isValid(AppCompatEditText view) {
        return view.getText().length() <= 0 || view.getText().length() == 4;
    }

    @Override
    public String getMessage(Context context) {
        return ValidationErrorMessage.PIN_ERROR;
    }
}
