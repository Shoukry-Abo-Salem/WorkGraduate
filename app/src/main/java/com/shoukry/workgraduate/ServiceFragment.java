package com.shoukry.workgraduate;

import static com.android.volley.Request.Method.GET;
import static com.android.volley.Request.Method.POST;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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
import java.util.HashMap;
import java.util.Map;


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
        requestQueue = Volley.newRequestQueue(getActivity());
        binding = FragmentServiceBinding.inflate(inflater);

        shared = PreferenceManager.getDefaultSharedPreferences(getActivity());

        int loginProvider = shared.getInt("provider", 0);
        int loginCustomer = shared.getInt("customer", 0);

        if (loginCustomer == 1) {
            binding.recyclerViewOrder.setVisibility(View.INVISIBLE);
            getAllWorks();
        } else if (loginProvider == 1) {
            binding.recyclerViewService.setVisibility(View.VISIBLE);
            getAllDeliver();
        }

        return binding.getRoot();
    }

    //   Get All Works
    void getAllWorks() {
        StringRequest stringRequest = new StringRequest(GET, "https://studentucas.awamr.com/api/all/works", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    JSONObject jsonObject1;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObject1 = jsonArray.getJSONObject(i);
                        nameOfWork = jsonObject1.getString("name");
                        id = jsonObject1.getInt("id");
                        WorkModel workModel = new WorkModel(id, nameOfWork, R.drawable.work_1);
                        arrayList1 = new ArrayList<>();
                        arrayList1.add(workModel);
                        workAdapter = new WorkAdapter(getActivity(), arrayList1);
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

    //   Get All Deliver
    void getAllDeliver() {
        StringRequest stringRequest = new StringRequest(POST, "https://studentucas.awamr.com/api/home/deliver", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    JSONObject jsonObject1;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObject1 = jsonArray.getJSONObject(i);
                        int userId = jsonObject1.getInt("user_id");
                        int id = jsonObject1.getInt("id");
                        JSONObject jsonObjectWork = jsonObject1.getJSONObject("work");
                        JSONObject jsonObjectUser = jsonObject1.getJSONObject("user");
                        String W_name = jsonObjectWork.getString("name");
                        String U_name = jsonObjectUser.getString("name");
                        arrayList2 = new ArrayList<>();
                        ProviderModel providerModel = new ProviderModel(U_name, W_name, id, R.drawable.smal_user_icon, userId);
                        arrayList2.add(providerModel);
                        providerAdapter = new ProviderAdapter(getActivity(), arrayList2);
                        binding.recyclerViewOrder.setAdapter(providerAdapter);
                        binding.recyclerViewOrder.setLayoutManager(new LinearLayoutManager(getActivity()));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI5Nzc2N2JmYS1mZWVhLTQ0MTEtOWE0My0wYTliNzE4Y2YwZmEiLCJqdGkiOiIyNTU3NTVjZTlkYTg0MzhhYzNkY2M2ZmNlY2QyNDkyMmI0MjQzMjA1ZThhYmNlYTUwM2ExMjIwMWExNWZiN2ViYjg0OTg0MTJlNWVmOGE5MiIsImlhdCI6MTY3MTI2ODkwMSwibmJmIjoxNjcxMjY4OTAxLCJleHAiOjE3MDI4MDQ5MDEsInN1YiI6IjE4Iiwic2NvcGVzIjpbXX0.B1gIWGhJwcrKiDqOwXYCHDA_3Yg8H4cox_56KvwVNEtuYWkWL81hthnjCBd_Iz5AXDeYnh4yt5J-t1bEEaVdkjIT_65MKyw7cNSiEK2mzJdApwsynvIikWtec8WpDtZUgOHXq67pwxzcg6QSxq1L9Efyt227TuyitWFwEIXWSqd1WHynw9mJQdA9WDAsSBVCGA4eneN1u8465xS0Cj3VoP1C1DhUVFc4ny6RHlSXhttrnaeH1zPo43WqaySCgntN3E246BFx4SzFnVAz3FtbHkWG1Bes6OczQba-jJDpSyCqAWwQ7u3ymxiciIsZC5_1uieiGOU1Z71YAdZQVrrNJpgUBXSKrSugm2USkjPK9qKwqM_ViqxVu_D-_dqbrPZyytpNxq93iUaO_AzI_VQ_TrTg80IqDhakJtpu64t1ajFfyXMQzyJNUKxUq3YDJADuJH2MMHDbONOKHynkoTFIBpNXVjDGDXuDgGCUv7Z5Zjai47zYmcymUiwtHCJKsjKVPnybxBWkobNlPEbGOt-MDZZBQtEGjukWUfEvZm1v9BHuRtTdPrVY-qg1ThMHYv47s4EjaFyM4LLXYFq0nLBGro9-R17Vgqm9ZywCqsZ9l6eQbxuVyYLo5Emvo6DKkKtBvx-dfHV9RWjIWAVAvvwMdUExOYUsj2zKvAWDjdQK-Ac");
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
}
