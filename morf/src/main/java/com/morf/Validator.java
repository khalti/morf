package com.morf;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.morf.interfaces.OnItemSelectedListener;
import com.morf.interfaces.OnValidationListener;
import com.morf.interfaces.ValidatorProtocols;
import com.morf.utils.MorfEmptyUtil;
import com.morf.utils.MorfLogUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Validator implements ValidatorProtocols {

    private com.mobsandgeeks.saripaar.Validator validator;
    private Map<String, View> editableMap;
    private Map<String, OnItemSelectedListener> spinnerMap = new HashMap<>();
    private Context context;

    public Validator(final Context context, List<ValidationConfig> validations, final OnValidationListener onValidationListener) {
        this.context = context;
        init(validations, onValidationListener);
    }

    @Override
    public void validate() {
        validator.validate(true);
    }

    @Override
    public void setCustomError(String viewTag, String error) {
        if (editableMap.containsKey(viewTag)) {
            setError((AppCompatEditText) editableMap.get(viewTag), error);
        }
    }

    @Override
    public void setCustomError(Map<String, String> errorMap) {
        for (String key : errorMap.keySet()) {
            setCustomError(key, errorMap.get(key));
        }
    }

    @Override
    public void removeValidation(View view) {
        if (MorfEmptyUtil.isNotNull(validator)) {
            validator.removeRules(view);
            editableMap.remove(view.getTag() + "");
        }
    }

    @Override
    public void addValidation(ValidationConfig validationConfig) {
        if (MorfEmptyUtil.isNotNull(validator)) {
            onPutValidation(validationConfig);
        }
    }

    @Override
    @Nullable
    public OnItemSelectedListener getSpinnerListener(String tag) {
        if (spinnerMap.containsKey(tag)) {
            return spinnerMap.get(tag);
        }
        return null;
    }

    @Override
    public boolean isValidating() {
        return validator.isValidating();
    }

    private void init(List<ValidationConfig> validations, final OnValidationListener onValidationListener) {
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

                    String[] msg = validationError.getCollatedErrorMessage(context).split("\\r\\n|\\r|\\n");
                    String message = msg[msg.length - 1];

                    if (view instanceof AppCompatEditText) {
                        setError((AppCompatEditText) view, message);
                    } else if ((view instanceof AppCompatTextView) || view instanceof Spinner) {
                        View v = editableMap.get(view.getTag() + "");
                        if (MorfEmptyUtil.isNotNull(v) && v instanceof AppCompatEditText) {
                            AppCompatEditText et = (AppCompatEditText) v;
                            String oldTag = et.getTag() + "";
                            et.setTag("error_view");
                            setError(et, message);
                            et.setTag(oldTag);
                        } else {
                            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        for (final ValidationConfig config : validations) {
            onPutValidation(config);
        }
    }

    private void setError(AppCompatEditText view, String error) {
        view.setVisibility(View.VISIBLE);
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.shake);

        view.startAnimation(animation);
        view.setError(error);
        if (MorfEmptyUtil.isNull(error) && view.getTag().equals("error_view")) {
            view.setVisibility(View.GONE);
        }
    }

    private void onPutValidation(final ValidationConfig config) {
        Map<String, Object> extraData = config.getExtraData();
        validator.put(config.getView(), config.getQuickRule());
        if (MorfEmptyUtil.isNotNull(config.getViewTag())) {
            if (MorfEmptyUtil.isNotNull(config.getErrorView())) {
                config.getErrorView().setTag(config.getViewTag());
            }
            config.getView().setTag(config.getViewTag());
            editableMap.put(config.getViewTag(), MorfEmptyUtil.isNotNull(config.getErrorView()) ? config.getErrorView() : config.getView());

            if (config.getView() instanceof AppCompatSpinner) {
                boolean customListener = false;
                if (MorfEmptyUtil.isNotNull(extraData) && extraData.containsKey("custom_listener")) {
                    customListener = (boolean) extraData.get("custom_listener");
                }
                if (customListener) {
                    spinnerMap.put(config.getViewTag(), position -> {
                        if (position != 0) {
                            AppCompatEditText et = (AppCompatEditText) editableMap.get(config.getViewTag() + "");
                            if (MorfEmptyUtil.isNotNull(et)) {
                                et.setTag("error_view");
                                setError(et, null);
                                et.setTag(config.getViewTag());
                            }
                        }
                    });
                } else {
                    ((AppCompatSpinner) config.getView()).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if (adapterView.getSelectedItemPosition() != 0) {
                                AppCompatEditText et = (AppCompatEditText) editableMap.get(config.getViewTag() + "");
                                if (MorfEmptyUtil.isNotNull(et)) {
                                    et.setTag("error_view");
                                    setError(et, null);
                                    et.setTag(config.getViewTag());
                                }
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
            }

            if (config.getView() instanceof AppCompatTextView) {
                ((AppCompatTextView) config.getView()).addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        AppCompatEditText et = (AppCompatEditText) editableMap.get(config.getViewTag() + "");
                        if (MorfEmptyUtil.isNotNull(et)) {
                            et.setTag("error_view");
                            setError(et, null);
                            et.setTag(config.getViewTag());
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
            }
        }
    }
}