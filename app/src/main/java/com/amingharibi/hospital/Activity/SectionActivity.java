package com.amingharibi.hospital.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.amingharibi.hospital.Adapter.CategoryAdapter;
import com.amingharibi.hospital.Domain.Category;
import com.amingharibi.hospital.databinding.ActivitySectionBinding;

import java.util.List;

public class SectionActivity extends AppCompatActivity {
    ActivitySectionBinding binding;
    List<Category> fullList;
    CategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySectionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.progressBarSection.setVisibility(View.VISIBLE);
        fullList = (List<Category>) getIntent().getSerializableExtra("fullList");
        initCategorySection();


    }


    public void initCategorySection() {


        binding.sectionView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new CategoryAdapter(fullList);
        binding.sectionView.setAdapter(adapter);


        binding.progressBarSection.setVisibility(View.GONE);
    }
}