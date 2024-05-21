package com.example.med.User;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.med.R;

public class RequestDoctorFragment extends Fragment {

    public RequestDoctorFragment() {
        // Required empty public constructor
    }

    public static RequestDoctorFragment newInstance() {
        return new RequestDoctorFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_request_doctor, container, false);

        Button buttonSubmit = view.findViewById(R.id.button_submit);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Логика для отправки данных
                // Например, сохранение данных и возврат к предыдущему фрагменту

                // Переход обратно к CallsFragmentUser
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new CallsFragmentUser());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }
}
