
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
import com.bumptech.glide.Glide;
import com.parse.ParseFile;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;
    private String title;
    private int categoryId;
    private String text;
    private ParseFile imageFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        binding.backButtonDetail.setOnClickListener(view1 -> finish());

        getIntentExtra();
        setVariable();


    }

    private void setVariable() {
        if (imageFile != null) {
            Glide.with(this)
                    .load(imageFile.getUrl())
                    .into(binding.imgDetail);
        }
        binding.titleDetail.setText(title);
        binding.descTxt.setText(text);
    }

    private void getIntentExtra() {
        // categoryId = getIntent().getIntExtra("CategoryId",0);

        title = getIntent().getStringExtra("Title");
        text = getIntent().getStringExtra("Text");
        imageFile = getIntent().getParcelableExtra("ImageFile");


    }
}