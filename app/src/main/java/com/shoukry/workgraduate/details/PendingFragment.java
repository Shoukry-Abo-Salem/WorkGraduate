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
//        arrayList = new ArrayList<>();
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
                        arrayList = new ArrayList<>();
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

        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("Authorization","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI5Nzc2N2JmYS1mZWVhLTQ0MTEtOWE0My0wYTliNzE4Y2YwZmEiLCJqdGkiOiJkMTAwZDQ5NGE0ZWY0MDI4MTYxMmQxN2EzMGE0NzhiZjY0OTg0YzNlOTYxMWQ2MjJhMzQ5MTNkZDljZDUzMWRhMGExOGNjMDI1YzNiNmZkZiIsImlhdCI6MTY3MDgyOTE5NSwibmJmIjoxNjcwODI5MTk1LCJleHAiOjE3MDIzNjUxOTUsInN1YiI6IjQiLCJzY29wZXMiOltdfQ.iUeujmzW9I5Ho40HKPQJCrGTVUrxwB7o0xozLGS2ClDxw95FYuMn0GxrwN5d630mE14HGkEvxkjip_FDEv2G0j8WVItjsvpqQsE19jbKJvxYImPr40_MI5vGO1Yf8yQZYLU2nGa4Isk0hx5saxsmY9Lf2U4PVGQjDTxuf5IfIN1e1wO1sPt9YLIAST8aXxq3j4vaYE68xw4xmXCithnzRfN5SyMWWj7hgnZ_aK8lqYG9Ato7jaqwlkOObXkkhfi01RQ4VmgNWNyDWEojLQXLNp_xiAKPF03h_OjFcHvN_YUADPZsc_BTpNg_gRiYHxhBr4Af9B_xX2EVwISjWAPt5Xog7KBNZu5hsEn3Qu9PV37l-Ymf6PflhBe2uL3P4Cq2PcZvSpYYCLXQh1F04RAx6CuoaoSMMnIX7hO-niM7NhlYCro5zQhiYKVgFm9a-gFN_F449zKtYRIpBOzAjpZF7aH7fCXcYAZCzLugKxNowiYCyyOMxQJki2-PPGa2VYOXMIGnsf4CUIYYFMtlFOxSVe0Da2dGA6CzKiw9RjcoOOKzFwPU0E8cjxEeLDCWOnXiXJ4ydadixFFe_RA-6cvIO15ftgLY4lg6nUzssEGapX7KA8iRGZo7bZRxrgbmbrzgvsC86ri_Xfsn7Ygx3SY8Gu3-1QH6qL0efoh1NEgfY4o");
                return map;
            }
        };
        queue.add(stringRequest);
    }
}