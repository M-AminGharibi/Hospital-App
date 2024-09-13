package com.amingharibi.hospital.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amingharibi.hospital.Adapter.CategoryAdapter;
import com.amingharibi.hospital.Adapter.DoctorAdapter;
import com.amingharibi.hospital.Domain.Category;
import com.amingharibi.hospital.Domain.CategoryDataHolder;
import com.amingharibi.hospital.Domain.Doctor;
import com.amingharibi.hospital.Domain.DoctorDataHolder;
import com.amingharibi.hospital.R;
import com.amingharibi.hospital.databinding.ActivityMainBinding;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    List<Category> fullListCat;
    List<Category> limitedListCat;
    List<Doctor> fullListDoc;
    List<Doctor> limitedListDoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        fullListCat = new ArrayList<>();
        fullListDoc = new ArrayList<>();

        setupSeeAllText();

        initCategoryMain();
        initDoctorMain();

        DoctorDataHolder.getInstance().setFullList(fullListDoc);
        CategoryDataHolder.getInstance().setFullList(fullListCat);

        binding.seeAllDocTV.setOnClickListener(view12 -> {
            Intent intent = new Intent(MainActivity.this, DoctorActivity.class);

            startActivity(intent);
        });

        binding.seeAllCatTV.setOnClickListener(view1 -> {
            Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
            startActivity(intent);
        });

        binding.searchBarMain.setOnClickListener(v -> openSearchFragment());


        binding.searchBarMain.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                openSearchFragment();
            }
        });


        binding.searchBarMain.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                RecyclerView recyclerViewDoctorsSearch = findViewById(R.id.recyclerViewDoctorsSearch);
                RecyclerView recyclerViewCategorySearch = findViewById(R.id.recyclerViewCategorySearch);

                DoctorAdapter doctorAdapter = (DoctorAdapter) recyclerViewDoctorsSearch.getAdapter();
                CategoryAdapter categoryAdapter = (CategoryAdapter) recyclerViewCategorySearch.getAdapter();
                if (doctorAdapter != null || categoryAdapter != null) {
                    doctorAdapter.filter(binding.searchBarMain.getText().toString());
                    categoryAdapter.filter(binding.searchBarMain.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @SuppressLint("ResourceAsColor")
    private void setupSeeAllText() {
        binding.searchBarMain.setHintTextColor(R.color.black);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            // برای API 17 و بالاتر
            binding.seeAllCatTV.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.less_than, 0, 0, 0);
            binding.seeAllDocTV.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.less_than, 0, 0, 0);
        } else {
            // برای API های قدیمی‌تر
            binding.seeAllCatTV.setCompoundDrawablesWithIntrinsicBounds(R.drawable.less_than, 0, 0, 0);
            binding.seeAllDocTV.setCompoundDrawablesWithIntrinsicBounds(R.drawable.less_than, 0, 0, 0);
        }

    }


    private void initDoctorMain() {
        binding.progressBarDoctor.setVisibility(View.VISIBLE);
        binding.doctorView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Docters");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    // داده‌ها با موفقیت بازیابی شدند
                    for (ParseObject parseDoctor : objects) {
                        int categoryIdDoctor = parseDoctor.getInt("CategoryId");
                        ParseFile imagePath = parseDoctor.getParseFile("ImageDoc");
                        String categoryName = parseDoctor.getString("Category");
                        String doctorName = parseDoctor.getString("FullName");
                        String doctorTime = parseDoctor.getString("Dates");
                        Doctor doctor = Doctor.fromParseObject(parseDoctor);
                        fullListDoc.add(doctor);
                    }
                    limitedListDoc = getLimitedList(fullListDoc, 4);
                    binding.doctorView.setAdapter(new DoctorAdapter(limitedListDoc));
                    binding.progressBarDoctor.setVisibility(View.GONE);
                } else {
                    // خطا در بازیابی داده‌ها
                    e.printStackTrace();
                }
            }
        });

    }


    private void initCategoryMain() {
        binding.progressBarCategory.setVisibility(View.VISIBLE);
        binding.categoryView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Category");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    // داده‌ها با موفقیت بازیابی شدند
                    for (ParseObject parseCategory : objects) {
                        int categoryIdParse = parseCategory.getInt("CategoryId");
                        ParseFile imagePath = parseCategory.getParseFile("ImagePath");
                        String categoryName = parseCategory.getString("CategoryName");
                        Category category = Category.fromParseObject(parseCategory);
                        fullListCat.add(category);
                    }
                    limitedListCat = getLimitedList(fullListCat, 4);
                    binding.categoryView.setAdapter(new CategoryAdapter(limitedListCat));
                    binding.progressBarCategory.setVisibility(View.GONE);
                } else {
                    // خطا در بازیابی داده‌ها
                    e.printStackTrace();
                }
            }
        });
    }


    private <T> List<T> getLimitedList(List<T> originalList, int limit) {
        if (originalList.size() <= limit) {
            return new ArrayList<>(originalList);
        } else {
            return new ArrayList<>(originalList.subList(0, limit));
        }
    }


    private void openSearchFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment searchFragment = fragmentManager.findFragmentByTag("SEARCH_FRAGMENT");
        binding.fragmentContainer.bringToFront();
        if (searchFragment == null) {
            // Fragment is not already opened, so open it
            searchFragment = new SearchFragment();
            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.slide_up, R.anim.slide_down, R.anim.slide_up,R.anim.slide_down)
                    .replace(R.id.fragment_container, searchFragment, "SEARCH_FRAGMENT")
                    .addToBackStack(null)
                    .commit();
        }
    }

}