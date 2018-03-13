package com.morfimpl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.morf.OnValidationListener;
import com.morf.ValidationConfig;
import com.morf.Validator;
import com.morf.utils.LogUtil;
import com.morf.validationRules.Mobile;
import com.morf.validationRules.NotEmpty;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.etMobile)
    MaterialEditText etMobile;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;

    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        validator = new Validator(this, new ArrayList<ValidationConfig>() {{
            add(new ValidationConfig(new NotEmpty(), etMobile));
            add(new ValidationConfig(new Mobile(), etMobile));
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

        btnSubmit.setOnClickListener(view -> validator.validate());
    }
}
