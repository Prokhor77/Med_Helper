package com.example.med.Adapters;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.med.DBHelper;
import com.example.med.Doctor.doctor_details_Activity;
import com.example.med.R;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class CallsAdapter extends RecyclerView.Adapter<CallsAdapter.CallsViewHolder> {
    
    private Cursor cursor;
    private DBHelper dbHelper;
    private Context context;

    public CallsAdapter(Context context, Cursor cursor, DBHelper dbHelper) {
        this.context = context;
        this.cursor = cursor;
        this.dbHelper = dbHelper;
    }

    public void swapCursor(Cursor newCursor) {
        if (cursor != null) {
            cursor.close();
        }
        cursor = newCursor;
        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public CallsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_call, parent, false);
        return new CallsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CallsViewHolder holder, int position) {
        if (cursor != null && cursor.moveToPosition(position)) {
            long unixEpochCreate = cursor.getLong(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_UNIX_EPOCH_CREATE));
            String phoneNumber = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_PHONE_NUMBER));
            String symtomps = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_SYMTOMPS));
            String id = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_ID));
            String id_call = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_ID_CALL));

            List<String> ids = Collections.singletonList(id);
            Cursor userCursor = dbHelper.getUsersByIds(ids);
            if (userCursor != null && userCursor.moveToFirst()) {
                do {
                    String name = userCursor.getString(userCursor.getColumnIndexOrThrow(DBHelper.COLUMN_FULL_NAME));
                    String adre = userCursor.getString(userCursor.getColumnIndexOrThrow(DBHelper.COLUMN_ADDRESS));

                    String formattedDate = formatDate(unixEpochCreate);

                    holder.unixEpochCreateTextView.setText("Дата вызова: " + formattedDate);
                    holder.phoneNumberTextView.setText("Номер телефона: " + phoneNumber);
                    holder.symtompsTextView.setText("Симптомы: " + symtomps);
                    holder.adreTextView.setText("Адрес: " + adre);
                    holder.nameTextView.setText("ФИО: " + name);

                    holder.buttonCall.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse("tel:" + phoneNumber));
                            v.getContext().startActivity(intent);
                        }
                    });

                    holder.buttonDetails.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, doctor_details_Activity.class);
                            intent.putExtra("id_call", id_call);
                            intent.putExtra("name", name);
                            intent.putExtra("adre", adre);
                            context.startActivity(intent);
                        }
                    });

                } while (userCursor.moveToNext());
                userCursor.close();

            }
        }
    }

    private String formatDate(long unixEpochCreate) {
        Date date = new Date(unixEpochCreate * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        return sdf.format(date);
    }


    @Override
    public int getItemCount() {
        return cursor != null ? cursor.getCount() : 0;
    }

    public static class CallsViewHolder extends RecyclerView.ViewHolder {
        TextView unixEpochCreateTextView, phoneNumberTextView, symtompsTextView, adreTextView, nameTextView;
        Button buttonCall, buttonDetails;

        public CallsViewHolder(@NonNull View itemView) {
            super(itemView);
            unixEpochCreateTextView = itemView.findViewById(R.id.text_unix_epoch_create);
            phoneNumberTextView = itemView.findViewById(R.id.text_phone_number);
            symtompsTextView = itemView.findViewById(R.id.text_symtomps);
            adreTextView = itemView.findViewById(R.id.text_adress);
            nameTextView = itemView.findViewById(R.id.text_name);
            buttonCall = itemView.findViewById(R.id.button_call);
            buttonDetails = itemView.findViewById(R.id.button_view_details);
        }
    }
}