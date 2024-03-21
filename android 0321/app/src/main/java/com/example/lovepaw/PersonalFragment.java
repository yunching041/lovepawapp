// PersonalFragment.java
package com.example.lovepaw;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class PersonalFragment extends Fragment {

    private TabLayout personalTabLayout;
    private ViewPager personalViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_personal, container, false);

        personalTabLayout = rootView.findViewById(R.id.personalTabLayout);
        personalViewPager = rootView.findViewById(R.id.personalViewPager);

        // Create an adapter for the ViewPager
        VPAdapter personalAdapter = new VPAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        personalAdapter.addFragment(new FirstTabFragment(), "毛爸媽");
        personalAdapter.addFragment(new SecondTabFragment(), "代理爸媽");
        // Add more fragments and titles if needed

        // Set the adapter on the ViewPager
        personalViewPager.setAdapter(personalAdapter);

        // Connect the TabLayout with the ViewPager
        personalTabLayout.setupWithViewPager(personalViewPager);

        return rootView;
    }
}
