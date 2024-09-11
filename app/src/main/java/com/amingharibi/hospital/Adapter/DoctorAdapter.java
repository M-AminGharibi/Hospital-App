package com.amingharibi.hospital.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amingharibi.hospital.Domain.Doctor;
import com.amingharibi.hospital.R;
import com.amingharibi.hospital.databinding.ViewholderDoctorBinding;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.parse.ParseFile;

import java.util.ArrayList;
import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {
    private List<Doctor> items;
    private List<Doctor> filteredItems;
    private Context context;

    public DoctorAdapter(List<Doctor> items) {
        this.items = items;
        this.filteredItems = new ArrayList<>(items);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewholderDoctorBinding binding = ViewholderDoctorBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorAdapter.ViewHolder holder, int position) {
        Doctor doctor = filteredItems.get(position);
        holder.binding.doctorName.setText(doctor.getDoctorName());
        holder.binding.docterCategory.setText(doctor.getCategoryName());
        holder.binding.doctorTime.setText(doctor.getDoctorTime());
        ParseFile imagePath = doctor.getImageFileDoc();
        if (imagePath != null) {
            Glide.with(context)
                    .load(imagePath.getUrl())
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(16)))
                    .into(holder.binding.imgDoc);
        } else {
            holder.binding.imgDoc.setImageResource(R.drawable.logo); // جایگزین R.drawable.placeholder با تصویری پیش‌فرض
        }
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
            for (Doctor doctor : items) {
                if (doctor.getCategoryName().toLowerCase().contains(query.toLowerCase()) ||
                        doctor.getDoctorName().toLowerCase().contains(query.toLowerCase())) {
                    filteredItems.add(doctor);
                }
            }
        }
        notifyDataSetChanged(); // برای به‌روزرسانی RecyclerView
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderDoctorBinding binding;

        public ViewHolder(@NonNull ViewholderDoctorBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
