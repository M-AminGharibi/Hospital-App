package com.amingharibi.hospital.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.amingharibi.hospital.Adapter.CategoryAdapter;
import com.amingharibi.hospital.Adapter.DoctorAdapter;
import com.amingharibi.hospital.Domain.Category;
import com.amingharibi.hospital.Domain.CategoryDataHolder;
import com.amingharibi.hospital.Domain.Doctor;
import com.amingharibi.hospital.Domain.DoctorDataHolder;
import com.amingharibi.hospital.databinding.FragmentSearchBinding;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    private FragmentSearchBinding binding;

    private List<Doctor> doctorFullList = new ArrayList<>();
    private List<Category> categoryFullList = new ArrayList<>();

    private DoctorAdapter doctorAdapter;
    private CategoryAdapter categoryAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);

        doctorFullList = DoctorDataHolder.getInstance().getFullList();
        categoryFullList = CategoryDataHolder.getInstance().getFullList();


        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerViews();

    }

    private void setupRecyclerViews() {

        binding.recyclerViewDoctorsSearch.setLayoutManager(new LinearLayoutManager(getContext() , LinearLayoutManager.HORIZONTAL,false));
        binding.recyclerViewCategorySearch.setLayoutManager(new GridLayoutManager(getContext() , 2));


        doctorAdapter = new DoctorAdapter(doctorFullList);
        categoryAdapter = new CategoryAdapter(categoryFullList);

        binding.recyclerViewDoctorsSearch.setAdapter(doctorAdapter);
        binding.recyclerViewCategorySearch.setAdapter(categoryAdapter);


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
