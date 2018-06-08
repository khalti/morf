package com.morfimpl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.morf.ValidationConfig;
import com.morf.Validator;
import com.morf.interfaces.OnValidationListener;
import com.morf.utils.LogUtil;
import com.morf.validationRules.Mobile;
import com.morf.validationRules.NotEmpty;
import com.morf.validationRules.Password;
import com.morf.validationRules.PasswordConfirm;
import com.morf.validationRules.Range;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;

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

    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        validator = new Validator(this, new ArrayList<ValidationConfig>() {{
            add(new ValidationConfig(etMobile, "mobile", new NotEmpty(), new Mobile()));
            add(new ValidationConfig(etPassword, new NotEmpty(), new Password(10)));
            add(new ValidationConfig(etConfirmPassword, new NotEmpty(), new PasswordConfirm(etPassword)));
            add(new ValidationConfig(etAmount, new NotEmpty(), new Range(10L, 1000000L)));
        }}, new OnValidationListener() {
            @Override
            public void onValidated() {
                LogUtil.checkpoint("On validated");
            }

            @Override
            public void onError() {
                LogUtil.checkpoint("On error");
            }
        });

        btnSubmit.setOnClickListener(view -> validator.setCustomError("mobile", "This is custom error"));
    }
}
