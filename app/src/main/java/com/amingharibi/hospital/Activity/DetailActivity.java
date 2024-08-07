
package com.amingharibi.hospital.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.amingharibi.hospital.R;
import com.amingharibi.hospital.databinding.ActivityBlogBinding;
import com.amingharibi.hospital.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;
private String title;
    private int categoryId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.backButtonDetail.setOnClickListener(view1 -> finish());


        getIntentExtra();


    }

    private void getIntentExtra() {
       // categoryId = getIntent().getIntExtra("CategoryId",0);

        title = getIntent().getStringExtra("Title");

        binding.titleDetail.setText(title);


    }
}