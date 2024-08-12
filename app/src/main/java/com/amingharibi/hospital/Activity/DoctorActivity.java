package com.amingharibi.hospital.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.amingharibi.hospital.R;
import com.amingharibi.hospital.databinding.ActivityDoctorBinding;

public class DoctorActivity extends AppCompatActivity {
    ActivityDoctorBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityDoctorBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}