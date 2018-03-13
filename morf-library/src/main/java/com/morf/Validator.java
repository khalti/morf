package com.morf;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;

import java.util.List;

public class Validator implements ValidatorProtocols {

    private com.mobsandgeeks.saripaar.Validator validator;

    public Validator(final Context context, List<ValidationConfig> validations, final OnValidationListener onValidationListener) {
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
                        Animation animation = AnimationUtils.loadAnimation(context, R.anim.shake);

                        view.startAnimation(animation);
                        ((AppCompatEditText) view).setError(message);
                    } else {
                        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        for (ValidationConfig config : validations) {
            validator.put(config.getEditText(), config.getQuickRule());
        }
    }

    @Override
    public void validate() {
        validator.validate();
    }
}
