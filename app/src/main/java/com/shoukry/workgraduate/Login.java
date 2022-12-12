package com.shoukry.workgraduate;

import static com.android.volley.Request.Method.POST;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.shoukry.workgraduate.AdapterFragments.LoginFragmentAdapter;
import com.shoukry.workgraduate.databinding.ActivityLoginBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    ActivityLoginBinding binding;
    LoginFragmentAdapter adapter;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    EditText email,password;
    Button login,signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        requestQueue = Volley.newRequestQueue(Login.this);


        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(new LoginFragment());
        fragmentArrayList.add(new LoginFragment());

        adapter = new LoginFragmentAdapter(this,fragmentArrayList);
        binding.viewPager.setAdapter(adapter);

//        signup = findViewById(R.id.signup);
//        signup.setOnClickListener(view ->{
//            startActivity(new Intent(Login.this, Register.class));
//        });




        binding.loginNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int  itemId = item.getItemId();
                login = findViewById(R.id.btn_Login);

                if (itemId == R.id.provider){
                    binding.viewPager.setCurrentItem(0);
                }else if (itemId == R.id.customer){
                    binding.viewPager.setCurrentItem(1);
                }

                login.setOnClickListener(view ->{
                    if (itemId == R.id.provider) {
                        Log.d("customer", "customer");
                        requestQueue = Volley.newRequestQueue(Login.this);
                        setLoginCustomer();
                    }else if (itemId == R.id.customer){
                        Log.d("provider", "provider");
                        requestQueue = Volley.newRequestQueue(Login.this);
                        setLoginProvider();
                    }
                });

                signup = findViewById(R.id.signup);
                signup.setOnClickListener(view ->{
                    if (itemId == R.id.provider){
                        startActivity(new Intent(getApplicationContext(),Register.class));
                    }else if (itemId == R.id.customer){
                        startActivity(new Intent(getApplicationContext(),Register.class));
//                        binding.viewPager.setCurrentItem(1);
                    }
                });


                return false;
            }
        });


        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                binding.loginNav.getMenu().getItem(position).setChecked(true);
            }
        });

    }
    //    Login Delivery
    void setLoginProvider(){
        JSONObject jsonObject = new JSONObject();
        email = findViewById(R.id.edText_Email);
        password = findViewById(R.id.edText_Password);
        try {
            jsonObject.put("email",email.getText().toString());
            jsonObject.put("password",password.getText().toString());

            jsonObjectRequest = new JsonObjectRequest(POST, "https://studentucas.awamr.com/api/auth/login/delivery", jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        if (response.getBoolean("success") == true){


                            Toast.makeText(Login.this, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login.this,MainActivity.class));
                            finish();
                        }else{
                        Toast.makeText(Login.this, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("sad3","Error");
                    Toast.makeText(Login.this, "Error in Email or Password", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
        requestQueue.add(jsonObjectRequest);
    }

    //      Login Customer
    void setLoginCustomer(){
        JSONObject jsonObject = new JSONObject();
        email = findViewById(R.id.edText_Email);
        password = findViewById(R.id.edText_Password);
        try {
            jsonObject.put("email",email.getText().toString());
            jsonObject.put("password",password.getText().toString());

            jsonObjectRequest = new JsonObjectRequest(POST, "https://studentucas.awamr.com/api/auth/login/user", jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        if (response.getBoolean("success") == true){
                            Toast.makeText(Login.this, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login.this,MainActivity.class));
                            finish();
                        }else{
                            Toast.makeText(Login.this, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("sad3","Error");
                    Toast.makeText(Login.this, "Error in Email or Password", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
        requestQueue.add(jsonObjectRequest);
    }
}