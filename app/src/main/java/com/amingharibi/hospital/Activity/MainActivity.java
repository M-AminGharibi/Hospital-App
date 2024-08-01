package com.amingharibi.hospital.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.amingharibi.hospital.Adapter.CategoryAdapter;
import com.amingharibi.hospital.Domain.Category;
import com.amingharibi.hospital.Domain.DataHolder;
import com.amingharibi.hospital.databinding.ActivityMainBinding;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    List<Category> fullList;
    List<Category> limitedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        fullList = new ArrayList<>();


        initCategoryMain();

        binding.seeAllTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SectionActivity.class);
                //intent.putExtra("fullList", (Serializable) fullList);
                //intent.putParcelableArrayListExtra("fullList", (ArrayList<? extends Parcelable>) fullList);
                //intent.putStringArrayListExtra("fullList", fullList);
                DataHolder.getInstance().setFullList(fullList);

                startActivity(intent);
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
                        ParseFile imagePath = parseCategory.getParseFile("ImagePath");
                        String categoryName = parseCategory.getString("CategoryName");
                        Category category = com.amingharibi.hospital.Domain.Category.fromParseObject(parseCategory);
                        fullList.add(category);
                    }
                    limitedList = getLimitedList(fullList, 4);
                    binding.categoryView.setAdapter(new CategoryAdapter(limitedList));
                } else {
                    // خطا در بازیابی داده‌ها
                    e.printStackTrace();
                }
            }
        });

        binding.progressBarCategory.setVisibility(View.GONE);
    }



    private List<Category> getLimitedList(List<Category> originalList, int limit) {
        if (originalList.size() <= limit) {
            return new ArrayList<>(originalList);
        } else {
            return new ArrayList<>(originalList.subList(0, limit));
        }
    }

}