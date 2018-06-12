package com.morf;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.morf.interfaces.OnValidationListener;
import com.morf.interfaces.ValidatorProtocols;
import com.morf.utils.EmptyUtil;

import java.util.HashMap;
import java.util.List;

public class Validator implements ValidatorProtocols {

    private com.mobsandgeeks.saripaar.Validator validator;
    private HashMap<String, AppCompatEditText> editableMap;
    private Context context;

    public Validator(final Context context, List<ValidationConfig> validations, final OnValidationListener onValidationListener) {
        this.context = context;
        editableMap = new HashMap<>();
        validator = new com.mobsandgeeks.saripaar.Validator(this);
        validator.setValidationListener(new com.mobsandgeeks.saripaar.Validator.ValidationListener() {
            @Override
            public void onValidationSucceeded() {
                onValidationListener.onValidated();
            }

            @Override
            public void onValidationFailed(List<ValidationError> errors) {
                onValidationListener.onError();

                for (ValidationError validationError : errors) {
                    View view = validationError.getView();

                    String msg[] = validationError.getCollatedErrorMessage(context).split("\\r\\n|\\r|\\n");
                    String message = msg[msg.length - 1];

                    if (view instanceof AppCompatEditText) {
                        setError((AppCompatEditText) view, message);
                    } else {
                        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        for (ValidationConfig config : validations) {
            validator.put(config.getEditText(), config.getQuickRule());
            if (EmptyUtil.isNotNull(config.getViewTag())) {
                editableMap.put(config.getViewTag(), config.getEditText());
            }
        }
    }

    @Override
    public void validate() {
        validator.validate(true);
    }

    @Override
    public void setCustomError(String viewTag, String error) {
        if (editableMap.containsKey(viewTag)) {
            setError(editableMap.get(viewTag), error);
        }
    }

    @Override
    public void setCustomError(HashMap<String, String> errorMap) {
        for (String key : errorMap.keySet()) {
            setCustomError(key, errorMap.get(key));
        }
    }

    private void setError(AppCompatEditText view, String error) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.shake);

        view.startAnimation(animation);
        view.setError(error);
    }
}
