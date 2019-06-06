package com.morf;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Spinner;

import com.mobsandgeeks.saripaar.QuickRule;

public class ValidationConfig {
    private QuickRule[] quickRule;
    private View view;
    private AppCompatEditText errorView;
    private String viewTag;

    public ValidationConfig(View view, QuickRule... quickRule) {
        this.quickRule = quickRule;
        this.view = view;
    }

    public ValidationConfig(View view, @NonNull String viewTag, QuickRule... quickRule) {
        this.quickRule = quickRule;
        this.view = view;
        this.viewTag = viewTag;
    }

    public ValidationConfig(View view, AppCompatEditText errorView, QuickRule... quickRule) {
        this.quickRule = quickRule;
        this.view = view;
        this.errorView = errorView;
    }

    public ValidationConfig(View view, AppCompatEditText errorView, @NonNull String viewTag, QuickRule... quickRule) {
        this.quickRule = quickRule;
        this.view = view;
        this.viewTag = viewTag;
        this.errorView = errorView;
    }

    public View getView() {
        return view;
    }

    QuickRule[] getQuickRule() {
        return quickRule;
    }

    String getViewTag() {
        return viewTag;
    }

    public AppCompatEditText getErrorView() {
        return errorView;
    }
}