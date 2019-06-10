package com.morf;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;

import com.mobsandgeeks.saripaar.QuickRule;

public class ValidationConfig {
    private QuickRule[] quickRule;
    private View view;
    private AppCompatEditText errorView;
    private String viewTag;
    private Extra extra;

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

    public ValidationConfig(View view, Extra extra, QuickRule... quickRule) {
        this.quickRule = quickRule;
        this.view = view;
        this.extra = extra;
    }

    public ValidationConfig(View view, @NonNull String viewTag, Extra extra, QuickRule... quickRule) {
        this.quickRule = quickRule;
        this.view = view;
        this.viewTag = viewTag;
        this.extra = extra;
    }

    public ValidationConfig(View view, AppCompatEditText errorView, Extra extra, QuickRule... quickRule) {
        this.quickRule = quickRule;
        this.view = view;
        this.errorView = errorView;
        this.extra = extra;
    }

    public ValidationConfig(View view, AppCompatEditText errorView, @NonNull String viewTag, Extra extra, QuickRule... quickRule) {
        this.quickRule = quickRule;
        this.view = view;
        this.viewTag = viewTag;
        this.errorView = errorView;
        this.extra = extra;
    }

    View getView() {
        return view;
    }

    QuickRule[] getQuickRule() {
        return quickRule;
    }

    String getViewTag() {
        return viewTag;
    }

    @Nullable
    AppCompatEditText getErrorView() {
        return errorView;
    }

    @Nullable
    Extra getExtra() {
        return extra;
    }
}