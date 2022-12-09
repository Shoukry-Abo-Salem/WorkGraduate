package com.shoukry.workgraduate;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.shoukry.workgraduate.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {


//    Button login;
    private Login login ;
    FragmentLoginBinding binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Login
//    login.findViewById(R.id.btn_Login);
//    login.setOnClickListener(view ->{
//        startActivity(new Intent(LoginFragment.this,Register.class));
//    });



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    binding = FragmentLoginBinding.inflate(getLayoutInflater());
    return binding.getRoot();

        //return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        binding.signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(g,Register.class));
//                Toast.makeText(login, "Done", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}