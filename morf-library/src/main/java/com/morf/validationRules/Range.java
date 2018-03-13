package com.morf.validationRules;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;

import com.mobsandgeeks.saripaar.QuickRule;
import com.morf.utils.EmptyUtil;
import com.morf.validationRules.error.ValidationErrorMessage;

public class Range extends QuickRule<AppCompatEditText> {

    private String error;
    private Long min, max;

    public Range(Long min, Long max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean isValid(AppCompatEditText view) {
        if (EmptyUtil.isNotEmpty(view.getText() + "")) {
            try {
                Long number = Long.parseLong(view.getText() + "");
                if (number < min) {
                    error = ValidationErrorMessage.RANGE_LOW_ERROR + min;
                    return false;
                } else if (number > max) {
                    error = ValidationErrorMessage.RANGE_HIGH_ERROR + max;
                    return false;
                } else {
                    return true;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                error = ValidationErrorMessage.NUMBER_ERROR;
                return false;
            }
        }
        return true;
    }

    @Override
    public String getMessage(Context context) {
        return error;
    }
}
