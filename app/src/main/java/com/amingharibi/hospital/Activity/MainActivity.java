package com.amingharibi.hospital.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.amingharibi.hospital.Adapter.CategoryAdapter;
import com.amingharibi.hospital.Adapter.DoctorAdapter;
import com.amingharibi.hospital.Domain.Category;
import com.amingharibi.hospital.Domain.CategoryDataHolder;
import com.amingharibi.hospital.Domain.Doctor;
import com.amingharibi.hospital.Domain.DoctorDataHolder;
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


        initCategoryMain();
        initDoctorMain();

        binding.seeAllDocTV.setOnClickListener(view12 -> {
            Intent intent = new Intent(MainActivity.this, DoctorActivity.class);
            DoctorDataHolder.getInstance().setFullList(fullListDoc);
            startActivity(intent);
        });

        binding.seeAllCatTV.setOnClickListener(view1 -> {
            Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
            CategoryDataHolder.getInstance().setFullList(fullListCat);
            startActivity(intent);
        });


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


}