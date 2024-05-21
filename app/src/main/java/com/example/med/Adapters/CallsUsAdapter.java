package com.example.med.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.med.R;

public class CallsUsAdapter extends RecyclerView.Adapter<CallsUsAdapter.ViewHolder> {

    private Context context;
    private Cursor cursor;

    public CallsUsAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_uscall, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (cursor.moveToPosition(position)) {
            String unixEpochCreate = cursor.getString(cursor.getColumnIndexOrThrow("unix_epoch_create"));
            String id = cursor.getString(cursor.getColumnIndexOrThrow("id"));
            String phoneNumber = cursor.getString(cursor.getColumnIndexOrThrow("phone_number"));
            String symptoms = cursor.getString(cursor.getColumnIndexOrThrow("symtomps"));
            String diagnosis = cursor.getString(cursor.getColumnIndexOrThrow("diagnosis"));
            String treatment = cursor.getString(cursor.getColumnIndexOrThrow("treatment"));
            String appointmentTime = cursor.getString(cursor.getColumnIndexOrThrow("appointment_time"));
            String unixEpochEnd = cursor.getString(cursor.getColumnIndexOrThrow("unix_epoch_end"));
            String area = cursor.getString(cursor.getColumnIndexOrThrow("area"));
            String actual = cursor.getString(cursor.getColumnIndexOrThrow("actual"));
            String idCall = cursor.getString(cursor.getColumnIndexOrThrow("id_call"));

            holder.textView.setText(
                    unixEpochCreate + "\n" +
                            id + "\n" +
                            phoneNumber + "\n" +
                            symptoms + "\n" +
                            diagnosis + "\n" +
                            treatment + "\n" +
                            appointmentTime + "\n" +
                            unixEpochEnd + "\n" +
                            area + "\n" +
                            actual + "\n" +
                            idCall
            );
        }
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view);
        }
    }
}

