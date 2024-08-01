package com.amingharibi.hospital.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.amingharibi.hospital.Adapter.CategoryAdapter;
import com.amingharibi.hospital.Domain.Category;
import com.amingharibi.hospital.Domain.DataHolder;
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
        Intent intent = getIntent();
        binding.progressBarSection.setVisibility(View.VISIBLE);

//        fullList = (List<Category>) getIntent().getSerializableExtra("fullList");
//        fullList = intent.getParcelableArrayListExtra("fullList");
        fullList = DataHolder.getInstance().getFullList();

        initCategorySection();


    }


    public void initCategorySection() {


        binding.sectionView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new CategoryAdapter(fullList);
        binding.sectionView.setAdapter(adapter);


        binding.progressBarSection.setVisibility(View.GONE);
    }
}