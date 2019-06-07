package com.morf.interfaces;

import android.view.View;

import com.morf.ValidationConfig;

import java.util.HashMap;

public interface ValidatorProtocols {

    void validate();

    void setCustomError(String viewTag, String error);

    void setCustomError(HashMap<String, String> errorMap);

    void removeValidation(View view);

    void addValidation(ValidationConfig validationConfig);

    boolean isValidating();
}
