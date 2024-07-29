package com.amingharibi.hospital.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amingharibi.hospital.Domain.Category;
import com.amingharibi.hospital.R;
import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewholder> {
    private ArrayList<Category> items;
    Context context;

    public CategoryAdapter(ArrayList<Category> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CategoryAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category, parent, false);
        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.viewholder holder, int position) {
//        holder.selectionName.setText(items.get(position).getCategoryName());
//        int drawableResourceId = context.getResources().getIdentifier(String.valueOf(items.get(position).getImagePath()), "drawable", holder.itemView.getContext().getPackageName());
//        Glide.with(context).load(drawableResourceId).into(holder.pic);
        Category category = items.get(position);
        holder.selectionName.setText(category.getCategoryName());

        ParseFile imagePath = category.getImagePath();
        if (imagePath != null) {
            Glide.with(context)
                    .load(imagePath.getUrl())
                    .into(holder.imgCat);
        } else {
            holder.imgCat.setImageResource(R.drawable.logo); // جایگزین R.drawable.placeholder با تصویری پیش‌فرض
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView selectionName;
        ImageView imgCat;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            selectionName = itemView.findViewById(R.id.selectionName);
            imgCat = itemView.findViewById(R.id.imgCat);
        }
    }
}
