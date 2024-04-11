package com.example.med.Doctor;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.med.DBHelper;
import com.example.med.R;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class doctor_details_Activity extends AppCompatActivity {

    private DBHelper dbHelper;
    private long unixEpochEndTime;


    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        dbHelper = new DBHelper(this);

        String id_call  = getIntent().getStringExtra("id_call");
        String name  = getIntent().getStringExtra("name");
        String adre  = getIntent().getStringExtra("adre");

        TextView nameTextView = findViewById(R.id.name);
        TextView adreTextView = findViewById(R.id.adre);
        TextView idTextView = findViewById(R.id.id);
        EditText symtompsEditText = findViewById(R.id.symtomps);
        EditText diagnosEditText = findViewById(R.id.diagnos);
        EditText treatmentEditText = findViewById(R.id.treatment);
        Button buttonExit = findViewById(R.id.back_to_menu_button);
        Button buttonEnd = findViewById(R.id.finish_appointment_button);
        DatePicker datePicker = findViewById(R.id.appoint_time_picker);



        Cursor cursor = dbHelper.getCallById(id_call);
        if (cursor.moveToFirst()) {
            String symtomps = cursor.getString(cursor.getColumnIndexOrThrow("symtomps"));
            String diagnosis = cursor.getString(cursor.getColumnIndexOrThrow("diagnosis"));
            String treatment = cursor.getString(cursor.getColumnIndexOrThrow("treatment"));
            String id = cursor.getString(cursor.getColumnIndexOrThrow("id"));

            SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("id", id);
            editor.apply();

            nameTextView.setText("ФИО пацинета:  " + name);
            adreTextView.setText("Адресс:  " + adre);
            idTextView.setText("ID человека:" + id);

            diagnosEditText.setText(diagnosis);
            symtompsEditText.setText(symtomps);
            treatmentEditText.setText(treatment);
        }

        buttonExit.setOnClickListener(view -> {
            finish();
        });

        buttonEnd.setOnClickListener(view -> {
            long currentTimeMillis = System.currentTimeMillis();
            unixEpochEndTime = currentTimeMillis / 1000;

            int day = datePicker.getDayOfMonth();
            int month = datePicker.getMonth();
            int year = datePicker.getYear();

            String selectedDate = day + "/" + (month + 1) + "/" + year;

            String symtompsValue = symtompsEditText.getText().toString();
            String diagnosValue = diagnosEditText.getText().toString();
            String treatmentValue = treatmentEditText.getText().toString();

            Completable.fromCallable(() -> {
                        dbHelper.updateCall(id_call, symtompsValue, diagnosValue, treatmentValue, selectedDate, unixEpochEndTime);
                        return Completable.complete();
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                        long currentThreadId = Thread.currentThread().getId();
                        Log.d("INFOOOOOO", "ПАЦИЕНТ УСПЕШНО СОХРАНЕН. Поток: " + currentThreadId);
                        Toast.makeText(this, "ПАЦИЕНТ УСПЕШНО СОХРАНЕН", Toast.LENGTH_SHORT).show();

                        finish(); // ОСН ПОТОК
                    }, throwable -> {
                        long currentThreadId = Thread.currentThread().getId();
                        Log.e("INFOOOOOO", "ПРОИЗОШЛА ОШИБКА ПРИ СОХРАНЕНИИ ПАЦИЕНТА. Поток: " + currentThreadId, throwable);
                        Toast.makeText(this, "ПРОИЗОШЛА ОШИБКА ПРИ СОХРАНЕНИИ ПАЦИЕНТА", Toast.LENGTH_SHORT).show();
                    });
        });

        cursor.close();
        dbHelper.close();
    }

}