package com.amingharibi.hospital.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.amingharibi.hospital.Adapter.BlogListAdapter;
import com.amingharibi.hospital.Adapter.DoctorAdapter;
import com.amingharibi.hospital.Domain.Blog;
import com.amingharibi.hospital.Domain.Doctor;
import com.amingharibi.hospital.Domain.DoctorDataHolder;
import com.amingharibi.hospital.databinding.ActivityBlogBinding;
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
    private int categoryId;
    List<Doctor> doctorList;
    List<Doctor> doctorListCat;
    private String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityBlogBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.progressBar2.setVisibility(View.VISIBLE);
        blogList = new ArrayList<>();
        doctorListCat = new ArrayList<>();


        doctorList = DoctorDataHolder.getInstance().getFullList();

        getIntentExtra();
        initBlogList();
        initDoctorList();


    }

    private void initDoctorList() {
        for (Doctor doctor : doctorList) {
            int categoryIdDoctor = doctor.getCategoryId();
            if (categoryId == categoryIdDoctor) {
                doctorListCat.add(doctor);
            }
        }
        if (doctorListCat.isEmpty()) {
            Doctor doctor1 = new Doctor();
            doctor1.setDoctorName("پزشکی وحود ندارد");
            doctorListCat.add(doctor1);
        }

        binding.doctorViewBlog.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.doctorViewBlog.setAdapter(new DoctorAdapter(doctorListCat));
        binding.progressBar2.setVisibility(View.GONE);


    }

    private void getIntentExtra() {
        categoryId = getIntent().getIntExtra("CategoryId", 0);
        // categoryName = getIntent().getStringExtra("CategoryName");
        binding.backButtonBlog.setOnClickListener(view -> finish());

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
                    for (ParseObject parseBlog : objects) {
                        int categoryIdParse = parseBlog.getInt("CategoryId");
                        if (categoryId == categoryIdParse) {
                            ParseFile imageFile = parseBlog.getParseFile("ImagePath");
                            String title = parseBlog.getString("Title");
                            String text = parseBlog.getString("Text");
                            Blog blog = com.amingharibi.hospital.Domain.Blog.fromParseObject(parseBlog);
                            blogList.add(blog);
                        } else {
                            Blog blog = new Blog();
                            String title = "مقاله ای وجود ندارد";
                            blog.setTitle(title);
                            blogList.add(blog);
                        }

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