package com.amingharibi.hospital.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.amingharibi.hospital.Adapter.BlogListAdapter;
import com.amingharibi.hospital.Adapter.CategoryAdapter;
import com.amingharibi.hospital.Domain.Blog;
import com.amingharibi.hospital.Domain.Category;
import com.amingharibi.hospital.R;
import com.amingharibi.hospital.databinding.ActivityBlogBinding;
import com.amingharibi.hospital.databinding.ActivityMainBinding;
import com.amingharibi.hospital.databinding.ActivitySectionBinding;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class BlogActivity extends AppCompatActivity {
    ActivityBlogBinding binding;
    List<Blog> blogList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityBlogBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        blogList = new ArrayList<>();
        initBlogList();
    }

    private void initBlogList() {
        binding.progressBarBlog.setVisibility(View.VISIBLE);
        binding.blogView.setLayoutManager(new LinearLayoutManager(BlogActivity.this));


        ParseQuery<ParseObject> query = ParseQuery.getQuery("Blog");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    // داده‌ها با موفقیت بازیابی شدند
                    for (ParseObject parseCategory : objects) {
                        ParseFile imageFile = parseCategory.getParseFile("ImagePath");
                        String title = parseCategory.getString("Title");
                        String text = parseCategory.getString("Text");
                        Blog blog = com.amingharibi.hospital.Domain.Blog.fromParseObject(parseCategory);
                        blogList.add(blog);
                    }
                    binding.blogView.setAdapter(new BlogListAdapter(blogList));
                    binding.progressBarBlog.setVisibility(View.GONE);
                } else {
                    // خطا در بازیابی داده‌ها
                    e.printStackTrace();
                }
            }
        });


    }



}