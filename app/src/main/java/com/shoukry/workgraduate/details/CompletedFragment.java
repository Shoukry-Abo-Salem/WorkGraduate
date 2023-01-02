package com.shoukry.workgraduate.details;

import static com.android.volley.Request.Method.GET;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.shoukry.workgraduate.AdapterFragments.DetailsAdapter;
import com.shoukry.workgraduate.Models.DetailsModel;
import com.shoukry.workgraduate.R;
import com.shoukry.workgraduate.databinding.FragmentCompletedBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CompletedFragment extends Fragment {

    FragmentCompletedBinding binding;
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
        binding = FragmentCompletedBinding.inflate(inflater);
        completed();
        return binding.getRoot();
    }

    void completed(){
        StringRequest stringRequest = new StringRequest(GET, "https://studentucas.awamr.com/api/order/complete/user", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject;
                try {
                    jsonObject = new JSONObject(response);
                    Toast.makeText(getActivity(), ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    JSONObject jsonObject1;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObject1 = jsonArray.getJSONObject(i);
                        int user_id = jsonObject1.getInt("user_id");
                        JSONObject jsonObject2 = jsonObject1.getJSONObject("work");
                        String service = jsonObject2.getString("name");
                        DetailsModel model = new DetailsModel(user_id,service);
                        arrayList = new ArrayList<>();
                        arrayList.add(model);
                        DetailsAdapter adapter = new DetailsAdapter(getActivity(),arrayList);
                        binding.recyclerViewCompleted.setAdapter(adapter);
                        binding.recyclerViewCompleted.setLayoutManager(new LinearLayoutManager(getActivity()));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("Authorization","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI5Nzc2N2JmYS1mZWVhLTQ0MTEtOWE0My0wYTliNzE4Y2YwZmEiLCJqdGkiOiI1MGEwMTJlZWM3NTMyODJlZjE1NDc5NDkyNGM2Y2E0YTNlYTdiZGY2MDBlY2RmMzQ0NDBhZmE1ODYxMTQ1OTQwY2U3MDExNTAzZjQ5ZDBkMyIsImlhdCI6MTY3MjI5NTY5OSwibmJmIjoxNjcyMjk1Njk5LCJleHAiOjE3MDM4MzE2OTksInN1YiI6IjE0Iiwic2NvcGVzIjpbXX0.doDJRm4YIzAB4YrIRNj25dXG3sHSA-7RBxyipjxynSpHZzOyxX5jrQqctjP44r2B6giyYj6Js05Sj5LGcQy2eW_xjHRkiH_rJjU-WFQQM9xIq2XBKFo3gIPsRB0EeB3h_PsVnFZZgBp_yWkSOJotE1r0bkJtAKXSH2rtuf_ajs1PxJE_fgENVTrd6RP4C1FaPNEH0wLMHfk0XuLkZm5g2XZyzwXxSyhaCaW1u7qvkRMOMWb-WKr-x2j0G5NsEHrW6Ipb3XXW-Y-wKwlE-fNR48_57UgXKSXdKLSJos4zLiUAStbw9za2G-jSi8_cTGf4pvnV-fGkw2t-2lTgEjbZ-htLDXauIU-w5n-mLbvODa5aXqM0TzfkhxfCKqk2Q_AToZjiLVD6Vut9oK9NTBQc0-ts_NDOYp7Lp0xYe3Jz472mZgIzIETMtiqNC9_lr6zpptcH24DuhY-R06lrCTKm9xAulNxCEsD8V9NRWHUM1_GGnM3oft_Ovy74Ui09SLT0K-wNm6Am7UhXF4rgFAOO9_K9A31qowL40cp0y9FgamwNn3vgEF8lsBSBVNdM351tJnRDB3iCNjfUYjrywe5g9mGfAtOL-ERd7R0VLW_Z2NYyXrPkWA4sMLJ3UlzY3_-VzZVpzIS79kXOhZ49pxBR6rq4pQXWg-TC_p9YBtf7IzU");
                return map;
            }
        };
        queue.add(stringRequest);
    }
}