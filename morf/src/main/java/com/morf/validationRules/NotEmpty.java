package com.morf.validationRules;

import android.content.Context;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import android.view.View;
import android.widget.Spinner;

import com.mobsandgeeks.saripaar.QuickRule;
import com.morf.utils.MorfEmptyUtil;
import com.morf.validationRules.error.ValidationErrorMessage;

public class NotEmpty extends QuickRule<View> {

    public NotEmpty() {
    }

    @Override
    public boolean isValid(View view) {
        if (view instanceof AppCompatEditText) {
            return MorfEmptyUtil.isNotNull(((AppCompatEditText) view).getText()) && MorfEmptyUtil.isNotEmpty(((AppCompatEditText) view).getText() + "");
        } else if (view instanceof Spinner) {
            return ((Spinner) view).getSelectedItemPosition() != 0;
        } else if (view instanceof AppCompatTextView) {
            AppCompatTextView tv = (AppCompatTextView) view;
            String value = tv.getText().toString();
            return MorfEmptyUtil.isNotEmpty(value) && !value.equals("...");
        }
        return true;
    }

    @Override
    public String getMessage(Context context) {
        return ValidationErrorMessage.EMPTY_ERROR;
    }
}