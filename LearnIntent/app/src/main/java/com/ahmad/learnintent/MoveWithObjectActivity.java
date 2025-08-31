package com.ahmad.learnintent;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MoveWithObjectActivity extends AppCompatActivity {
    public static final String EXTRA_STUDENT = "extra_student";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_with_object);
        TextView tvObject = findViewById(R.id.tv_object_received);

        Student student;
        if (Build.VERSION.SDK_INT >= 33) {
            student = getIntent().getParcelableExtra(EXTRA_STUDENT, Student.class);
        } else {
            student = getIntent().getParcelableExtra(EXTRA_STUDENT);
        }

        String text = "Name : " + student.getName() + ",\nEmail : " + student.getEmail() + ",\nAge : " + student.getAge() + ",\nLocation : " + student.getCity();
        tvObject.setText(text);
    }
}