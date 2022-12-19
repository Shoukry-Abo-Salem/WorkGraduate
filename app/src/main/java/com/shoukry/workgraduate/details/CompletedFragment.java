package com.shoukry.workgraduate.details;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shoukry.workgraduate.R;
import com.shoukry.workgraduate.databinding.FragmentCompletedBinding;

public class CompletedFragment extends Fragment {

    FragmentCompletedBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCompletedBinding.inflate(inflater);

        return binding.getRoot();
    }

    void completed(){

    }
}