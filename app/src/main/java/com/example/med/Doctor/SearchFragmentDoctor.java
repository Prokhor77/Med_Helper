package com.example.med.Doctor;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.med.DBHelper;
import com.example.med.R;

public class SearchFragmentDoctor extends Fragment {

    private EditText searchEditText;
    private Button searchButton;
    private DBHelper dbHelper;

    public SearchFragmentDoctor() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_doctor, container, false);

        searchEditText = view.findViewById(R.id.search_text_doc);
        searchButton = view.findViewById(R.id.search_button_doc);
        dbHelper = new DBHelper(getActivity());

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchDoctor();
            }
        });

        return view;
    }

    private void searchDoctor() {
        String searchText = searchEditText.getText().toString().trim();
        if (searchText.isEmpty()) {
            Toast.makeText(getActivity(), "Введите текст для поиска", Toast.LENGTH_SHORT).show();
            return;
        }

        int userId = Integer.parseInt(searchText); // Предполагается, что searchText содержит ID
        Cursor cursor = dbHelper.searchDoctor(userId);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndexOrThrow("id"));
                String unix_epoch_create = cursor.getString(cursor.getColumnIndexOrThrow("unix_epoch_create"));
                String phone_number = cursor.getString(cursor.getColumnIndexOrThrow("phone_number"));
                String symtomps = cursor.getString(cursor.getColumnIndexOrThrow("symtomps"));
                String area = cursor.getString(cursor.getColumnIndexOrThrow("area"));
                String actual = cursor.getString(cursor.getColumnIndexOrThrow("actual"));
                String id_call = cursor.getString(cursor.getColumnIndexOrThrow("id_call"));

                Log.d("SearchFragmentDoctor", "id: " + id +
                        ", unix_epoch_create: " + unix_epoch_create +
                        ", phone_number: " + phone_number +
                        ", symtomps: " + symtomps +
                        ", area: " + area +
                        ", actual: " + actual +
                        ", id_call: " + id_call);
            }
        } else {
            Toast.makeText(getActivity(), "Вызовы у пользователя с ID " + userId + " не найдены", Toast.LENGTH_SHORT).show();
        }
    }
}