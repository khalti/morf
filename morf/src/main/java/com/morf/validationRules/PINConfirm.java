package com.morf.validationRules;

import android.content.Context;
import androidx.appcompat.widget.AppCompatEditText;

import com.mobsandgeeks.saripaar.QuickRule;
import com.morf.validationRules.error.ValidationErrorMessage;

public class PINConfirm extends QuickRule<AppCompatEditText> {

    private AppCompatEditText pinView;

    public PINConfirm(AppCompatEditText pinView) {
        this.pinView = pinView;
    }

    @Override
    public boolean isValid(AppCompatEditText view) {
        return (view.getText() + "").equals(pinView.getText() + "");
    }

    @Override
    public String getMessage(Context context) {
        return ValidationErrorMessage.PIN_CONFIRM_ERROR;
    }

}
