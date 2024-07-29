package com.amingharibi.hospital.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amingharibi.hospital.Domain.Category;
import com.amingharibi.hospital.R;
import com.amingharibi.hospital.databinding.ViewholderCategoryBinding;
import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private List<Category> items;
    private Context context;

    public CategoryAdapter(List<Category> items) {
        this.items = items;
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
        Category category = items.get(position);
        holder.binding.selectionName.setText(category.getCategoryName());

        ParseFile imagePath = category.getImagePath();
        if (imagePath != null) {
            Glide.with(context)
                    .load(imagePath.getUrl())
                    .into(holder.binding.imgCat);
        } else {
            holder.binding.imgCat.setImageResource(R.drawable.logo); // جایگزین R.drawable.placeholder با تصویری پیش‌فرض
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderCategoryBinding binding;

        public ViewHolder(ViewholderCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
