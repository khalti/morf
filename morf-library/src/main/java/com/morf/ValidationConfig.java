package com.morf;

import android.support.v7.widget.AppCompatEditText;

import com.mobsandgeeks.saripaar.QuickRule;

public class ValidationConfig {
    private QuickRule quickRule;
    private AppCompatEditText editText;

    public ValidationConfig(QuickRule quickRule, AppCompatEditText editText) {
        this.quickRule = quickRule;
        this.editText = editText;
    }

    public QuickRule getQuickRule() {
        return quickRule;
    }

    public AppCompatEditText getEditText() {
        return editText;
    }
}
