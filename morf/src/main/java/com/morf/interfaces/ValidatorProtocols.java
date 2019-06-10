package com.morf.interfaces;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;

import com.morf.ValidationConfig;

import java.util.Map;

public interface ValidatorProtocols {

    void validate();

    void setCustomError(String viewTag, String error);

    void setCustomError(Map<String, String> errorMap);

    void removeValidation(View view);

    void addValidation(ValidationConfig validationConfig);

    @Nullable
    OnItemSelectedListener getSpinnerListener(String tag);

    boolean isValidating();
}