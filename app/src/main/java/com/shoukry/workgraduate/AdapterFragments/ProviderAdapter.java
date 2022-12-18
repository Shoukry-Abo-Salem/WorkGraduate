package com.shoukry.workgraduate.AdapterFragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shoukry.workgraduate.Models.ProviderModel;
import com.shoukry.workgraduate.Models.WorkModel;
import com.shoukry.workgraduate.R;
import com.shoukry.workgraduate.databinding.OrderItemBinding;

import java.util.ArrayList;

public class ProviderAdapter extends RecyclerView.Adapter<ProviderAdapter.ProviderViewHolder>{


    Context context;
    ArrayList<ProviderModel> arrayList;

    public ProviderAdapter(Context context, ArrayList<ProviderModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ProviderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProviderViewHolder(OrderItemBinding.inflate(LayoutInflater.from(context),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProviderViewHolder holder, int position) {

        ProviderModel providerModel = arrayList.get(position);

        holder.binding.txtServiceType.setText(providerModel.getService());
        holder.binding.txtUserName.setText(providerModel.getName());
        holder.binding.txtOrderId.setText(String.valueOf(providerModel.getId()));
        holder.binding.imageUser.setImageResource(providerModel.getImg());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ProviderViewHolder extends RecyclerView.ViewHolder {

        OrderItemBinding binding;
        public ProviderViewHolder(OrderItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
