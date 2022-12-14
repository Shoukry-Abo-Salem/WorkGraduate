package com.shoukry.workgraduate;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shoukry.workgraduate.databinding.FragmentOrdersBinding;


public class OrdersFragment extends Fragment {

    public OrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentOrdersBinding binding = FragmentOrdersBinding.inflate(inflater,container,false);
//        binding.ordersViewPager.
        return binding.getRoot();
    }
}