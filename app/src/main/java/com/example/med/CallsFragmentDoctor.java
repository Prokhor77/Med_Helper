package com.example.med;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class CallsFragmentDoctor extends Fragment {

    private DBHelper dbHelper;
    private CallsAdapter callsAdapter;
    private Context context;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calls_doctor, container, false);

        dbHelper = new DBHelper(getContext());
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Cursor cursor = dbHelper.getCalls();
        callsAdapter = new CallsAdapter(requireContext(), cursor, dbHelper);
        recyclerView.setAdapter(callsAdapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (callsAdapter != null) {
            callsAdapter.swapCursor(null);
        }
        if (dbHelper != null) {
            dbHelper.close();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Cursor newCursor = dbHelper.getCalls();
        callsAdapter.swapCursor(newCursor);
    }

}