package com.example.med;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.med.databinding.ActivityUserBinding;

public class UserActivity extends AppCompatActivity {

    ActivityUserBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new ProfileFragmentUser());

        binding.bottomNavigationViewUser.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.profile_user) {
                replaceFragment(new ProfileFragmentUser());

            } else if (item.getItemId() == R.id.calls_user) {
                replaceFragment(new CallsFragmentUser());

            } else if (item.getItemId() == R.id.search_user) {
                replaceFragment(new SearchFragmentUser());

            } else if (item.getItemId() == R.id.spravka_user) {
                replaceFragment(new SpravkaFragmentUser());

            }
            return true;
        });
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_user,fragment);
        fragmentTransaction.commit();

    }

}