package com.example.med.User;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.med.Adapters.CallsUsAdapter;
import com.example.med.DBHelper;
import com.example.med.R;

public class CallsFragmentUser extends Fragment {

    private Button buttonCallDoctor;
    private RecyclerView recyclerView;
    private DBHelper dbHelper;
    private CallsUsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calls_user, container, false);

        dbHelper = new DBHelper(getContext());

        buttonCallDoctor = view.findViewById(R.id.button_call_doctor);
        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        loadData();

        buttonCallDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonCallDoctor.setVisibility(View.GONE);

                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new RequestDoctorFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        buttonCallDoctor.setVisibility(View.VISIBLE);
    }

    private void loadData() {
        Cursor cursor = dbHelper.getCallsUser();
        adapter = new CallsUsAdapter(getContext(), cursor);
        recyclerView.setAdapter(adapter);
    }


}
