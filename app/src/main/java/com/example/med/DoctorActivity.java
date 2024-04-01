package com.example.med;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.med.databinding.ActivityDoctorBinding;

public class DoctorActivity extends AppCompatActivity {

    ActivityDoctorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDoctorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new profileFragmentDoctor());

        binding.bottomNavigationViewDoctor.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.profile_doctor) {
                replaceFragment(new profileFragmentDoctor());

            } else if (item.getItemId() == R.id.calls_doctor) {
                replaceFragment(new CallsFragmentDoctor());

            } else if (item.getItemId() == R.id.search_doctor) {
                replaceFragment(new SearchFragmentDoctor());

            } else if (item.getItemId() == R.id.spravka_doctor) {
                replaceFragment(new SpravkaFragmentDoctor());

            }
            return true;
        });
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_doctor,fragment);
        fragmentTransaction.commit();

    }

}