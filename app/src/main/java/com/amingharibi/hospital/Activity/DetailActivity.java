
package com.amingharibi.hospital.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.amingharibi.hospital.databinding.ActivityDetailBinding;
import com.bumptech.glide.Glide;
import com.parse.ParseFile;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;
    private String title;
    private int categoryId;
    private String text;
    private ParseFile imageFile;
    String articleTitle;
    String articleContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        binding.backButtonDetail.setOnClickListener(view1 -> finish());



        binding.shareBtn.setOnClickListener(v -> shareArticle(articleTitle, articleContent));

        getIntentExtra();
        setVariable();
        articleTitle = binding.titleDetail.getText().toString();
        articleContent = binding.descTxt.getText().toString();

    }

    private void shareArticle(String title, String content) {
        String shareText = title + "\n\n" + content;

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);


        startActivity(Intent.createChooser(shareIntent, "Share via"));
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