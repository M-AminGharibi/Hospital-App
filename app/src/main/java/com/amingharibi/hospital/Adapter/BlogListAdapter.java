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

import java.util.List;


public class BlogListAdapter extends RecyclerView.Adapter<BlogListAdapter.ViewHolder> {

    private List<Blog> items;
    private Context context;

    public BlogListAdapter(List<Blog> items) {
        this.items = items;
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
        Blog blog = items.get(position);
        holder.binding.descTxt.setText(blog.getDescTxt());
        holder.binding.titleBlog.setText(blog.getTitle());
        ParseFile imagePath = blog.getImageFileBlog();
        if (imagePath != null) {
            Glide.with(context)
                    .load(imagePath.getUrl())
                    .into(holder.binding.imgBlog);
        } else {
            holder.binding.imgBlog.setImageResource(R.drawable.bicycle); // جایگزین R.drawable.placeholder با تصویری پیش‌فرض
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imagePath != null) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    int adapterPosition = holder.getAdapterPosition();
                   // intent.putExtra("CategoryId", items.get(adapterPosition).getCategoryId());
                    intent.putExtra("Title", items.get(adapterPosition).getTitle());
                    intent.putExtra("Text", items.get(adapterPosition).getDescTxt());
                    intent.putExtra("ImageFile", items.get(adapterPosition).getImageFileBlog());
                    context.startActivity(intent);
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderBlogBinding binding;

        public ViewHolder(ViewholderBlogBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
