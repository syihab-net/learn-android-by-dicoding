package com.ahmad.learnactivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ahmad.learnactivity.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding binding;

    private static final String STATE_RESULT = "state_result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnGetFullName.setOnClickListener(this);

        if (savedInstanceState != null) {
            String fullNameValue = savedInstanceState.getString(STATE_RESULT);
            binding.resultFullName.setText(fullNameValue);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_get_full_name) {
            binding.resultFullName.setText("");
            boolean isErrror = false;

            String firstNameValue = binding.editFirtName.getText().toString().trim();
            String lastNameValue = binding.editLastName.getText().toString().trim();

             if (TextUtils.isEmpty(firstNameValue)) {
                 isErrror = true;
                 binding.editFirtName.setError("First name is required");
             }

             if (TextUtils.isEmpty(lastNameValue)) {
                 isErrror = true;
                 binding.editLastName.setError("Last name is required");
             }

             if (!isErrror) {
                 String fullNameValue = firstNameValue + " " + lastNameValue;

                 binding.resultFullName.setText(String.format("Your full Name is: %s", String.valueOf(fullNameValue)));
             }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, binding.resultFullName.getText().toString());
    }
}