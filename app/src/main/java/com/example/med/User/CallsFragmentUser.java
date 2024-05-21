package com.example.med.User;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.med.R;

public class CallsFragmentUser extends Fragment {

    // Параметры и методы

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calls_user, container, false);

        Button buttonCallDoctor = view.findViewById(R.id.button_call_doctor);
        buttonCallDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Переход к RequestDoctorFragment
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new RequestDoctorFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }
}
