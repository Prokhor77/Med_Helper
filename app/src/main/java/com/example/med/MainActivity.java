package com.example.med;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextPassport;
    private EditText editTextFullName;
    private DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);
        new Handler().postDelayed(this::checkAuthorization, 2000);
    }

    private void checkAuthorization() {
        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        String passportId = sharedPreferences.getString("passportId", "");
        String fullName = sharedPreferences.getString("fullName", "");

        if (!passportId.isEmpty() && !fullName.isEmpty()) {
            int isDoctor = sharedPreferences.getInt("isDoctor", -1);
            startNextActivity(isDoctor);
        } else {
            showMainScreen();
        }
    }

    private void startNextActivity(int isDoctor) {
        Intent intent;
        switch (isDoctor) {
            case 0:
                intent = new Intent(MainActivity.this, UserActivity.class);
                break;
            case 1:
                intent = new Intent(MainActivity.this, DoctorActivity.class);
                break;
            case 2:
                intent = new Intent(MainActivity.this, SpravkaActivity.class);
                break;
            default:
                return;
        }
        startActivity(intent);
        finish();
    }

    private void showMainScreen() {
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);

        editTextPassport = findViewById(R.id.passport);
        editTextFullName = findViewById(R.id.full_name);

        Button buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(v -> login());
    }

    private void login() {
        String passportId = editTextPassport.getText().toString().trim();
        String fullName = editTextFullName.getText().toString().trim();

        Cursor cursor = dbHelper.getUser(passportId, fullName);
        if (cursor.moveToFirst()) {
            int isDoctor = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_IS_DOCTOR));

            SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("passportId", passportId);
            editor.putString("fullName", fullName);
            editor.putInt("isDoctor", isDoctor);
            editor.apply();

            startNextActivity(isDoctor);
        } else {
            Toast.makeText(this, "Неверные цифры или ФИО", Toast.LENGTH_SHORT).show();
        }

        cursor.close();
    }
}