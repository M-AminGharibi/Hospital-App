package com.amingharibi.hospital.Activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.amingharibi.hospital.Adapter.CategoryAdapter;
import com.amingharibi.hospital.Domain.Category;
import com.amingharibi.hospital.Domain.DataHolder;
import com.amingharibi.hospital.databinding.ActivityCategoryBinding;

import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    ActivityCategoryBinding binding;
    List<Category> fullList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityCategoryBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        binding.progressBarSection.setVisibility(View.VISIBLE);

        fullList = DataHolder.getInstance().getFullList();

        initCategorySection();


        binding.searchBarCat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //blogListAdapter.filter(s.toString());
                CategoryAdapter adapter = (CategoryAdapter) binding.sectionView.getAdapter();
                if (adapter != null) {
                    adapter.filter(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });




    }

    public void initCategorySection() {

        binding.sectionView.setLayoutManager(new GridLayoutManager(this, 2));
        binding.sectionView.setAdapter(new CategoryAdapter(fullList));
        if (fullList != null) {
            binding.progressBarSection.setVisibility(View.GONE);
        }
        binding.backButtonSection.setOnClickListener(view -> finish());

    }
}