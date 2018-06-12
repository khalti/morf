package com.morf.interfaces;

import java.util.HashMap;

public interface ValidatorProtocols {

    void validate();

    void setCustomError(String viewTag, String error);

    void setCustomError(HashMap<String, String> errorMap);
}
