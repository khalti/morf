package com.morf;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatEditText;

import com.mobsandgeeks.saripaar.QuickRule;

public class ValidationConfig {
    private QuickRule[] quickRule;
    private AppCompatEditText editText;
    private String viewTag;

    public ValidationConfig(AppCompatEditText editText, QuickRule... quickRule) {
        this.quickRule = quickRule;
        this.editText = editText;
    }

    public ValidationConfig(AppCompatEditText editText, @NonNull String viewTag, QuickRule... quickRule) {
        this.quickRule = quickRule;
        this.editText = editText;
        this.viewTag = viewTag;
    }

    QuickRule[] getQuickRule() {
        return quickRule;
    }

    AppCompatEditText getEditText() {
        return editText;
    }

    String getViewTag() {
        return viewTag;
    }
}