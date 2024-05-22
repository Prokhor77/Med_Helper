package com.example.med.Doctor;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.med.DBHelper;
import com.example.med.Adapters.DoctorCallsAdapter;
import com.example.med.R;

public class SearchFragmentDoctor extends Fragment {

    private EditText searchEditText;
    private Button searchButton;
    private Button clearButton;
    private DBHelper dbHelper;
    private RecyclerView recyclerView;
    private DoctorCallsAdapter adapter;

    public SearchFragmentDoctor() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_doctor, container, false);

        searchEditText = view.findViewById(R.id.search_text_doc);
        searchButton = view.findViewById(R.id.search_button_doc);
        clearButton = view.findViewById(R.id.clear_button_doc);
        dbHelper = new DBHelper(getActivity());
        recyclerView = view.findViewById(R.id.recyclerViewResults);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchDoctor();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchEditText.setText(""); // Очистка текстового поля
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
            adapter = new DoctorCallsAdapter(cursor);
            recyclerView.setAdapter(adapter);
        } else {
            Toast.makeText(getActivity(), "Вызовы у пользователя с ID " + userId + " не найдены", Toast.LENGTH_SHORT).show();
        }
    }

    public void clearSearchText(View view) {
        searchEditText.setText("");
    }
}
