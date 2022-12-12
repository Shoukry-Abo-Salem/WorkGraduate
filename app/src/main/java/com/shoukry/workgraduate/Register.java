package com.shoukry.workgraduate;

import static com.android.volley.Request.Method.GET;
import static com.android.volley.Request.Method.POST;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.shoukry.workgraduate.AdapterFragments.LoginFragmentAdapter;
import com.shoukry.workgraduate.databinding.ActivityRegisterBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Register extends AppCompatActivity {

    ActivityRegisterBinding binding;
    LoginFragmentAdapter adapter;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    String nameOfWork;
    SharedPreferences shared;
    SharedPreferences.Editor editor;
    public static int USER_REGISTER_KEY = 0;
    public static int PROVIDER_REGISTER_KEY = 0;
    String id;
    Button signUp;
    TextView signIn;
    Spinner spinner;
    EditText fullName,email,phoneNumber,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        signIn = findViewById(R.id.text_SignIn);
//        signIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Register.this,Login.class));
//            }
//        });

        requestQueue = Volley.newRequestQueue(Register.this);
        ArrayList<String> arrayListSpinner = new ArrayList<>();
//        ArrayAdapter spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListSpinner);
//        spinner.setAdapter(spinnerAdapter);

        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(new RegisterFragment());
        fragmentArrayList.add(new RegisterFragment());

        adapter = new LoginFragmentAdapter(this,fragmentArrayList);
        binding.viewPagerRegister.setAdapter(adapter);

        binding.imageViewBack.setOnClickListener(view ->{
            startActivity(new Intent(getApplicationContext(), Login.class));
            finish();
        });

        binding.registerNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                    spinner = findViewById(R.id.main_spinner);
                if (itemId == R.id.provider){
                    binding.viewPagerRegister.setCurrentItem(0);
                    getAllWorks();
                    spinner.setVisibility(View.VISIBLE);
                    Toast.makeText(Register.this, ""+nameOfWork+id, Toast.LENGTH_SHORT).show();
                }else if (itemId == R.id.customer){
                    binding.viewPagerRegister.setCurrentItem(1);
                    spinner.setVisibility(View.INVISIBLE);

//                    arrayListSpinner.add(nameOfWork);
                }

                signUp = findViewById(R.id.btn_SignUp);
                signUp.setOnClickListener(view ->{
                    if (itemId == R.id.provider) {
                        Log.d("customer", "customer");
                        requestQueue = Volley.newRequestQueue(Register.this);
                        setRegisterProvider();
                    }else if (itemId == R.id.customer){
                        Log.d("provider", "provider");
                        requestQueue = Volley.newRequestQueue(Register.this);
                        setRegisterCustomer();
                    }
                });
                return false;
            }
        });

        binding.viewPagerRegister.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                binding.registerNav.getMenu().getItem(position).setChecked(true);
            }
        });



    }

    //    Register Delivery
    void setRegisterProvider(){
        JSONObject jsonObject = new JSONObject();
        fullName = findViewById(R.id.edText_FullName);
        email = findViewById(R.id.edit_txt_email);
        phoneNumber = findViewById(R.id.edit_txt_PhoneNumber);
        password = findViewById(R.id.edit_txt_password);

        try {
            jsonObject.put("name",fullName.getText().toString());
            jsonObject.put("email",email.getText().toString());
            jsonObject.put("phone",phoneNumber.getText().toString());
            jsonObject.put("work_id",1);
            jsonObject.put("password",password.getText().toString());

            jsonObjectRequest = new JsonObjectRequest(POST, "https://studentucas.awamr.com/api/auth/register/delivery", jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        if (response.getBoolean("success") == true){

                            Toast.makeText(Register.this, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Register.this,MainActivity.class));
                            finish();
                        }else{
                            Toast.makeText(Register.this, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("sad3","Error");
                    Toast.makeText(Register.this, "Error Register", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
        requestQueue.add(jsonObjectRequest);
    }

    //   Register Customer
    void setRegisterCustomer(){
        JSONObject jsonObject = new JSONObject();
        fullName = findViewById(R.id.edText_FullName);
        email = findViewById(R.id.edit_txt_email);
        phoneNumber = findViewById(R.id.edit_txt_PhoneNumber);
        password = findViewById(R.id.edit_txt_password);
        try {
            jsonObject.put("name",fullName.getText().toString());
            jsonObject.put("email",email.getText().toString());
            jsonObject.put("phone",phoneNumber.getText().toString());
            jsonObject.put("password",password.getText().toString());

            jsonObjectRequest = new JsonObjectRequest(POST, "https://studentucas.awamr.com/api/auth/register/user", jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        if (response.getBoolean("success") == true){
                            Toast.makeText(Register.this, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Register.this,MainActivity.class));
                            finish();
                        }else{
                            Toast.makeText(Register.this, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("sad3","Error");
                    Toast.makeText(Register.this, "Error Register", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
        requestQueue.add(jsonObjectRequest);
    }

    //   Get All Works
    void getAllWorks(){
        StringRequest stringRequest = new StringRequest(GET, "https://studentucas.awamr.com/api/all/works", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    JSONObject jsonObject1;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObject1=jsonArray.getJSONObject(i);
                        nameOfWork = jsonObject1.getString("name");
                        id = jsonObject1.getString("id");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }
}