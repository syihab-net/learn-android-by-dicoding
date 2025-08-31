package com.ahmad.learnintent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvResult;

    final ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == MoveForResultActivity.RESULT_CODE && result.getData() != null) {
            int selectedValue = result.getData().getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE, 0);
            tvResult.setText(String.format("Hasil : %s", selectedValue));
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMoveActivity = findViewById(R.id.btn_move_activity);
        btnMoveActivity.setOnClickListener(this);

        Button btnWithDataActivity = findViewById(R.id.btn_move_activity_data);
        btnWithDataActivity.setOnClickListener(this);

        Button btnMoveWithObject = findViewById(R.id.btn_move_activity_object);
        btnMoveWithObject.setOnClickListener(this);

        Button btnDialPhone = findViewById(R.id.btn_dial_number);
        btnDialPhone.setOnClickListener(this);

        Button btnMoveForResult = findViewById(R.id.btn_move_for_result);
        btnMoveForResult.setOnClickListener(this);

        tvResult = findViewById(R.id.tv_result);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_move_activity) {
            Intent moveIntent = new Intent(MainActivity.this, MoveActivity.class);
            startActivity(moveIntent);
        } else if (v.getId() == R.id.btn_move_activity_data) {
            Intent moveWithDataInten = new Intent(MainActivity.this, MoveWithDataActivity.class);
            moveWithDataInten.putExtra(MoveWithDataActivity.EXTRA_NAME, "Ahmad Sihabudin");
            moveWithDataInten.putExtra(MoveWithDataActivity.EXTRA_AGE, 26);
            startActivity(moveWithDataInten);
        } else if (v.getId() == R.id.btn_move_activity_object) {
            Student student = new Student();
            student.setName("Ahmad Sihabudin");
            student.setAge(26);
            student.setEmail("ahmad.syihab@gmail.com");
            student.setCity("Banyuwangi");

            Intent moveWithObjectInten = new Intent(MainActivity.this, MoveWithObjectActivity.class);
            moveWithObjectInten.putExtra(MoveWithObjectActivity.EXTRA_STUDENT, student);
            startActivity(moveWithObjectInten);
        } else if (v.getId() == R.id.btn_dial_number) {
            String phoneNumber = "087730352565";
            Intent dialPhoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
            startActivity(dialPhoneIntent);
        } else if (v.getId() == R.id.btn_move_for_result) {
            Intent moveForResultIntent = new Intent(MainActivity.this, MoveForResultActivity.class);
            resultLauncher.launch(moveForResultIntent);
        }
    }
}