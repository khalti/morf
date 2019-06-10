package com.morfimpl;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.morf.ValidationConfig;
import com.morf.Validator;
import com.morf.interfaces.OnItemSelectedListener;
import com.morf.interfaces.OnValidationListener;
import com.morf.utils.MorfEmptyUtil;
import com.morf.utils.MorfLogUtil;
import com.morf.validationRules.Mobile;
import com.morf.validationRules.NotEmpty;
import com.morf.validationRules.Password;
import com.morf.validationRules.PasswordConfirm;
import com.morf.validationRules.Range;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.etMobile)
    MaterialEditText etMobile;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    @BindView(R.id.etPassword)
    MaterialEditText etPassword;
    @BindView(R.id.etConfirmPassword)
    MaterialEditText etConfirmPassword;
    @BindView(R.id.etAmount)
    MaterialEditText etAmount;
    @BindView(R.id.spValue)
    Spinner spValue;
    @BindView(R.id.etError)
    MaterialEditText etError;
    @BindView(R.id.etError1)
    MaterialEditText etError1;
    @BindView(R.id.tvValue)
    AppCompatTextView tvValue;

    private Validator validator;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ArrayAdapter spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<String>() {{
            add("-- Select Value --");
            add("1");
            add("2");
            add("3");
            add("4");
        }});
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spValue.setAdapter(spinnerAdapter);

        validator = new Validator(this, new ArrayList<ValidationConfig>() {{
            add(new ValidationConfig(spValue, etError, "value", new HashMap<String, Object>() {{
                put("custom_listener", false);
            }}, new NotEmpty()));
            add(new ValidationConfig(tvValue, etError1, "value1", new NotEmpty()));
            add(new ValidationConfig(etMobile, "mobile", new NotEmpty(), new Mobile()));
            add(new ValidationConfig(etPassword, "password", new NotEmpty(), new Password(10)));
            add(new ValidationConfig(etConfirmPassword, new NotEmpty(), new PasswordConfirm(etPassword)));
            add(new ValidationConfig(etAmount, new NotEmpty(), new Range(10L, 1000000L)));
        }}, new OnValidationListener() {
            @Override
            public void onValidated() {
                MorfLogUtil.checkpoint("On validated");
            }

            @Override
            public void onError() {
                MorfLogUtil.checkpoint("On error");
            }
        });

        /*spValue.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                MorfLogUtil.log("selected", i);
                OnItemSelectedListener itemSelectedListener = validator.getSpinnerListener(spValue.getTag() + "");
                if (MorfEmptyUtil.isNotNull(itemSelectedListener)) {
                    itemSelectedListener.onItemSelected(i);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/

        btnSubmit.setOnClickListener(view -> {
            /*validator.setCustomError(new HashMap<String, String>() {{
                put("mobile", "This is mobile error");
                put("password", "This is password error");
                put("passwords", "fjjfksjdfjsfjjaf");
            }});*/

            validator.validate();
        });

        etMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tvValue.setText(charSequence);
                validator.removeValidation(etPassword);
                validator.addValidation(new ValidationConfig(etPassword, new NotEmpty(), new Password(20)));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                MorfLogUtil.log("char", charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        tvValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                MorfLogUtil.log("text view text", charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}