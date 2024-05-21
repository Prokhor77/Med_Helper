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

    private Button buttonCallDoctor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calls_user, container, false);

        buttonCallDoctor = view.findViewById(R.id.button_call_doctor);
        buttonCallDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Скрыть кнопку перед переходом к RequestDoctorFragment
                buttonCallDoctor.setVisibility(View.GONE);

                // Переход к RequestDoctorFragment
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new RequestDoctorFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Показывать кнопку, если это CallsFragmentUser
        buttonCallDoctor.setVisibility(View.VISIBLE);
    }
}
