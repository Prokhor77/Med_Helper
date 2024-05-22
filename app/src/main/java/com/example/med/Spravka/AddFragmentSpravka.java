package com.example.med.Spravka;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.med.DBHelper;
import com.example.med.R;

import java.util.Random;

public class AddFragmentSpravka extends Fragment {

    private EditText editTextPassport, editTextAddress, editTextFullName, editTextRegion;
    private RadioGroup radioGroupOptions;
    private Button buttonCreate;

    private DBHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_spravka, container, false);

        dbHelper = new DBHelper(getActivity());

        editTextPassport = view.findViewById(R.id.editTextPassport);
        editTextAddress = view.findViewById(R.id.editTextAddress);
        editTextFullName = view.findViewById(R.id.editTextFullName);
        editTextRegion = view.findViewById(R.id.editTextRegion);
        radioGroupOptions = view.findViewById(R.id.radioGroupOptions);
        buttonCreate = view.findViewById(R.id.buttonCreate);

        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    insertData();
                    clearFields();
                    Toast.makeText(getActivity(), "Успешно записано в базу данных", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private boolean validateFields() {
        return !editTextPassport.getText().toString().isEmpty() &&
                !editTextAddress.getText().toString().isEmpty() &&
                !editTextFullName.getText().toString().isEmpty() &&
                !editTextRegion.getText().toString().isEmpty();
    }

    private void clearFields() {
        editTextPassport.setText("");
        editTextAddress.setText("");
        editTextFullName.setText("");
        editTextRegion.setText("");
        radioGroupOptions.clearCheck();
    }

    private void insertData() {
        String passport = editTextPassport.getText().toString();
        String address = editTextAddress.getText().toString();
        String fullName = editTextFullName.getText().toString();
        String region = editTextRegion.getText().toString();
        int id = new Random().nextInt(1000000);
        int idDoctor = radioGroupOptions.getCheckedRadioButtonId() == R.id.radioButtonUser ? 0 : 1;

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("passport_id", passport);
        values.put("address", address);
        values.put("full_name", fullName);
        values.put("area", region);
        values.put("id", id);
        values.put("is_doctor", idDoctor);

        db.insert("users", null, values);
        db.close();
    }
}
