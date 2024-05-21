package com.example.med.Doctor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.med.DBHelper;
import com.example.med.MainActivity;
import com.example.med.R;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Map;

public class profileFragmentDoctor extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_doctor, container, false);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        String passportId = sharedPreferences.getString("passportId", "");
        String fullName = sharedPreferences.getString("fullName", "");

        Button logoutButton = view.findViewById(R.id.logoutButton);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LineChart lineChart = view.findViewById(R.id.lineChart);

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0, 4));
        entries.add(new Entry(1, 8));
        entries.add(new Entry(2, 6));
        entries.add(new Entry(3, 2));
        entries.add(new Entry(4, 7));

        LineDataSet dataSet = new LineDataSet(entries, "Label");
        dataSet.setColor(ColorTemplate.rgb("#FF5722"));
        dataSet.setValueTextColor(ColorTemplate.rgb("#3F51B5"));

        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);

        Description description = new Description();
        description.setText("График");
        lineChart.setDescription(description);

        lineChart.invalidate();

        long epochTimeSeconds = System.currentTimeMillis() / 1000;
        Log.d("MainActivity", "Текущее время в формате epoch time: " + epochTimeSeconds);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        Map<String, ?> allEntries = sharedPreferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            Log.d("SharedPreferences", entry.getKey() + ": " + entry.getValue().toString());
        }
        String fullName = sharedPreferences.getString("fullName", "");

        TextView passportTextView = view.findViewById(R.id.passportTextView);
        TextView adressTextView = view.findViewById(R.id.adressTextView);
        TextView fullnameTextView = view.findViewById(R.id.fullnameTextView);
        TextView areaTextView = view.findViewById(R.id.areaTextView);
        TextView statusTextView = view.findViewById(R.id.statusTextView);

        Button logoutButton = view.findViewById(R.id.logoutButton);

        DBHelper dbHelper = new DBHelper(getActivity());
        Cursor cursor = dbHelper.getUserByFullName(fullName);

        if (cursor.moveToFirst()) {
            String statusText;

            String passportId = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_PASSPORT_ID));
            String address = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_ADDRESS));
            String fullname = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_FULL_NAME));
            String area = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_AREA));
            String id = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_ID));
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("area", area);
            editor.putString("id", id);
            editor.apply();

            int statusInt = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_IS_DOCTOR));

            if (statusInt == 0) {
                statusText = "Пациент";
            } else if (statusInt == 1) {
                statusText = "Доктор";
            } else if (statusInt == 2) {
                statusText = "Справка";
            } else {
                statusText = "Неизвестный статус";
            }

            passportTextView.setText("Идентификационный номер: "+passportId);
            adressTextView.setText("Адресс: "+address);
            fullnameTextView.setText("ФИО: "+fullname);
            areaTextView.setText("Участок: "+area);
            statusTextView.setText("Статус: " + statusText);
        }

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        cursor.close();
    }


}
