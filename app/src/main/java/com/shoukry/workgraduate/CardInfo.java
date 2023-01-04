package com.shoukry.workgraduate;

import static com.android.volley.Request.Method.POST;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.shoukry.workgraduate.AdapterFragments.LoginFragmentAdapter;
import com.shoukry.workgraduate.databinding.ActivityCardInfoBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CardInfo extends AppCompatActivity {

    ActivityCardInfoBinding binding;
    LoginFragmentAdapter adapter;
    JsonObjectRequest jsonObjectRequest;
    RequestQueue requestQueue;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCardInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        requestQueue = Volley.newRequestQueue(CardInfo.this);
        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(new MapsFragment());

        adapter = new LoginFragmentAdapter(this,fragmentArrayList);
        binding.viewPagerLocation.setAdapter(adapter);

        binding.btnBackToChoiceCard.setOnClickListener(View ->{
            startActivity(new Intent(getApplicationContext(),ChoiceCard.class));
            finish();
        });

        binding.btnAddOrder.setOnClickListener(View ->{
            createOrder();
//            startActivity(new Intent(getApplicationContext(), Service.class));
//            finish();
        });
    }

    void createOrder(){
        JSONObject jsonObject = new JSONObject();
        String details = intent.getStringExtra("details");
        try {
            jsonObject.put("details",details);
            jsonObject.put("lat",150);
            jsonObject.put("long",150);
            jsonObject.put("details_address",binding.editeTxtDetailsLocation.getText().toString());

            jsonObjectRequest = new JsonObjectRequest(POST, "https://studentucas.awamr.com/api/auth/login/delivery", jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        if (response.getBoolean("success") == true){
                            Toast.makeText(getBaseContext(), ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Service.class));
                            finish();
                        }else{
                            Toast.makeText(getBaseContext(), ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Error Response", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
        requestQueue.add(jsonObjectRequest);
    }
}