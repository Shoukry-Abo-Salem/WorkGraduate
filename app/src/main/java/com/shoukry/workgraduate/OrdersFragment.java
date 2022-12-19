package com.shoukry.workgraduate;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
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
        // Inflate the layout for this fragment
        ArrayList<Fragment> arrayList = new ArrayList<>();
        arrayList.add(new PendingFragment());
        arrayList.add(new CompletedFragment());
        arrayList.add(new UnderwayFragment());

        adapter = new LoginFragmentAdapter(getActivity(),arrayList);
        binding.ordersViewPager.setAdapter(adapter);

        binding = FragmentOrdersBinding.inflate(inflater,container,false);
        binding.tabLayoutService.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.ordersViewPager.setCurrentItem(tab.getPosition());
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
}