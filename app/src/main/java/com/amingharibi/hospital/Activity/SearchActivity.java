package com.amingharibi.hospital.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.amingharibi.hospital.databinding.ActivitySearchBinding;

public class SearchActivity extends AppCompatActivity {
    ActivitySearchBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}