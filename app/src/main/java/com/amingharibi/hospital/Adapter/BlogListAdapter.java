package com.amingharibi.hospital.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amingharibi.hospital.Activity.BlogActivity;
import com.amingharibi.hospital.Activity.DetailActivity;
import com.amingharibi.hospital.Domain.Blog;
import com.amingharibi.hospital.Domain.Category;
import com.amingharibi.hospital.R;
import com.amingharibi.hospital.databinding.ViewholderBlogBinding;
import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.ArrayList;
import java.util.List;


public class BlogListAdapter extends RecyclerView.Adapter<BlogListAdapter.ViewHolder> {

    private List<Blog> items;
    private List<Blog> filteredItems; // لیست فیلتر شده
    private Context context;

    public BlogListAdapter(List<Blog> items) {
        this.items = items;
        this.filteredItems = new ArrayList<>(items); // در ابتدا لیست فیلتر شده با همه آیتم‌ها برابر است
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewholderBlogBinding binding = ViewholderBlogBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogListAdapter.ViewHolder holder, int position) {
        Blog blog = filteredItems.get(position); // استفاده از لیست فیلتر شده
        holder.binding.descTxt.setText(blog.getDescTxt());
        holder.binding.titleBlog.setText(blog.getTitle());
        ParseFile imagePath = blog.getImageFileBlog();
        if (imagePath != null) {
            Glide.with(context)
                    .load(imagePath.getUrl())
                    .into(holder.binding.imgBlog);
        } else {
            holder.binding.imgBlog.setImageResource(R.drawable.bicycle); // جایگزین R.drawable.bicycle با تصویری پیش‌فرض
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imagePath != null) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    int adapterPosition = holder.getAdapterPosition();
                    intent.putExtra("Title", filteredItems.get(adapterPosition).getTitle());
                    intent.putExtra("Text", filteredItems.get(adapterPosition).getDescTxt());
                    intent.putExtra("ImageFile", filteredItems.get(adapterPosition).getImageFileBlog());
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredItems.size(); // تعداد آیتم‌ها از لیست فیلتر شده
    }

    // متدی برای فیلتر کردن داده‌ها بر اساس ورودی کاربر
    public void filter(String query) {
        filteredItems.clear();
        if (query.isEmpty()) {
            filteredItems.addAll(items); // اگر جستجو خالی باشد، همه آیتم‌ها نمایش داده می‌شود
        } else {
            for (Blog blog : items) {
                if (blog.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                        blog.getDescTxt().toLowerCase().contains(query.toLowerCase())) {
                    filteredItems.add(blog);
                }
            }
        }
        notifyDataSetChanged(); // برای به‌روزرسانی RecyclerView
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderBlogBinding binding;

        public ViewHolder(ViewholderBlogBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
