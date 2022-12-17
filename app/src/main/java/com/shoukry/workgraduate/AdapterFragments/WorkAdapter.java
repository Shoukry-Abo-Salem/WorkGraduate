package com.shoukry.workgraduate.AdapterFragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shoukry.workgraduate.Models.WorkModel;
import com.shoukry.workgraduate.databinding.ServiceItemBinding;

import java.util.ArrayList;

public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.WorkViewHolder>{


    Context context;
    ArrayList<WorkModel> arrayList;

    public WorkAdapter(Context context, ArrayList<WorkModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public WorkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WorkViewHolder(ServiceItemBinding.inflate(LayoutInflater.from(context),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull WorkViewHolder holder, int position) {
        WorkModel workModel = arrayList.get(position);

        holder.binding.imageWork1.setImageResource(workModel.getPhoto());
        holder.binding.txtNameWork.setText(workModel.getName());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class WorkViewHolder extends RecyclerView.ViewHolder{
        ServiceItemBinding binding;
        public WorkViewHolder(ServiceItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
