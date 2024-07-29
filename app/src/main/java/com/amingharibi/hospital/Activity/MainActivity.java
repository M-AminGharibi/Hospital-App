package com.amingharibi.hospital.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.amingharibi.hospital.Adapter.CategoryAdapter;
import com.amingharibi.hospital.Domain.Category;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initCategory();


        binding.seeAllTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SectionActivity.class);
                startActivity(intent);
            }
        });


    }

    private void initCategory() {
        binding.progressBarCategory.setVisibility(View.VISIBLE);
        ArrayList<Category> list = new ArrayList<>();
        binding.categoryView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Category");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    // داده‌ها با موفقیت بازیابی شدند
                    for (ParseObject Category : objects) {
                        ParseFile imagePath = Category.getParseFile("ImagePath");
                        String categoryName = Category.getString("CategoryName");
                        Category category = com.amingharibi.hospital.Domain.Category.fromParseObject(Category);
                        list.add(category);
                        // داده‌ها را نمایش دهید
                        // به عنوان مثال:
                      //  Toast.makeText(MainActivity.this, imagePath + " " + categoryName, Toast.LENGTH_SHORT).show();
                    }
                    binding.categoryView.setAdapter(new CategoryAdapter(list));
                } else {
                    // خطا در بازیابی داده‌ها
                    e.printStackTrace();
                }
            }
        });


        binding.progressBarCategory.setVisibility(View.GONE);
    }


}