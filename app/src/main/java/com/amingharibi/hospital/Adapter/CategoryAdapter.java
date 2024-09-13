package com.amingharibi.hospital.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amingharibi.hospital.Activity.BlogActivity;
import com.amingharibi.hospital.Domain.Category;
import com.amingharibi.hospital.R;
import com.amingharibi.hospital.databinding.ViewholderCategoryBinding;
import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private List<Category> items;
    private List<Category> filteredItems;
    private Context context;

    public CategoryAdapter(List<Category> items) {
        this.items = items;
        this.filteredItems = new ArrayList<>(items);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewholderCategoryBinding binding = ViewholderCategoryBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // holder.selectionName.setText(items.get(position).getCategoryName());
        // int drawableResourceId = context.getResources().getIdentifier(String.valueOf(items.get(position).getImagePath()), "drawable", holder.itemView.getContext().getPackageName());
        // Glide.with(context).load(drawableResourceId).into(holder.pic);
        Category category = filteredItems.get(position);
        holder.binding.selectionName.setText(category.getCategoryName());
        ParseFile imagePath = category.getImageFileCat();
        if (imagePath != null) {
            Glide.with(context)
                    .load(imagePath.getUrl())
                    .into(holder.binding.imgCat);
        } else {
            holder.binding.imgCat.setImageResource(R.drawable.logo); // جایگزین R.drawable.placeholder با تصویری پیش‌فرض
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BlogActivity.class);
                int adapterPosition = holder.getAdapterPosition();
                intent.putExtra("CategoryId", filteredItems.get(adapterPosition).getCategoryId());
                //intent.putExtra("CategoryName", items.get(position).getCategoryName());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return filteredItems.size();
    }


    public void filter(String query) {
        filteredItems.clear();
        if (query.isEmpty()) {
            filteredItems.addAll(items); // اگر جستجو خالی باشد، همه آیتم‌ها نمایش داده می‌شود
        } else {
            for (Category category : items) {
                if (category.getCategoryName().toLowerCase().contains(query.toLowerCase())) {
                    filteredItems.add(category);
                }
            }
        }
        notifyDataSetChanged(); // برای به‌روزرسانی RecyclerView
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderCategoryBinding binding;

        public ViewHolder(ViewholderCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
