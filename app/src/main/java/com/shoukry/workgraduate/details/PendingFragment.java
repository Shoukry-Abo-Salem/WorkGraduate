package com.shoukry.workgraduate.details;

import static com.android.volley.Request.Method.GET;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.shoukry.workgraduate.AdapterFragments.DetailsAdapter;
import com.shoukry.workgraduate.Models.DetailsModel;
import com.shoukry.workgraduate.R;
import com.shoukry.workgraduate.databinding.FragmentPendingBinding;
import com.shoukry.workgraduate.databinding.OrderTowItemBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class PendingFragment extends Fragment {

    FragmentPendingBinding binding;
    RequestQueue queue;
    ArrayList<DetailsModel> arrayList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPendingBinding.inflate(inflater);
        queue = Volley.newRequestQueue(getActivity());
        arrayList = new ArrayList<>();
        pending();
        return binding.getRoot();
    }

    void pending(){
        StringRequest stringRequest = new StringRequest(GET, "https://studentucas.awamr.com/api/order/pending/user", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject;
                try {
                    jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    JSONObject jsonObject1;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObject1 = jsonArray.getJSONObject(i);
                        int user_id = jsonObject1.getInt("user_id");
                        JSONObject jsonObject2 = jsonObject1.getJSONObject("work");
                        String service = jsonObject2.getString("name");
                        DetailsModel model = new DetailsModel(user_id,service);
                        arrayList.add(model);
                        DetailsAdapter adapter = new DetailsAdapter(getActivity(),arrayList);
                        binding.recyclerViewPending.setAdapter(adapter);
                        binding.recyclerViewPending.setLayoutManager(new LinearLayoutManager(getActivity()));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("Authorization","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI5Nzc2N2JmYS1mZWVhLTQ0MTEtOWE0My0wYTliNzE4Y2YwZmEiLCJqdGkiOiI4ODcwN2ExMDVkZmEyZDk5NWNiZDhjM2IwM2U3ZjJhOWRlNjZlNDMwN2NlNTQ1MWFlNzM5MjMzMTk2Mzc5MDEyZTk4NmU0M2M3MDBiMzFjNiIsImlhdCI6MTY3MTQzNDIzOSwibmJmIjoxNjcxNDM0MjM5LCJleHAiOjE3MDI5NzAyMzksInN1YiI6IjE4Iiwic2NvcGVzIjpbXX0.sLwYTm4wAuQtCyDj_9Jgdem04fxJ5Zd61NeJgKeqz6GKb7HMwS4oGrOiS3BLf77WNCGqqASbVTxIL7q3T0PzY0nMKPPlGlwMOm9fhA4hMvQZcNbWsnjeunVAl4Ber-uXQpPX7eop-zVBaNukjTVDLPLE5Je44W-y8-jBbtHzvbPRFJFGiSqQKqM20Z_o96NebhYvvMM99MECXrnx3-ZyYvjYWzipQAj0DenJoK_yaY8vajt2rLf9HP50epmgg8F-mgTtl7O_R_mWUQJbMgEU5IuxfLqqMIV1sibxIyRhxvxWWC5KZYx17zpT7CmndmuOnfcKC4CLMSDCQQuivrVGZOHFepZDUSKGwHNTtwKwcZMpAJV3WzjGs2c5-gYaerz5hiwcdCIi8pscrwGJ4UvUikMr6X6_GDyzmt8o9cmDqXINPTHijHtXMaIyZgdAznyFf0phKSYAV3XGrNCJthscDlVRb-5T2YbVSt9hMA4SkIpYUWEc5PaoRHdZryEZ84MRJrDutLb_FwyF2JFKM7SFBZi4-ZAy-hD0V6OQcTrc6gj8kZhRY39ZR7xeOi15QC0IbnPhp9-8tzjYJDA0t1t1-E0ecIJtaYii3ff9vfy71xfLTVqXznFaWa1fnnKNxPOrlEFZoJWU_p6_l2vIqJV62iOspfeqBXwbKjvTIsAVNPI");
                return map;
            }
        };
        queue.add(stringRequest);
    }
}