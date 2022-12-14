package com.shoukry.workgraduate;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shoukry.workgraduate.databinding.FragmentOrdersBinding;
import com.shoukry.workgraduate.databinding.FragmentServiceBinding;


public class ServiceFragment extends Fragment {

    SharedPreferences shared;
    // TODO: Rename parameter arguments, choose names that match


    public ServiceFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentServiceBinding binding = FragmentServiceBinding.inflate(inflater,container,false);

        shared = PreferenceManager.getDefaultSharedPreferences(getContext());

        int loginProvider = shared.getInt("provider",0);
        int loginCustomer = shared.getInt("customer",0);

        if (loginCustomer == 1){
            binding.recyclerViewOrder.setVisibility(View.INVISIBLE);
        }else if (loginProvider == 1){
            binding.recyclerViewService.setVisibility(View.VISIBLE);
        }

        return binding.getRoot();
    }
}