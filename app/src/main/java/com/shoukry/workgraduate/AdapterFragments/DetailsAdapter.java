package com.shoukry.workgraduate.AdapterFragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shoukry.workgraduate.Models.DetailsModel;
import com.shoukry.workgraduate.databinding.OrderTowItemBinding;

import java.util.ArrayList;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder>{

    Context context;
    ArrayList<DetailsModel> arrayList;

    public DetailsAdapter(Context context, ArrayList<DetailsModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DetailsViewHolder(OrderTowItemBinding.inflate(LayoutInflater.from(context),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsViewHolder holder, int position) {
        DetailsModel detailsModel = arrayList.get(position);
        holder.binding.txtServiceType.setText(detailsModel.getServiceType());
        holder.binding.txtOrderNumber.setText(String.valueOf(detailsModel.getOrderNumber()));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class DetailsViewHolder extends RecyclerView.ViewHolder {
        OrderTowItemBinding binding;
        public DetailsViewHolder(OrderTowItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
