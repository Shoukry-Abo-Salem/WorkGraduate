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
import com.shoukry.workgraduate.AdapterFragments.ProviderAdapter;
import com.shoukry.workgraduate.AdapterFragments.WorkAdapter;
import com.shoukry.workgraduate.Models.ProviderModel;
import com.shoukry.workgraduate.Models.WorkModel;
import com.shoukry.workgraduate.databinding.FragmentOrdersBinding;
import com.shoukry.workgraduate.databinding.FragmentServiceBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ServiceFragment extends Fragment {

    SharedPreferences shared;
    RequestQueue requestQueue;
    String nameOfWork;
    int id;
    FragmentServiceBinding binding;
    ArrayList<WorkModel> arrayList1;
    ArrayList<ProviderModel> arrayList2;
    WorkAdapter workAdapter;
    ProviderAdapter providerAdapter;

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
        binding = FragmentServiceBinding.inflate(inflater,container,false);

        shared = PreferenceManager.getDefaultSharedPreferences(getContext());

        int loginProvider = shared.getInt("provider",0);
        int loginCustomer = shared.getInt("customer",0);

        if (loginCustomer == 1){
            binding.recyclerViewOrder.setVisibility(View.INVISIBLE);
            getAllWorks();
        }else if (loginProvider == 1){
            binding.recyclerViewService.setVisibility(View.VISIBLE);
            getAllDeliver();
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
                        id = jsonObject1.getInt("id");
                        arrayList1 = new ArrayList<>();
                        WorkModel workModel = new WorkModel(id,nameOfWork,R.drawable.work_1);
                        arrayList1.add(workModel);
                        workAdapter = new WorkAdapter(getActivity(),arrayList1);
                        binding.recyclerViewService.setAdapter(workAdapter);

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

    //  Get All Deliver
    void getAllDeliver(){
        StringRequest stringRequest = new StringRequest(GET, "https://studentucas.awamr.com/api/home/deliver", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    JSONObject jsonObject1;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObject1=jsonArray.getJSONObject(i);
                        int id = jsonObject1.getInt("id");
                        int userId = jsonObject1.getInt("user_id");
                        int work_Id = jsonObject1.getInt("work_id");
                        String details = jsonObject1.getString("details_address");
                        String lat = jsonObject1.getString("lat");
                        String _long = jsonObject1.getString("long");
                        arrayList2 = new ArrayList<>();
                        ProviderModel providerModel = new ProviderModel(id,userId,details,lat,_long,work_Id);
                        arrayList2.add(providerModel);
                        providerAdapter = new ProviderAdapter(getActivity(),arrayList2);
                        binding.recyclerViewOrder.setAdapter(workAdapter);

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
