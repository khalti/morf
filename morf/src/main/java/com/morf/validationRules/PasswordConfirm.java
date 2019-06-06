package com.morf.validationRules;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;

import com.mobsandgeeks.saripaar.QuickRule;
import com.morf.utils.MorfEmptyUtil;
import com.morf.validationRules.error.ValidationErrorMessage;

public class PasswordConfirm extends QuickRule<AppCompatEditText> {

    private AppCompatEditText passwordView;

    public PasswordConfirm(AppCompatEditText passwordView) {
        this.passwordView = passwordView;
    }

    @Override
    public boolean isValid(AppCompatEditText view) {
        return MorfEmptyUtil.isEmpty(view.getText() + "") || (view.getText() + "").equals(passwordView.getText() + "");
    }

    @Override
    public String getMessage(Context context) {
        return ValidationErrorMessage.PASSWORD_CONFIRM_ERROR;
    }

}
