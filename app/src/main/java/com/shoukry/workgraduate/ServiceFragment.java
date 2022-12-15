package com.shoukry.workgraduate;

import static com.android.volley.Request.Method.GET;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.shoukry.workgraduate.databinding.FragmentOrdersBinding;
import com.shoukry.workgraduate.databinding.FragmentServiceBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ServiceFragment extends Fragment {

    SharedPreferences shared;
    RequestQueue requestQueue;
    String nameOfWork;
    String id;

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
        requestQueue = Volley.newRequestQueue(getContext());
        FragmentServiceBinding binding = FragmentServiceBinding.inflate(inflater,container,false);

        shared = PreferenceManager.getDefaultSharedPreferences(getContext());

        int loginProvider = shared.getInt("provider",0);
        int loginCustomer = shared.getInt("customer",0);

        if (loginCustomer == 1){
            binding.recyclerViewOrder.setVisibility(View.INVISIBLE);
            getAllWorks();
            Toast.makeText(getContext(), ""+nameOfWork+id, Toast.LENGTH_SHORT).show();
        }else if (loginProvider == 1){
            binding.recyclerViewService.setVisibility(View.VISIBLE);
        }

        return binding.getRoot();
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
