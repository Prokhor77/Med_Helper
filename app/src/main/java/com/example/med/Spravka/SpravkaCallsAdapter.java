package com.example.med.Spravka;


import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.med.Adapters.DoctorCallsAdapter;
import com.example.med.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class SpravkaCallsAdapter extends RecyclerView.Adapter<DoctorCallsAdapter.ViewHolder> {

    private Cursor cursor;

    public SpravkaCallsAdapter(Cursor cursor) {
        this.cursor = cursor;
    }

    @NonNull
    @Override
    public DoctorCallsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_doccall, parent, false);
        return new DoctorCallsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorCallsAdapter.ViewHolder holder, int position) {
        if (!cursor.moveToPosition(position)) {
            return;
        }

        String unixEpochCreate = cursor.getString(cursor.getColumnIndexOrThrow("unix_epoch_create"));
        String id = cursor.getString(cursor.getColumnIndexOrThrow("id"));
        String phoneNumber = cursor.getString(cursor.getColumnIndexOrThrow("phone_number"));
        String symptoms = cursor.getString(cursor.getColumnIndexOrThrow("symtomps"));
        String diagnosis = cursor.getString(cursor.getColumnIndexOrThrow("diagnosis"));
        String treatment = cursor.getString(cursor.getColumnIndexOrThrow("treatment"));
        String actual = cursor.getString(cursor.getColumnIndexOrThrow("actual"));
        String idCall = cursor.getString(cursor.getColumnIndexOrThrow("id_call"));
        String area = cursor.getString(cursor.getColumnIndexOrThrow("area"));

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
        String formattedDate = dateFormat.format(new Date(Long.parseLong(unixEpochCreate) * 1000));

        String actualText = actual.equals("1") ? "Актуален" : "Неактуален";

        holder.unixEpochCreateTextView.setText("Дата вызова: " + formattedDate);
        holder.idTextView.setText("ID Аккаунта: " + id);
        holder.phoneNumberTextView.setText("Номер телефона: " + phoneNumber);
        holder.symptomsTextView.setText("Симптомы: " + symptoms);
        holder.symptomsTextView.setText("Диагноз: " + diagnosis);
        holder.symptomsTextView.setText("Лечение: " + treatment);
        holder.areaTextView.setText("Район: " + area);
        holder.actualTextView.setText("Актуален вызов: " + actualText);
        holder.idCallTextView.setText("ID вызова: " + idCall);
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView idTextView;
        public TextView unixEpochCreateTextView;
        public TextView phoneNumberTextView;
        public TextView symptomsTextView;
        public TextView areaTextView;
        public TextView actualTextView;
        public TextView idCallTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idTextView = itemView.findViewById(R.id.text_id);
            unixEpochCreateTextView = itemView.findViewById(R.id.text_unix_epoch_create);
            phoneNumberTextView = itemView.findViewById(R.id.text_phone_number);
            symptomsTextView = itemView.findViewById(R.id.text_symptoms);
            areaTextView = itemView.findViewById(R.id.text_area);
            actualTextView = itemView.findViewById(R.id.text_actual);
            idCallTextView = itemView.findViewById(R.id.text_id_call);
        }
    }
}