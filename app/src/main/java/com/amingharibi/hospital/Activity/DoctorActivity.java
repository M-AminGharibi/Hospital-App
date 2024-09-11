package com.amingharibi.hospital.Activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.amingharibi.hospital.Adapter.DoctorAdapter;
import com.amingharibi.hospital.Domain.Doctor;
import com.amingharibi.hospital.Domain.DoctorDataHolder;
import com.amingharibi.hospital.databinding.ActivityDoctorBinding;

import java.util.List;

public class DoctorActivity extends AppCompatActivity {
    ActivityDoctorBinding binding;
    List<Doctor> fullList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityDoctorBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        binding.progressBarDoctor.setVisibility(View.VISIBLE);



        fullList = DoctorDataHolder.getInstance().getFullList();


        initDoctorSection();

        binding.searchBarDoc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //blogListAdapter.filter(s.toString());
                DoctorAdapter adapter = (DoctorAdapter) binding.doctorView.getAdapter();
                if (adapter != null) {
                    adapter.filter(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    private void initDoctorSection() {

        binding.doctorView.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.VERTICAL,false));
        binding.doctorView.setAdapter(new DoctorAdapter(fullList));
        if (fullList != null) {
            binding.progressBarDoctor.setVisibility(View.GONE);
        }
        binding.backButtonDoctor.setOnClickListener(view1 -> finish());
    }


}