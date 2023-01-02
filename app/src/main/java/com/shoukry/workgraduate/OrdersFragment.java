package com.shoukry.workgraduate;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.shoukry.workgraduate.AdapterFragments.DetailsAdapter;
import com.shoukry.workgraduate.AdapterFragments.LoginFragmentAdapter;
import com.shoukry.workgraduate.databinding.FragmentOrdersBinding;
import com.shoukry.workgraduate.details.CompletedFragment;
import com.shoukry.workgraduate.details.PendingFragment;
import com.shoukry.workgraduate.details.UnderwayFragment;

import java.util.ArrayList;


public class OrdersFragment extends Fragment {
    FragmentOrdersBinding binding;
    LoginFragmentAdapter adapter;

    OnClickItemDetails onClickItemDetails;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnClickItemDetails){
            onClickItemDetails = (OnClickItemDetails) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onClickItemDetails =null;
    }

    public OrdersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOrdersBinding.inflate(inflater, container, false);

        // Inflate the layout for this fragment
        ArrayList<Fragment> arrayList = new ArrayList<>();
        arrayList.add(new PendingFragment());
        arrayList.add(new CompletedFragment());
        arrayList.add(new UnderwayFragment());


        adapter = new LoginFragmentAdapter(getActivity(), arrayList);
        binding.ordersViewPager.setAdapter(adapter);
        setTittleTabLayout();

        binding.tabLayoutService.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getText().toString().equals("Pending")){

                    onClickItemDetails.onClickItemTabLayout(tab.getText().toString());
                }else if (tab.getText().toString().equals("UnderWay")){
                    onClickItemDetails.onClickItemTabLayout(tab.getText().toString());

                }else if (tab.getText().toString().equals("Completed")){
                    onClickItemDetails.onClickItemTabLayout(tab.getText().toString());

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        binding.ordersViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                binding.tabLayoutService.selectTab(binding.tabLayoutService.getTabAt(position));
            }
        });

        return binding.getRoot();
    }


    private void setTittleTabLayout() {
        ArrayList<String> arrayListTabLayout = new ArrayList<>();
        arrayListTabLayout.add("Pending");
        arrayListTabLayout.add("UnderWay");
        arrayListTabLayout.add("Completed");

        new TabLayoutMediator(binding.tabLayoutService, binding.ordersViewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(arrayListTabLayout.get(position));

            }
        }).attach();
    }


    public interface OnClickItemDetails{
        void onClickItemTabLayout(String tabName);
    }
}