package com.morf;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Spinner;

import com.mobsandgeeks.saripaar.QuickRule;

import java.util.Map;

public class ValidationConfig {
    private QuickRule[] quickRule;
    private View view;
    private AppCompatEditText errorView;
    private String viewTag;
    private Map<String, Object> extraData;

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

    public ValidationConfig(View view, Map<String, Object> extraData, QuickRule... quickRule) {
        this.quickRule = quickRule;
        this.view = view;
        this.extraData = extraData;
    }

    public ValidationConfig(View view, @NonNull String viewTag, Map<String, Object> extraData, QuickRule... quickRule) {
        this.quickRule = quickRule;
        this.view = view;
        this.viewTag = viewTag;
        this.extraData = extraData;
    }

    public ValidationConfig(View view, AppCompatEditText errorView, Map<String, Object> extraData, QuickRule... quickRule) {
        this.quickRule = quickRule;
        this.view = view;
        this.errorView = errorView;
        this.extraData = extraData;
    }

    public ValidationConfig(View view, AppCompatEditText errorView, @NonNull String viewTag, Map<String, Object> extraData, QuickRule... quickRule) {
        this.quickRule = quickRule;
        this.view = view;
        this.viewTag = viewTag;
        this.errorView = errorView;
        this.extraData = extraData;
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

    @Nullable
    public AppCompatEditText getErrorView() {
        return errorView;
    }

    @Nullable
    public Map<String, Object> getExtraData() {
        return extraData;
    }
}