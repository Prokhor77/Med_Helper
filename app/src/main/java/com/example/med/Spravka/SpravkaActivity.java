package com.example.med.Spravka;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.med.R;
import com.example.med.User.CallsFragmentUser;
import com.example.med.User.ProfileFragmentUser;
import com.example.med.User.SpravkaFragmentUser;
import com.example.med.databinding.ActivitySpravkaBinding;
import com.example.med.databinding.ActivityUserBinding;

public class SpravkaActivity extends AppCompatActivity {

    ActivitySpravkaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySpravkaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new ProfileFragmentSpravka());

        binding.bottomNavigationViewSpravka.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.profile_spravka) {
                replaceFragment(new ProfileFragmentSpravka());

            } else if (item.getItemId() == R.id.search_spravka) {
                replaceFragment(new CallsFragmentSpravka());

            } else if (item.getItemId() == R.id.spravka_spravka) {
                replaceFragment(new SpravkaFragmentSpravka());

            }
            return true;
        });
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_spravka,fragment);
        fragmentTransaction.commit();

    }
}