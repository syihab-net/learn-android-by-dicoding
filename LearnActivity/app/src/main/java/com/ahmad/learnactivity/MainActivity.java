package com.ahmad.learnactivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText firstName;

    private  EditText lastName;

    private Button btnGetFullName;

    private TextView fullName;

    private static final String STATE_RESULT = "state_result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstName = findViewById(R.id.edit_firt_name);
        lastName = findViewById(R.id.edit_last_name);
        btnGetFullName = findViewById(R.id.btn_get_full_name);
        fullName = findViewById(R.id.result_full_name);

        btnGetFullName.setOnClickListener(this);

        if (savedInstanceState != null) {
            String fullNameValue = savedInstanceState.getString(STATE_RESULT);
            fullName.setText(fullNameValue);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_get_full_name) {
            fullName.setText("");
            boolean isErrror = false;

            String firstNameValue = firstName.getText().toString().trim();
            String lastNameValue = lastName.getText().toString().trim();

             if (TextUtils.isEmpty(firstNameValue)) {
                 isErrror = true;
                 firstName.setError("First name is required");
             }

             if (TextUtils.isEmpty(lastNameValue)) {
                 isErrror = true;
                 lastName.setError("Last name is required");
             }

             if (!isErrror) {
                 String fullNameValue = firstNameValue + " " + lastNameValue;

                 fullName.setText(String.format("Your full Name is: %s", String.valueOf(fullNameValue)));
             }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, fullName.getText().toString());
    }
}