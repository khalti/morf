package com.morf.validationRules;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;

import com.mobsandgeeks.saripaar.QuickRule;
import com.morf.validationRules.error.ValidationErrorMessage;

import java.util.HashMap;
import java.util.Map;

public class Password extends QuickRule<AppCompatEditText> {
    private final Map<Scheme, String> SCHEME_PATTERNS =
            new HashMap<Scheme, String>() {{
                put(Password.Scheme.ANY, ".+");
                put(Password.Scheme.ALPHA, "\\w+");
                put(Password.Scheme.ALPHA_MIXED_CASE, "(?=.*[a-z])(?=.*[A-Z]).+");
                put(Password.Scheme.NUMERIC, "\\d+");
                put(Password.Scheme.ALPHA_NUMERIC, "(?=.*[a-zA-Z])(?=.*[\\d]).+");
                put(Password.Scheme.ALPHA_NUMERIC_MIXED_CASE,
                        "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d]).+");
                put(Password.Scheme.ALPHA_NUMERIC_SYMBOLS,
                        "(?=.*[a-zA-Z])(?=.*[\\d])(?=.*([^\\w])).+");
                put(Password.Scheme.ALPHA_NUMERIC_MIXED_CASE_SYMBOLS,
                        "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*([^\\w])).+");
            }};
    private int minLength = 8;
    private Scheme scheme = Scheme.ANY;

    public Password() {
    }

    public Password(int minLength) {
        this.minLength = minLength;
    }

    public Password(int minLength, Scheme scheme) {
        this.minLength = minLength;
        this.scheme = scheme;
    }

    @Override
    public boolean isValid(AppCompatEditText view) {
        String password = view.getText() + "";
        if (password.length() > 0) {
            boolean hasMinChars = password.length() >= minLength;
            boolean matchesScheme = password.matches(SCHEME_PATTERNS.get(scheme));
            return hasMinChars && matchesScheme;
        }
        return true;
    }

    @Override
    public String getMessage(Context context) {
        return ValidationErrorMessage.PASSWORD_ERROR;
    }

    public enum Scheme {
        ANY, ALPHA, ALPHA_MIXED_CASE,
        NUMERIC, ALPHA_NUMERIC, ALPHA_NUMERIC_MIXED_CASE,
        ALPHA_NUMERIC_SYMBOLS, ALPHA_NUMERIC_MIXED_CASE_SYMBOLS
    }
}
