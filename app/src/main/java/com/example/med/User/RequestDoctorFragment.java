package com.example.med.User;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.med.DBHelper;
import com.example.med.R;

public class RequestDoctorFragment extends Fragment {
    private DBHelper dbHelper;
    private Context context;
    long epochTimeSeconds = System.currentTimeMillis() / 1000;

    public RequestDoctorFragment() {
    }

    public static RequestDoctorFragment newInstance() {
        return new RequestDoctorFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_request_doctor, container, false);

        dbHelper = new DBHelper(getContext()); // Initialize the dbHelper object

        Button buttonSubmit = view.findViewById(R.id.button_submit);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextSymptoms = view.findViewById(R.id.editText_symptoms);
                EditText editTextPhone = view.findViewById(R.id.editText_phone);

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
                String idFromSharedPreferences = sharedPreferences.getString("id", "");
                String areaFromSharedPreferences = sharedPreferences.getString("area", "");

                boolean inserted = dbHelper.insertCall(epochTimeSeconds, Integer.parseInt(idFromSharedPreferences), editTextPhone.getText().toString(), editTextSymptoms.getText().toString(), areaFromSharedPreferences, 1, (int) (Math.random() * 100000));

                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new CallsFragmentUser());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }


}
