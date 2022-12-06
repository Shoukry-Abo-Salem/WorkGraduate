package com.shoukry.workgraduate;

import static com.android.volley.Request.Method.POST;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.shoukry.workgraduate.AdapterFragments.LoginFragmentAdapter;
import com.shoukry.workgraduate.databinding.ActivityRegisterBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Register extends AppCompatActivity {

    ActivityRegisterBinding binding;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    Button login;
    TextView signIn;
    EditText fullName,email,phoneNumber,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        requestQueue = Volley.newRequestQueue(Register.this);

        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(new RegisterFragment());
        fragmentArrayList.add(new RegisterFragment());

        LoginFragmentAdapter adapter = new LoginFragmentAdapter(this,fragmentArrayList);
        binding.viewPagerRegister.setAdapter(adapter);

        binding.imageViewBack.setOnClickListener(view ->{
            startActivity(new Intent(getApplicationContext(), Login.class));
            finish();
        });

        binding.registerNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.provider){
                    binding.viewPagerRegister.setCurrentItem(0);
                }else if (itemId == R.id.customer){
                    binding.viewPagerRegister.setCurrentItem(1);
                }

                login.setOnClickListener(view ->{
                    if (itemId == R.id.provider) {
                        Log.d("customer", "customer");
                        requestQueue = Volley.newRequestQueue(Register.this);
                        setRegisterProvider();
                    }else if (itemId == R.id.customer){
                        Log.d("provider", "provider");
                        requestQueue = Volley.newRequestQueue(Register.this);
//                        setRegisterCustomer();
                    }
                });
                return false;
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
            jsonObject.put("name",fullName);
            jsonObject.put("email",email);
            jsonObject.put("phone",phoneNumber);
            jsonObject.put("work_id",1);
            jsonObject.put("password",password);

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
}